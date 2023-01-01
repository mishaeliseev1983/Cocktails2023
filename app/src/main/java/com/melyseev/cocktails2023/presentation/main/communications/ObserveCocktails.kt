package com.melyseev.cocktails2023.presentation.main.communications

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.melyseev.cocktails2023.presentation.main.list_cocktails.CocktailResultUI

interface ObserveCocktails: ObserveSubcategories{
    fun observeStateCocktailList(owner: LifecycleOwner, observer: Observer<CocktailResultUI>)
    fun observeStateCategoryName(owner: LifecycleOwner, observer: Observer<String>)
}