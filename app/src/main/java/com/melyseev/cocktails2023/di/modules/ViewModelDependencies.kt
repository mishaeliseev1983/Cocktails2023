package com.melyseev.cocktails2023.di.modules


import com.melyseev.cocktails2023.common.Communications
import com.melyseev.cocktails2023.data.CocktailsRepositoryImpl
import com.melyseev.cocktails2023.data.HandleErrorToDomainException
import com.melyseev.cocktails2023.data.SelectCategorySubcategoryRepositoryImpl
import com.melyseev.cocktails2023.di.ApplicationScope
import com.melyseev.cocktails2023.domain.HandleDomainExceptionToString
import com.melyseev.cocktails2023.domain.SelectCategorySubcategoryRepository
import com.melyseev.cocktails2023.domain.main.CocktailsInteractor
import com.melyseev.cocktails2023.domain.main.CocktailsRepository
import com.melyseev.cocktails2023.presentation.main.DispatchersList
import com.melyseev.cocktails2023.presentation.main.communications.CocktailsCommunications
import com.melyseev.cocktails2023.presentation.main.communications.SubcategoriesCommunications
import com.melyseev.cocktails2023.presentation.select_category.communications.SelectCategoryCommunications
import dagger.Binds
import dagger.Module

@Module
interface ViewModelDependencies {

    @ApplicationScope
    @Binds
    fun provideDispatchersList(param: DispatchersList.Base): DispatchersList

    @ApplicationScope
    @Binds
    fun provideSubcategoriesCommunications(param: SubcategoriesCommunications.Base): SubcategoriesCommunications

    @ApplicationScope
    @Binds
    fun provideCocktailsCommunications(param: CocktailsCommunications.Base): CocktailsCommunications

    @Binds
    fun provideStateSelectCategoryCommunication(param: SelectCategoryCommunications.Base): SelectCategoryCommunications

    @Binds
    fun provideProgressCommunication(param: Communications.ProgressCommunication.Base): Communications.ProgressCommunication

    @Binds
    fun provideStateListSubcategoryCommunication(param: Communications.SubcategoryListStateCommunication.Base): Communications.SubcategoryListStateCommunication

    //@Binds
    //fun provideStateSubcategorySelectedListStateCommunication(param: Communications.SubcategorySelectedListStateCommunication.Base): Communications.SubcategorySelectedListStateCommunication

    @Binds
    fun provideStateListCocktailCommunication(param: Communications.CocktailListStateCommunication.Base): Communications.CocktailListStateCommunication

    @Binds
    fun provideCategoryNameStateCommunication(param: Communications.CategoryNameStateCommunication.Base): Communications.CategoryNameStateCommunication

    @Binds
    fun provideStateSelectedCategoryCommunication(param: Communications.SelectedCategoryCommunication.Base): Communications.SelectedCategoryCommunication


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