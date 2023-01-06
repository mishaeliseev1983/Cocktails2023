package com.melyseev.cocktails2023.presentation.details_cocktail.communications

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.melyseev.cocktails2023.common.Communications
import com.melyseev.cocktails2023.presentation.details_cocktail.DetailsCocktailResultUI
import javax.inject.Inject

interface DetailsCocktailCommunications : ObserveDetailsCocktail {
    fun showProgress(value: Int)
    fun showDetailsCocktailState(state: DetailsCocktailResultUI)


    class Base @Inject constructor(
        private val progress: Communications.ProgressCommunication,
        private val detailsCocktailStateCommunication: Communications.DetailsCocktailStateCommunication,

        ): DetailsCocktailCommunications{
        override fun showProgress(value: Int) {
            progress.change(value)
        }

        override fun showDetailsCocktailState(state: DetailsCocktailResultUI) {
            detailsCocktailStateCommunication.change(state)
        }

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) {
            progress.observe(owner, observer)
        }

        override fun observeStateDetailsCocktail(
            owner: LifecycleOwner,
            observer: Observer<DetailsCocktailResultUI>
        ) {
           detailsCocktailStateCommunication.observe(owner, observer)
        }

    }
}