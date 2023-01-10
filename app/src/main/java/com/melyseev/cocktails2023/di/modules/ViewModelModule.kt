package com.melyseev.cocktails2023.di.modules

import androidx.lifecycle.ViewModel
import com.melyseev.cocktails2023.di.ViewModelKey
import com.melyseev.cocktails2023.presentation.activity.MainViewModel
import com.melyseev.cocktails2023.presentation.details_cocktail.DetailsCocktailViewModel
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

    @IntoMap
    @ViewModelKey(DetailsCocktailViewModel::class)
    @Binds
    fun bindDetailsCocktailViewModel(impl: DetailsCocktailViewModel): ViewModel

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMainViewModel(impl: MainViewModel): ViewModel
}