package com.melyseev.cocktails2023.di.modules

import androidx.lifecycle.ViewModel
import com.melyseev.cocktails2023.di.ViewModelKey
import com.melyseev.cocktails2023.presentation.CocktailsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(CocktailsListViewModel::class)
    @Binds
    fun bindCocktailsListViewModel(impl: CocktailsListViewModel): ViewModel
}