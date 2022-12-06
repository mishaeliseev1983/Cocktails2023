package com.melyseev.cocktails2023

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.melyseev.cocktails2023.domain.CocktailsInteractor
import com.melyseev.cocktails2023.domain.ResultSubcategory
import com.melyseev.cocktails2023.domain.SubcategoryDomain
import com.melyseev.cocktails2023.presentation.SubcategoryUI
import com.melyseev.cocktails2023.presentation.ResultUI
import com.melyseev.cocktails2023.presentation.communications.SubcategoryCommunications

abstract class BaseTestCocktailsListViewModel {


    class TestSubcategoryCommunications: SubcategoryCommunications{



        val listShowProgress = mutableListOf<Int>()
        val listShowList = mutableListOf<SubcategoryUI>()
        val listShowState = mutableListOf<ResultUI>()

        override fun showProgress(value: Int) {
            listShowProgress.add(value)
        }

        override fun showState(state: ResultUI) {
            listShowState.add(state)
        }

        override fun showList(list: List<SubcategoryUI>) {
            listShowList.clear()
            listShowList.addAll(list)
        }

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) {
            TODO("Not yet implemented")
        }

        override fun observeState(owner: LifecycleOwner, observer: Observer<ResultUI>) {
            TODO("Not yet implemented")
        }

        override fun observeList(owner: LifecycleOwner, observer: Observer<List<SubcategoryUI>>) {
            TODO("Not yet implemented")
        }

    }


    class TestCocktailsInteractor: CocktailsInteractor {


        val list = mutableListOf<SubcategoryDomain>()
        override fun fetchListSubcategory(category: String): ResultSubcategory {
            return ResultSubcategory.Success(list)
        }

        fun changeExpectedResult(newList: List<SubcategoryDomain>){
            list.clear()
            list.addAll(newList)
        }

    }



}