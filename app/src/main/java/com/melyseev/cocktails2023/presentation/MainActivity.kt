package com.melyseev.cocktails2023.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.melyseev.cocktails2023.R
import com.melyseev.cocktails2023.presentation.main.CocktailsListFragment

class MainActivity : AppCompatActivity(), ShowFragment {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val cocktailsListFragment = CocktailsListFragment.newInstance()
            show(cocktailsListFragment, add = true)
        }
    }

    override fun show(fragment: Fragment, add: Boolean) {

        if (add)
            supportFragmentManager.beginTransaction().add(R.id.container, fragment).commit()
        else
            supportFragmentManager.beginTransaction()
                .addToBackStack(fragment.javaClass.canonicalName).replace(R.id.container, fragment)
                .commit()
    }
}