package com.melyseev.cocktails2023.presentation

import android.app.Application
import com.melyseev.cocktails2023.di.DaggerApplicationComponent

class App : Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}