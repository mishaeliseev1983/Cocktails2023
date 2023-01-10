package com.melyseev.cocktails2023.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.melyseev.cocktails2023.R
import com.melyseev.cocktails2023.presentation.App
import com.melyseev.cocktails2023.presentation.main.CocktailsListFragment
import com.melyseev.cocktails2023.presentation.main.ViewModuleFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ShowFragment {

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
            val cocktailsListFragment = CocktailsListFragment.newInstance()
            show(cocktailsListFragment, add = true)
        }
    }

    override fun show(fragment: Fragment, add: Boolean) {

        viewModel.viewModelScope.launch {

        }
        if (add)
            supportFragmentManager.beginTransaction().add(R.id.container, fragment).commit()
        else
            supportFragmentManager.beginTransaction()
                .addToBackStack(fragment.javaClass.canonicalName).replace(R.id.container, fragment)
                .commit()
    }
}