package com.melyseev.cocktails2023.presentation

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melyseev.cocktails2023.domain.CocktailsInteractor
import com.melyseev.cocktails2023.domain.subcategories.ResultSubcategory
import com.melyseev.cocktails2023.domain.subcategories.SubcategoryDomain
import com.melyseev.cocktails2023.presentation.communications.SubcategoryCommunications
import com.melyseev.cocktails2023.presentation.list_subcategories.SubcategoryResultUI
import com.melyseev.cocktails2023.presentation.list_subcategories.SubcategoryUI
import kotlinx.coroutines.launch
import javax.inject.Inject

class CocktailsListViewModel @Inject constructor(
    private val dispatchersList: DispatchersList,
    private val communications: SubcategoryCommunications,
    private val interactor: CocktailsInteractor
) : ViewModel(),
    ObserveSubcategory, FetchList {

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
            communications.showState(resultUI)
        }
    }

    override fun fetchListCocktails() {
        viewModelScope.launch(dispatchersList.io()) {
            communications.showProgress(View.VISIBLE)

            //SUBCATEGORY
            //get cocktails
            val result = interactor.fetchListCocktails(CATEGORY, SUBCATEGORY)
            communications.showProgress(View.GONE)
        }
    }


    override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) {
        communications.observeProgress(owner, observer)
    }

    override fun observeState(owner: LifecycleOwner, observer: Observer<SubcategoryResultUI>) {
        communications.observeState(owner, observer)
    }


    companion object {
        var CATEGORY = "In"
        var SUBCATEGORY = "11"
    }

}