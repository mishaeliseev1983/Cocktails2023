package com.melyseev.cocktails2023.presentation.select_subcategory

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melyseev.cocktails2023.domain.main.CocktailsInteractor
import com.melyseev.cocktails2023.domain.main.subcategories.ResultSubcategory
import com.melyseev.cocktails2023.domain.main.subcategories.SubcategoryDomain
import com.melyseev.cocktails2023.presentation.activity.FetchNavigation
import com.melyseev.cocktails2023.presentation.activity.NavigationCommunication
import com.melyseev.cocktails2023.presentation.activity.NavigationStrategy
import com.melyseev.cocktails2023.presentation.main.DispatchersList
import com.melyseev.cocktails2023.presentation.main.communications.ObserveSubcategories
import com.melyseev.cocktails2023.presentation.main.communications.SubcategoriesCommunications
import com.melyseev.cocktails2023.presentation.select_subcategory_ui_objects.SubcategoryResultUI
import com.melyseev.cocktails2023.presentation.select_subcategory_ui_objects.SubcategoryUI
import com.melyseev.cocktails2023.presentation.select_subcategory_ui_objects.SubcategoryUIEmpty
import kotlinx.coroutines.launch
import javax.inject.Inject

class SelectSubcategoryViewModel @Inject constructor(
    private val dispatchersList: DispatchersList,
    private val communications: SubcategoriesCommunications,
    private val navigationCommunication: NavigationCommunication,
    private val interactor: CocktailsInteractor
) : ViewModel(), ObserveSubcategories, FetchSubcategoryList, FetchNavigation {


    override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) {
        communications.observeProgress(owner, observer)
    }

    override fun observeStateSubcategoryList(
        owner: LifecycleOwner,
        observer: Observer<SubcategoryResultUI>
    ) {
        communications.observeStateSubcategoryList(owner, observer)
    }

    override fun fetchListSubcategory() {
        viewModelScope.launch(dispatchersList.io()) {
            communications.showProgress(View.VISIBLE)
            val result = interactor.fetchListSubcategory(interactor.getCategory())
            val resultUI = result.map(object : ResultSubcategory.Mapper<SubcategoryResultUI> {
                override fun mapToUi(
                    listSubcategoryDomain: List<SubcategoryDomain>,
                    message: String
                ): SubcategoryResultUI =
                    if (message.isEmpty()) {
                        if (listSubcategoryDomain.isEmpty())
                            SubcategoryResultUI.Success(listOf(SubcategoryUIEmpty))
                        else
                            SubcategoryResultUI.Success(listSubcategoryDomain.map {
                                SubcategoryUI(
                                    title = it.title,
                                    isSelected = it.isSelected
                                )
                            })
                    } else {
                        SubcategoryResultUI.Failure(message = message)
                    }

            })

            communications.showSubcategoryListState(state = resultUI)
            communications.showProgress(View.GONE)
        }
    }

    override fun updateSelectSubcategory(subcategory: String, isSelect: Boolean) {
        viewModelScope.launch {
            communications.showProgress(View.VISIBLE)
            interactor.updateSelectSubcategory(interactor.getCategory(), subcategory, isSelect)

            val result = interactor.fetchListSubcategory(interactor.getCategory())
            val resultUI = result.map(object : ResultSubcategory.Mapper<SubcategoryResultUI> {
                override fun mapToUi(
                    listSubcategoryDomain: List<SubcategoryDomain>,
                    message: String
                ): SubcategoryResultUI =
                    if (message.isEmpty()) {
                        val listSubcategoryUI = listSubcategoryDomain.map {
                            SubcategoryUI(
                                title = it.title,
                                isSelected = it.isSelected
                            )
                        }
                        SubcategoryResultUI.Success(listSubcategoryUI)
                    } else {
                        SubcategoryResultUI.Failure(message = message)
                    }

            })

            communications.showSubcategoryListState(state = resultUI)
            communications.showProgress(View.GONE)
        }
    }

    override fun navigate(navigationStrategy: NavigationStrategy) {
        navigationCommunication.change(navigationStrategy)
    }


}