package com.melyseev.cocktails2023.di.modules


import com.melyseev.cocktails2023.data.CocktailsRepositoryImpl
import com.melyseev.cocktails2023.data.HandleErrorToDomainException
import com.melyseev.cocktails2023.di.ApplicationScope
import com.melyseev.cocktails2023.domain.CocktailsInteractor
import com.melyseev.cocktails2023.domain.CocktailsRepository
import com.melyseev.cocktails2023.domain.HandleDomainExceptionToString
import com.melyseev.cocktails2023.presentation.DispatchersList
import com.melyseev.cocktails2023.presentation.communications.Communications
import com.melyseev.cocktails2023.presentation.communications.SubcategoryCommunications
import dagger.Binds
import dagger.Module

@Module
interface ViewModelDependencies {

    @ApplicationScope
    @Binds
    fun provideDispatchersList(param: DispatchersList.Base): DispatchersList

    @ApplicationScope
    @Binds
    fun provideNumberCommunication(param: SubcategoryCommunications.Base): SubcategoryCommunications

    @Binds
    fun provideProgressCommunication(param: Communications.ProgressCommunication.Base): Communications.ProgressCommunication

    @Binds
    fun provideStateCommunication(param: Communications.SubcategoryStateCommunication.Base): Communications.SubcategoryStateCommunication


    @ApplicationScope
    @Binds
    fun provideInteractor(param: CocktailsInteractor.Base): CocktailsInteractor


    @ApplicationScope
    @Binds
    fun provideRepository(param: CocktailsRepositoryImpl): CocktailsRepository

    @ApplicationScope
    @Binds
    fun provideHandleErrorToDomainException(param: HandleErrorToDomainException.Base): HandleErrorToDomainException


    @ApplicationScope
    @Binds
    fun provideHandleDomainExceptionToString(param: HandleDomainExceptionToString.Base): HandleDomainExceptionToString
    /*
    @ApplicationScope
    @Binds
    fun provideManageResources(param: ManageResources.Base): ManageResources

     */
}