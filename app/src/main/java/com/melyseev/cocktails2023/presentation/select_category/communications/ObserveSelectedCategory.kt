package com.melyseev.cocktails2023.presentation.select_category.communications

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface ObserveSelectedCategory {

    fun observeSelectedCategory(owner: LifecycleOwner, observer: Observer<String>)
}