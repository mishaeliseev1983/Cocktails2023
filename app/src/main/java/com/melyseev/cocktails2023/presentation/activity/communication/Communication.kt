package com.melyseev.cocktails2023.presentation.activity.communication

import com.melyseev.cocktails2023.common.Communications
import com.melyseev.cocktails2023.presentation.activity.NavigationStrategy
import javax.inject.Inject


interface NavigationStrategyState: Communications.Mutable<NavigationStrategy>{
    class Base @Inject constructor(): NavigationStrategyState, Communications.SinglePost<NavigationStrategy>()
}