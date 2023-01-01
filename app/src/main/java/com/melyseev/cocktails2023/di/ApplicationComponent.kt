package com.melyseev.cocktails2023.di

import android.content.Context
import com.melyseev.cocktails2023.di.modules.*
import com.melyseev.cocktails2023.presentation.main.CocktailsListFragment
import com.melyseev.cocktails2023.presentation.select_category.SelectCategoryFragment
import com.melyseev.cocktails2023.presentation.select_subcategory.SelectSubcategoryFragment
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
    fun inject(fragment: SelectCategoryFragment)
    fun inject(fragment: SelectSubcategoryFragment)
    @Component.Factory
    interface AppCompFactory {
        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}