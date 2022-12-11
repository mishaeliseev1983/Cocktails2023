package com.melyseev.cocktails2023.di

import android.content.Context
import com.melyseev.cocktails2023.di.modules.AppModule
import com.melyseev.cocktails2023.di.modules.ViewModelDependencies
import com.melyseev.cocktails2023.di.modules.ViewModelModule
import com.melyseev.cocktails2023.presentation.CocktailsListFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [AppModule::class, ViewModelModule::class, ViewModelDependencies::class])
interface ApplicationComponent {

    fun inject(fragment: CocktailsListFragment)

    @Component.Factory
    interface AppCompFactory{
        fun create(
            @BindsInstance context: Context): ApplicationComponent
    }
}