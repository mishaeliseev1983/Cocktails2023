package com.melyseev.cocktails2023.presentation.details_cocktail.communications

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.melyseev.cocktails2023.common.Communications
import com.melyseev.cocktails2023.presentation.details_cocktail.CocktailFavoriteStateResultUI
import com.melyseev.cocktails2023.presentation.details_cocktail.ui_objects.DetailsCocktailResultUI
import javax.inject.Inject

interface DetailsCocktailCommunications : ObserveDetailsCocktail {
    fun showProgress(value: Int)
    fun showDetailsCocktailState(state: DetailsCocktailResultUI)
    fun showLike(state: CocktailFavoriteStateResultUI)

    class Base @Inject constructor(
        private val progress: Communications.ProgressCommunication,
        private val detailsCocktailStateCommunication: DetailsCocktailStateCommunication,
        private val likeStateCommunication: LikeStateCommunication,
        ): DetailsCocktailCommunications{
        override fun showProgress(value: Int) {
            progress.change(value)
        }

        override fun showDetailsCocktailState(state: DetailsCocktailResultUI) {
            detailsCocktailStateCommunication.change(state)
        }

        override fun showLike(state: CocktailFavoriteStateResultUI) {
            likeStateCommunication.change(state)
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

        override fun observeStateLikeCocktail(
            owner: LifecycleOwner,
            observer: Observer<CocktailFavoriteStateResultUI>
        ) {
            likeStateCommunication.observe(owner, observer)
        }

    }
}