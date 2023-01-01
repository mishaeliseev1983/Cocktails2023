package com.melyseev.cocktails2023.presentation.main.communications

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.melyseev.cocktails2023.presentation.SubcategoryResultUI

interface ObserveSubcategories {
    fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>)
    fun observeStateSubcategoryList(owner: LifecycleOwner, observer: Observer<SubcategoryResultUI>)
}