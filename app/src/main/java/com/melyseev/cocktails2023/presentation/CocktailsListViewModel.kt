package com.melyseev.cocktails2023.presentation

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melyseev.cocktails2023.data.common.CATEGORY_DEFAULT
import com.melyseev.cocktails2023.data.common.SUBCATEGORY_DEFAULT
import com.melyseev.cocktails2023.domain.CocktailsInteractor
import com.melyseev.cocktails2023.domain.cocktails.CocktailDomain
import com.melyseev.cocktails2023.domain.cocktails.ResultCocktail
import com.melyseev.cocktails2023.domain.subcategories.ResultSubcategory
import com.melyseev.cocktails2023.domain.subcategories.SubcategoryDomain
import com.melyseev.cocktails2023.presentation.communications.CocktailsCommunications
import com.melyseev.cocktails2023.presentation.communications.ObserveCocktails
import com.melyseev.cocktails2023.presentation.list_cocktails.CocktailResultUI
import com.melyseev.cocktails2023.presentation.list_cocktails.CocktailUI
import com.melyseev.cocktails2023.presentation.list_subcategories.SubcategoryResultUI
import com.melyseev.cocktails2023.presentation.list_subcategories.SubcategoryUI
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class CocktailsListViewModel @Inject constructor(
    private val dispatchersList: DispatchersList,
    private val communications: CocktailsCommunications,
    private val interactor: CocktailsInteractor
) : ViewModel(),
    ObserveCocktails, FetchList {

    override fun fetchListSubcategory() {

        viewModelScope.launch(dispatchersList.io()) {
            communications.showProgress(View.VISIBLE)
            val result = interactor.fetchListSubcategory(CATEGORY)
            val resultUI = result.map(object : ResultSubcategory.Mapper<SubcategoryResultUI> {
                override fun mapToUi(
                    listSubcategoryDomain: List<SubcategoryDomain>,
                    message: String
                ): SubcategoryResultUI =
                    if (message.isEmpty()) {
                        val listSubcategoryUI = listSubcategoryDomain.map {
                            SubcategoryUI(title = it.title, isSelected = (it.title == SUBCATEGORY))
                        }
                        SubcategoryResultUI.Success(listSubcategoryUI)
                    } else {
                        SubcategoryResultUI.Failure(message = message)
                    }

            })

            communications.showProgress(View.GONE)
            communications.showSubcategoryListState(state = resultUI)
        }
    }

    override fun fetchListCocktails() {
        viewModelScope.launch(dispatchersList.io()) {
            communications.showProgress(View.VISIBLE)

            //SUBCATEGORY
            //get cocktails
            val result = interactor.fetchListCocktails(CATEGORY, SUBCATEGORY)

            val resultUI = result.map( object : ResultCocktail.Mapper<CocktailResultUI>{
                override fun mapToUi(
                    cocktailDomainDomain: List<CocktailDomain>,
                    message: String
                ): CocktailResultUI =
                    if(message.isEmpty()){
                        CocktailResultUI.Success(list = cocktailDomainDomain.map {
                            CocktailUI( it.title)
                        })
                    }else
                        CocktailResultUI.Failure(message = message)
            })

            communications.showCocktailListState(state = resultUI)
            communications.showProgress(View.GONE)
        }
    }


    override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) {
        communications.observeProgress(owner, observer)
    }

    override fun observeStateSubcategoryList(
        owner: LifecycleOwner, observer: Observer<SubcategoryResultUI>){
          communications.observeStateSubcategoryList(owner, observer)
    }

    override fun observeStateCocktailList(
        owner: LifecycleOwner,
        observer: Observer<CocktailResultUI>
    ) {
        communications.observeStateCocktailList(owner, observer)
    }

    companion object {
        var CATEGORY = CATEGORY_DEFAULT
        var SUBCATEGORY = SUBCATEGORY_DEFAULT
    }

}