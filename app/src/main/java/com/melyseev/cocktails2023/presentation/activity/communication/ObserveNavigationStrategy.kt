package com.melyseev.cocktails2023.presentation.activity.communication

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.melyseev.cocktails2023.presentation.activity.NavigationStrategy

interface ObserveNavigationStrategy {
    fun observeNavigationStrategy(owner: LifecycleOwner, observer: Observer<NavigationStrategy>)
}