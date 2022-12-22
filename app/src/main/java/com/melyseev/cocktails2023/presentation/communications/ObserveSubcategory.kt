package com.melyseev.cocktails2023.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.melyseev.cocktails2023.presentation.list_subcategories.SubcategoryResultUI

interface ObserveSubcategory {
    fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>)
    fun observeState(owner: LifecycleOwner, observer: Observer<SubcategoryResultUI>)
}