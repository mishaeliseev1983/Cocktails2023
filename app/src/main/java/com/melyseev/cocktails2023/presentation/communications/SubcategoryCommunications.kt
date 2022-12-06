package com.melyseev.cocktails2023.presentation.communications

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.melyseev.cocktails2023.presentation.ObserveSubcategory
import com.melyseev.cocktails2023.presentation.SubcategoryUI
import com.melyseev.cocktails2023.presentation.ResultUI

interface SubcategoryCommunications : ObserveSubcategory {
    fun showProgress(value: Int)
    fun showState(state: ResultUI)
    fun showList(list: List<SubcategoryUI>)


    class Base(
        private val progress: Communications.ProgressCommunication,
        private val state: Communications.SubcategoryStateCommunication,
        private val list: Communications.SubcategoryListCommunication
    ) : SubcategoryCommunications {
        override fun showProgress(value: Int) {
           progress.map(value)
        }

        override fun showState(value: ResultUI) {
           state.map(value)
        }

        override fun showList(value: List<SubcategoryUI>) {
          list.map(value)
        }

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) {
           progress.observe(owner, observer)
        }

        override fun observeState(owner: LifecycleOwner, observer: Observer<ResultUI>) {
            state.observe(owner, observer)
        }

        override fun observeList(owner: LifecycleOwner, observer: Observer<List<SubcategoryUI>>) {
            list.observe(owner, observer)
        }
    }
}