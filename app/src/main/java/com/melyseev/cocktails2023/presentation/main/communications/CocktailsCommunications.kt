package com.melyseev.cocktails2023.presentation.main.communications

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.melyseev.cocktails2023.common.Communications
import com.melyseev.cocktails2023.presentation.main.list_cocktails.CocktailResultUI
import com.melyseev.cocktails2023.presentation.main.list_subcategories.SubcategoryResultUI
import javax.inject.Inject

interface CocktailsCommunications : ObserveCocktails {
    fun showProgress(value: Int)
    fun showSubcategoryListState(state: SubcategoryResultUI)
    fun showCocktailListState(state: CocktailResultUI)
    fun showCategoryNameState(state: String)


    class Base @Inject constructor(
        private val progress: Communications.ProgressCommunication,
        private val stateSubcategoryList: Communications.SubcategoryListStateCommunication,
        private val stateCocktailList: Communications.CocktailListStateCommunication,
        private val stateCategoryName: Communications.CategoryNameStateCommunication,
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

        override fun showCategoryNameState(state: String) {
            stateCategoryName.change(state)
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

        override fun observeStateCategoryName(owner: LifecycleOwner, observer: Observer<String>
        ) = stateCategoryName.observe(owner, observer)

    }
}