package com.melyseev.cocktails2023.presentation.activity

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.melyseev.cocktails2023.presentation.activity.communication.NavigationStrategyState
import com.melyseev.cocktails2023.presentation.activity.communication.ObserveNavigationStrategy
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val navigationStrategyState: NavigationStrategyState
) : ViewModel(), ObserveNavigationStrategy, FetchNavigation {
    override fun observeNavigationStrategy(
        owner: LifecycleOwner,
        observer: Observer<NavigationStrategy>
    ) =
        navigationStrategyState.observe(owner, observer)


    override fun navigate(navigationStrategy: NavigationStrategy) =
        navigationStrategyState.change(navigationStrategy)

}