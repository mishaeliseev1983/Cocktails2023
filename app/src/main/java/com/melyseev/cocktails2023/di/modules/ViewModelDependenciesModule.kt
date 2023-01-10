package com.melyseev.cocktails2023.di.modules


import com.melyseev.cocktails2023.data.CocktailsRepositoryImpl
import com.melyseev.cocktails2023.data.HandleErrorToDomainException
import com.melyseev.cocktails2023.data.SelectCategorySubcategoryRepositoryImpl
import com.melyseev.cocktails2023.di.ApplicationScope
import com.melyseev.cocktails2023.domain.HandleDomainExceptionToString
import com.melyseev.cocktails2023.domain.SelectCategorySubcategoryRepository
import com.melyseev.cocktails2023.domain.main.CocktailsInteractor
import com.melyseev.cocktails2023.domain.main.CocktailsRepository
import com.melyseev.cocktails2023.presentation.main.DispatchersList
import dagger.Binds
import dagger.Module

@Module
interface ViewModelDependenciesModule {

    @Binds
    fun provideDispatchersList(param: DispatchersList.Base): DispatchersList

    @ApplicationScope
    @Binds
    fun provideInteractor(param: CocktailsInteractor.Base): CocktailsInteractor


    @ApplicationScope
    @Binds
    fun provideRepository(param: CocktailsRepositoryImpl): CocktailsRepository

    @ApplicationScope
    @Binds
    fun provideRepositorySelect(param: SelectCategorySubcategoryRepositoryImpl): SelectCategorySubcategoryRepository

    @ApplicationScope
    @Binds
    fun provideHandleErrorToDomainException(param: HandleErrorToDomainException.Base): HandleErrorToDomainException

    @ApplicationScope
    @Binds
    fun provideHandleDomainExceptionToString(param: HandleDomainExceptionToString.Base): HandleDomainExceptionToString

}