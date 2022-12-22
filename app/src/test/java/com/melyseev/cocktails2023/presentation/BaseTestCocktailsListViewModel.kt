package com.melyseev.cocktails2023.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.melyseev.cocktails2023.domain.CocktailsInteractor
import com.melyseev.cocktails2023.domain.cocktails.ResultCocktail
import com.melyseev.cocktails2023.domain.subcategories.ResultSubcategory
import com.melyseev.cocktails2023.domain.subcategories.SubcategoryDomain
import com.melyseev.cocktails2023.presentation.communications.SubcategoryCommunications
import com.melyseev.cocktails2023.presentation.list_subcategories.SubcategoryResultUI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

abstract class BaseTestCocktailsListViewModel {


    class TestSubcategoryCommunications: SubcategoryCommunications{

        val listShowProgress = mutableListOf<Int>()
        val listShowState = mutableListOf<SubcategoryResultUI>()

        override fun showProgress(value: Int) {
            listShowProgress.add(value)
        }

        override fun showState(state: SubcategoryResultUI) {
            listShowState.add(state)
        }


        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) {
            TODO("Not yet implemented")
        }

        override fun observeState(owner: LifecycleOwner, observer: Observer<SubcategoryResultUI>) {
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