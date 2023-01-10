package com.melyseev.cocktails2023.di.modules

import com.melyseev.cocktails2023.common.Communications
import com.melyseev.cocktails2023.di.ApplicationScope
import com.melyseev.cocktails2023.presentation.activity.NavigationCommunication
import com.melyseev.cocktails2023.presentation.activity.communication.NavigationStrategyState
import com.melyseev.cocktails2023.presentation.details_cocktail.communications.DetailsCocktailCommunications
import com.melyseev.cocktails2023.presentation.details_cocktail.communications.DetailsCocktailStateCommunication
import com.melyseev.cocktails2023.presentation.details_cocktail.communications.LikeStateCommunication
import com.melyseev.cocktails2023.presentation.main.communications.CocktailsCommunications
import com.melyseev.cocktails2023.presentation.main.communications.SubcategoriesCommunications
import com.melyseev.cocktails2023.presentation.select_category.communications.SelectCategoryCommunications
import dagger.Binds
import dagger.Module

@Module
interface CommunicationsModule {

    @Binds
    fun provideSubcategoriesCommunications(param: SubcategoriesCommunications.Base): SubcategoriesCommunications

    @Binds
    fun provideCocktailsCommunications(param: CocktailsCommunications.Base): CocktailsCommunications

    @Binds
    fun provideDetailsCocktailCommunications(param: DetailsCocktailCommunications.Base): DetailsCocktailCommunications

    @Binds
    fun provideStateSelectCategoryCommunication(param: SelectCategoryCommunications.Base): SelectCategoryCommunications

    @Binds
    fun provideProgressCommunication(param: Communications.ProgressCommunication.Base): Communications.ProgressCommunication

    @Binds
    fun provideStateListSubcategoryCommunication(param: Communications.SubcategoryListStateCommunication.Base): Communications.SubcategoryListStateCommunication

    @Binds
    fun provideStateListCocktailCommunication(param: Communications.CocktailListStateCommunication.Base): Communications.CocktailListStateCommunication

    @Binds
    fun provideCategoryNameStateCommunication(param: Communications.CategoryNameStateCommunication.Base): Communications.CategoryNameStateCommunication

    @Binds
    fun provideStateSelectedCategoryCommunication(param: Communications.SelectedCategoryCommunication.Base): Communications.SelectedCategoryCommunication

    @Binds
    fun provideStateDetailsCocktailStateCommunication(param: DetailsCocktailStateCommunication.Base): DetailsCocktailStateCommunication

    @Binds
    fun provideStateLikeStateCommunication(param: LikeStateCommunication.Base): LikeStateCommunication

    @ApplicationScope
    @Binds
    fun provideNavigationCommunication(param: NavigationCommunication.Base): NavigationCommunication

    @ApplicationScope
    @Binds
    fun provideStateNavigationStrategyStateCommunication(param: NavigationStrategyState.Base): NavigationStrategyState
}