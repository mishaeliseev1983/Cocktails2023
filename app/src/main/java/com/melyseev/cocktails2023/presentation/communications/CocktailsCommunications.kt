package com.melyseev.cocktails2023.presentation.communications

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.melyseev.cocktails2023.presentation.list_cocktails.CocktailResultUI
import com.melyseev.cocktails2023.presentation.list_subcategories.SubcategoryResultUI
import javax.inject.Inject

interface CocktailsCommunications : ObserveCocktails {
    fun showProgress(value: Int)
    fun showSubcategoryListState(state: SubcategoryResultUI)
    fun showCocktailListState(state: CocktailResultUI)


    class Base @Inject constructor(
        private val progress: Communications.ProgressCommunication,
        private val stateSubcategoryList: Communications.SubcategoryListStateCommunication,
        private val stateCocktailList: Communications.CocktailListStateCommunication,
    ) : CocktailsCommunications {
        override fun showProgress(value: Int) {
            progress.change(value)
        }

        override fun showSubcategoryListState(state: SubcategoryResultUI){
            stateSubcategoryList.change(state)
        }

        override fun showCocktailListState(state: CocktailResultUI) {
            stateCocktailList.change(state)
        }

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) {
            progress.observe(owner, observer)
        }

        override fun observeStateSubcategoryList(
            owner: LifecycleOwner,
            observer: Observer<SubcategoryResultUI>
        ) =   stateSubcategoryList.observe(owner, observer)

        override fun observeStateCocktailList(
            owner: LifecycleOwner,
            observer: Observer<CocktailResultUI>
        ) = stateCocktailList.observe(owner, observer)
    }
}