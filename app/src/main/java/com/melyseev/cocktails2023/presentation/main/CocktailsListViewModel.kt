package com.melyseev.cocktails2023.presentation.main

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melyseev.cocktails2023.domain.main.CocktailsInteractor
import com.melyseev.cocktails2023.domain.main.short_cocktail.CocktailShortDomain
import com.melyseev.cocktails2023.domain.main.short_cocktail.ResultCocktail
import com.melyseev.cocktails2023.domain.main.subcategories.ResultSubcategory
import com.melyseev.cocktails2023.domain.main.subcategories.SubcategoryDomain
import com.melyseev.cocktails2023.presentation.main.communications.CocktailsCommunications
import com.melyseev.cocktails2023.presentation.main.communications.ObserveCocktails
import com.melyseev.cocktails2023.presentation.main.list_cocktails.CocktailResultUI
import com.melyseev.cocktails2023.presentation.main.list_cocktails.CocktailUI
import com.melyseev.cocktails2023.presentation.SubcategoryResultUI
import com.melyseev.cocktails2023.presentation.SubcategoryUI
import com.melyseev.cocktails2023.presentation.main.list_cocktails.CocktailUIEmpty
import kotlinx.coroutines.launch
import javax.inject.Inject

class CocktailsListViewModel @Inject constructor(
    private val dispatchersList: DispatchersList,
    private val communications: CocktailsCommunications,
    private val interactor: CocktailsInteractor
) : ViewModel(),
    ObserveCocktails, FetchList {

    fun fetchData() {
        viewModelScope.launch(dispatchersList.io()) {
            communications.showProgress(View.VISIBLE)
            fetchListSubcategory()
            fetchListCocktails()
            communications.showProgress(View.GONE)
        }
    }

    override suspend fun fetchListSubcategory() {
        val result = interactor.fetchListSubcategorySelected(interactor.getCategory())
        val resultUI = result.map(object : ResultSubcategory.Mapper<SubcategoryResultUI> {
            override fun mapToUi(
                listSubcategoryDomain: List<SubcategoryDomain>,
                message: String
            ): SubcategoryResultUI =
                if (message.isEmpty()) {

                    //no subcategory
                    if (interactor.getSubcategory()
                            .isEmpty() && listSubcategoryDomain.isNotEmpty()
                    ) {
                        interactor.changeSubcategory(listSubcategoryDomain[0].title)
                    }
                    val listSubcategoryUI = listSubcategoryDomain.map {
                        SubcategoryUI(
                            title = it.title,
                            isSelected = (it.title == interactor.getSubcategory())
                        )
                    }
                    SubcategoryResultUI.Success(listSubcategoryUI)
                } else {
                    SubcategoryResultUI.Failure(message = message)
                }

        })
        communications.showSubcategoryListState(state = resultUI)
    }


    override suspend fun fetchListCocktails() {
        //SUBCATEGORY
        //get cocktails
        val result =
            interactor.fetchListCocktails(interactor.getCategory(), interactor.getSubcategory())

        val resultUI = result.map(object : ResultCocktail.Mapper<CocktailResultUI> {
            override fun mapToUi(
                cocktailDomainDomain: List<CocktailShortDomain>,
                message: String
            ): CocktailResultUI =
                if (message.isEmpty()) {
                    if (cocktailDomainDomain.isEmpty())
                        CocktailResultUI.Success(list = listOf(CocktailUIEmpty))
                    else
                        CocktailResultUI.Success(list = cocktailDomainDomain.map {
                            CocktailUI(it.id, it.title, it.urlImage)
                        })
                } else
                    CocktailResultUI.Failure(message = message)
        })
        communications.showCocktailListState(state = resultUI)
    }

    override fun fetchCategoryName() {
        viewModelScope.launch {
            communications.showCategoryNameState(interactor.getCategory())
        }
    }


    override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) {
        communications.observeProgress(owner, observer)
    }

    override fun observeStateSubcategoryList(
        owner: LifecycleOwner, observer: Observer<SubcategoryResultUI>
    ) {
        communications.observeStateSubcategoryList(owner, observer)
    }

    override fun observeStateCocktailList(
        owner: LifecycleOwner,
        observer: Observer<CocktailResultUI>
    ) {
        communications.observeStateCocktailList(owner, observer)
    }

    override fun observeStateCategoryName(owner: LifecycleOwner, observer: Observer<String>) {
        communications.observeStateCategoryName(owner, observer)
    }

    fun selectSubcategoryCocktails(subcategory: String) {
        interactor.changeSubcategory(subcategory)
        fetchData()
    }


}