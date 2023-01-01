package com.melyseev.cocktails2023.presentation.main.communications

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.melyseev.cocktails2023.common.Communications
import com.melyseev.cocktails2023.presentation.SubcategoryResultUI
import javax.inject.Inject

interface SubcategoriesCommunications: ObserveSubcategories {

    fun showSubcategoryListState(state: SubcategoryResultUI)
    fun showProgress(show: Int)
    class Base  @Inject constructor(
        private val progress: Communications.ProgressCommunication,
        private val stateSubcategoryList: Communications.SubcategoryListStateCommunication,
        ): SubcategoriesCommunications{

        override fun showSubcategoryListState(state: SubcategoryResultUI) {
            stateSubcategoryList.change(state)
        }

        override fun showProgress(show: Int) {
            progress.change(show)
        }

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) {
            progress.observe(owner, observer)
        }

        override fun observeStateSubcategoryList(
            owner: LifecycleOwner,
            observer: Observer<SubcategoryResultUI>
        ) {
            stateSubcategoryList.observe(owner, observer)
        }
    }
}