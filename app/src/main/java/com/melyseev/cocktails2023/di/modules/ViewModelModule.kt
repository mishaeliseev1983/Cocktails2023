package com.melyseev.cocktails2023.di.modules

import androidx.lifecycle.ViewModel
import com.melyseev.cocktails2023.di.ViewModelKey
import com.melyseev.cocktails2023.presentation.main.CocktailsListViewModel
import com.melyseev.cocktails2023.presentation.select_category.SelectCategoryViewModel
import com.melyseev.cocktails2023.presentation.select_subcategory.SelectSubcategoryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(CocktailsListViewModel::class)
    @Binds
    fun bindCocktailsListViewModel(impl: CocktailsListViewModel): ViewModel


    @IntoMap
    @ViewModelKey(SelectCategoryViewModel::class)
    @Binds
    fun bindSelectCategoryViewModel(impl: SelectCategoryViewModel): ViewModel


    @IntoMap
    @ViewModelKey(SelectSubcategoryViewModel::class)
    @Binds
    fun bindSelectSubcategoryViewModel(impl: SelectSubcategoryViewModel): ViewModel
}