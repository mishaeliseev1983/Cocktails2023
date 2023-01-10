package com.melyseev.cocktails2023.presentation.activity

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.melyseev.cocktails2023.presentation.activity.communication.NavigationStrategyState
import com.melyseev.cocktails2023.presentation.activity.communication.ObserveNavigationStrategy
import javax.inject.Inject

interface NavigationCommunication: ObserveNavigationStrategy {

    fun change(navigationStrategy: NavigationStrategy)

    class Base @Inject constructor(
        private val navigationStrategyState: NavigationStrategyState
    ): NavigationCommunication{
        override fun change(navigationStrategy: NavigationStrategy) {
            navigationStrategyState.change(navigationStrategy)
        }

        override fun observeNavigationStrategy(
            owner: LifecycleOwner,
            observer: Observer<NavigationStrategy>
        ) {
            navigationStrategyState.observe(owner = owner, observer = observer)
        }


    }
}