package com.melyseev.cocktails2023.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.melyseev.cocktails2023.domain.main.CocktailsInteractor
import com.melyseev.cocktails2023.domain.main.short_cocktail.ResultCocktail
import com.melyseev.cocktails2023.domain.main.subcategories.ResultSubcategory
import com.melyseev.cocktails2023.domain.main.subcategories.SubcategoryDomain
import com.melyseev.cocktails2023.presentation.main.DispatchersList
import com.melyseev.cocktails2023.presentation.main.communications.CocktailsCommunications
import com.melyseev.cocktails2023.presentation.main.list_cocktails.CocktailResultUI
import com.melyseev.cocktails2023.presentation.select_subcategory_ui_objects.SubcategoryResultUI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

abstract class BaseTestCocktailsListViewModel {


    class TestSubcategoryCommunications: CocktailsCommunications {

        val listShowProgress = mutableListOf<Int>()
        val listShowState = mutableListOf<SubcategoryResultUI>()

        override fun showProgress(value: Int) {
            listShowProgress.add(value)
        }

        override fun showSubcategoryListState(state: SubcategoryResultUI) {
           listShowState.add(state)
        }

        override fun showCocktailListState(state: CocktailResultUI) {
            TODO("Not yet implemented")
        }

        override fun showCategoryNameState(state: String) {
            TODO("Not yet implemented")
        }


        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) {
            TODO("Not yet implemented")
        }

        override fun observeStateSubcategoryList(
            owner: LifecycleOwner,
            observer: Observer<SubcategoryResultUI>){

        }

        override fun observeStateCocktailList(
            owner: LifecycleOwner,
            observer: Observer<CocktailResultUI>
        ) {
            TODO("Not yet implemented")
        }

        override fun observeStateCategoryName(owner: LifecycleOwner, observer: Observer<String>) {
            TODO("Not yet implemented")
        }


    }


    class TestCocktailsInteractor: CocktailsInteractor {

        val list = mutableListOf<SubcategoryDomain>()
        val messageError = "no connection"
        lateinit var resultSubcategory: ResultSubcategory

        override suspend fun fetchListSubcategory(category: String): ResultSubcategory {
            return resultSubcategory
        }

        override suspend fun fetchListCocktails(
            category: String,
            subcategory: String
        ): ResultCocktail {
            TODO("Not yet implemented")
        }

        override fun getCategory(): String {
            TODO("Not yet implemented")
        }

        override fun getSubcategory(): String {
            TODO("Not yet implemented")
        }

        override fun changeCategory(category: String) {
            TODO("Not yet implemented")
        }

        override fun changeSubcategory(subcategory: String) {
            TODO("Not yet implemented")
        }

        fun changeExpectedResultSubcategory(newResult: ResultSubcategory){
            resultSubcategory = newResult
        }

        fun changeExpectedList(newList: List<SubcategoryDomain>){
            list.clear()
            list.addAll(newList)
        }
    }

    class TestDisptachersList: DispatchersList {
        override fun io(): CoroutineDispatcher {
            return TestCoroutineDispatcher()
        }

        override fun ui(): CoroutineDispatcher {
            return StandardTestDispatcher()
        }
    }

}