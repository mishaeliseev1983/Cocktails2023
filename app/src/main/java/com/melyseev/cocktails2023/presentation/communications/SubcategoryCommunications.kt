package com.melyseev.cocktails2023.presentation.communications

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.melyseev.cocktails2023.presentation.ObserveSubcategory
import com.melyseev.cocktails2023.presentation.ResultUI
import javax.inject.Inject

interface SubcategoryCommunications : ObserveSubcategory {
    fun showProgress(value: Int)
    fun showState(state: ResultUI)


    class Base @Inject constructor(
        private val progress: Communications.ProgressCommunication,
        private val state: Communications.SubcategoryStateCommunication,
    ) : SubcategoryCommunications {
        override fun showProgress(value: Int) {
            progress.change(value)
        }

        override fun showState(value: ResultUI) {
            state.change(value)
        }

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) {
            progress.observe(owner, observer)
        }

        override fun observeState(owner: LifecycleOwner, observer: Observer<ResultUI>) {
            state.observe(owner, observer)
        }

    }
}