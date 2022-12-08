package com.melyseev.cocktails2023.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface ObserveSubcategory {
    fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>)
    fun observeState(owner: LifecycleOwner, observer: Observer<ResultUI>)
}