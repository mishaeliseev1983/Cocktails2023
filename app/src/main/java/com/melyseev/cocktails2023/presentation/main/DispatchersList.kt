package com.melyseev.cocktails2023.presentation.main

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface DispatchersList {
    fun io(): CoroutineDispatcher
    fun ui(): CoroutineDispatcher

    class Base @Inject constructor() : DispatchersList {
        override fun io(): CoroutineDispatcher {
            return Dispatchers.IO
        }

        override fun ui(): CoroutineDispatcher {
            return Dispatchers.Main
        }
    }
}