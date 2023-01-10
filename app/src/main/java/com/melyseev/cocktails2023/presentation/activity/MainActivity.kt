package com.melyseev.cocktails2023.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.melyseev.cocktails2023.R
import com.melyseev.cocktails2023.presentation.App
import com.melyseev.cocktails2023.presentation.main.CocktailsListFragment
import com.melyseev.cocktails2023.presentation.main.ViewModuleFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModuleFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private val daggerApplicationComponent by lazy {
        (application as App).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        daggerApplicationComponent.inject(this)
        if (savedInstanceState == null) {
            viewModel.navigate(NavigationStrategy.Add(CocktailsListFragment.newInstance()))
        }

        viewModel.observeNavigationStrategy(this) {
            when (it) {
                is NavigationStrategy.Replace -> it.startTransaction(
                    supportFragmentManager,
                    R.id.container
                )
                is NavigationStrategy.Add -> it.startTransaction(
                    supportFragmentManager,
                    R.id.container
                )

                NavigationStrategy.Back -> it.startTransaction(
                    supportFragmentManager,
                    R.id.container
                )
            }
        }

    }
}