package com.melyseev.cocktails2023.di

import android.content.Context
import com.melyseev.cocktails2023.di.modules.*
import com.melyseev.cocktails2023.presentation.CocktailsListFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [AppModule::class, ViewModelModule::class,
        ViewModelDependencies::class, RetrofitModule::class,
        RepositoryModule::class]
)
interface ApplicationComponent {

    fun inject(fragment: CocktailsListFragment)

    @Component.Factory
    interface AppCompFactory {
        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}