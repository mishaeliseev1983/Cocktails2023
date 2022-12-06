package com.melyseev.cocktails2023.presentation

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melyseev.cocktails2023.domain.CocktailsInteractor
import com.melyseev.cocktails2023.domain.ResultSubcategory
import com.melyseev.cocktails2023.presentation.communications.SubcategoryCommunications
import kotlinx.coroutines.launch

class CocktailsListViewModel(
    private val communications: SubcategoryCommunications,
    private val interactor: CocktailsInteractor
) : ViewModel(),
    ObserveSubcategory {

    fun init() {

        viewModelScope.launch {
            communications.showProgress(View.VISIBLE)

            val result = interactor.fetchListSubcategory("")
            //from resultSub to resultUI
            //communications.showState(result)
            result.

            when (result){

                is ResultSubcategory.Success -> {
                    //from SubcategoryDomain -> SubcategoryUI
                    communications.showList(result.listSubcategory)
                }
                is ResultSubcategory.Error -> communications.showState(ResultUI.Failure(result.message))
            }

            communications.showProgress(View.GONE)
        }
    }

    override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) {
        communications.observeProgress(owner, observer)
    }

    override fun observeState(owner: LifecycleOwner, observer: Observer<ResultUI>) {
        communications.observeState(owner, observer)
    }

    override fun observeList(owner: LifecycleOwner, observer: Observer<List<SubcategoryUI>>) {
        communications.observeList(owner, observer)
    }
}