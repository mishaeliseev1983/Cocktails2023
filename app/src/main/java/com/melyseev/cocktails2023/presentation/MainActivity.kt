package com.melyseev.cocktails2023.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.melyseev.cocktails2023.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            val cocktailsListFragment= CocktailsListFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.container, cocktailsListFragment).commit()
        }

    }
}