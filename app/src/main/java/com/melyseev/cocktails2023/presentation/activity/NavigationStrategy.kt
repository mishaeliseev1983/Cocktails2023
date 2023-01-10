package com.melyseev.cocktails2023.presentation.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface NavigationStrategy {

    fun startTransaction(supportFragmentManager: FragmentManager, containerId: Int)

    class Replace(val fragment: Fragment) : NavigationStrategy {
        override fun startTransaction(supportFragmentManager: FragmentManager, containerId: Int) {
            supportFragmentManager.beginTransaction().replace(containerId, fragment)
                .addToBackStack(fragment.javaClass.canonicalName).commit()
        }
    }

    class Add(val fragment: Fragment) : NavigationStrategy {
        override fun startTransaction(supportFragmentManager: FragmentManager, containerId: Int) {
            supportFragmentManager.beginTransaction().add(containerId, fragment).commit()
        }
    }
}