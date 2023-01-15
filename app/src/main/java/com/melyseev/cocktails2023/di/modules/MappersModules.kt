package com.melyseev.cocktails2023.di.modules

import com.melyseev.cocktails2023.data.SubcategoryData
import com.melyseev.cocktails2023.data.cache.entity.FavoriteEntity
import com.melyseev.cocktails2023.data.cache.entity.SubcategoryEntity
import com.melyseev.cocktails2023.data.details_cocktail.DetailsCocktailDto
import com.melyseev.cocktails2023.data.list_category.MapDrinkToDomain
import com.melyseev.cocktails2023.data.short_cocktail.ShortDto
import com.melyseev.cocktails2023.domain.*
import com.melyseev.cocktails2023.domain.details_cocktail.DetailsCocktailDomain
import com.melyseev.cocktails2023.domain.details_cocktail.FavoriteStateCocktailDomain
import com.melyseev.cocktails2023.domain.main.short_cocktail.CocktailShortDomain
import com.melyseev.cocktails2023.domain.main.subcategories.SubcategoryDomain
import dagger.Module
import dagger.Provides

@Module
class MappersModules {

    @Provides
    fun provideMapDetailsCocktailDtoToDetailsCocktailDomain(): DetailsCocktailDto.Mapper<DetailsCocktailDomain> {
        return mapDrinkToDetailsCocktailDomain()
    }

    @Provides
    fun provideMapShortDtoToCocktailShortDomain(): ShortDto.Mapper<List<CocktailShortDomain>> {
        return mapShortDtoToCocktailShortDomain()
    }

    @Provides
    fun provideMapSubcategoryEntityToSubcategoryData(): SubcategoryEntity.Mapper<SubcategoryData> {
        return mapSubcategoryEntityToSubcategoryData()
    }


    @Provides
    fun provideMapToSubcategoryData(): MapDrinkToDomain.Mapper<SubcategoryData> {
        return mapDrinkToSubcategoryData()
    }

    @Provides
    fun provideMapSubcategoryDataToSubcategoryDomain(): SubcategoryData.Mapper<SubcategoryDomain> {
        return mapSubcategoryDataToSubcategoryDomain()
    }


    @Provides
    fun provideMapFavoriteEntityToFavoriteStateCocktailDomain(): FavoriteEntity.Mapper<FavoriteStateCocktailDomain> {
        return mapFavoriteEntityToFavoriteStateCocktailDomain()
    }

    @Provides
    fun provideMapFavoriteEntityToCocktailShortDomain(): FavoriteEntity.Mapper<CocktailShortDomain> {
        return mapFavoriteEntityToCocktailShortDomain()
    }

}