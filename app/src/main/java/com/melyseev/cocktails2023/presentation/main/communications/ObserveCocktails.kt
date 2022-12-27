package com.melyseev.cocktails2023.presentation.main.communications

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.melyseev.cocktails2023.presentation.main.list_cocktails.CocktailResultUI
import com.melyseev.cocktails2023.presentation.main.list_subcategories.SubcategoryResultUI

interface ObserveCocktails {
    fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>)
    fun observeStateSubcategoryList(owner: LifecycleOwner, observer: Observer<SubcategoryResultUI>)
    fun observeStateCocktailList(owner: LifecycleOwner, observer: Observer<CocktailResultUI>)
    fun observeStateCategoryName(owner: LifecycleOwner, observer: Observer<String>)
}