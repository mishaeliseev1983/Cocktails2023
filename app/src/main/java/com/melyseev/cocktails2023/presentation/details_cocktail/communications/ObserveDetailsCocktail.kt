package com.melyseev.cocktails2023.presentation.details_cocktail.communications

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.melyseev.cocktails2023.presentation.details_cocktail.CocktailFavoriteStateResultUI
import com.melyseev.cocktails2023.presentation.details_cocktail.ui_objects.DetailsCocktailResultUI


interface ObserveDetailsCocktail {
    fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>)
    fun observeStateDetailsCocktail(
        owner: LifecycleOwner,
        observer: Observer<DetailsCocktailResultUI>
    )
    fun observeStateLikeCocktail(
        owner: LifecycleOwner,
        observer: Observer<CocktailFavoriteStateResultUI>
    )
}
