package com.melyseev.cocktails2023.di.modules

import com.melyseev.cocktails2023.data.SubcategoryData
import com.melyseev.cocktails2023.data.cache.entity.FavoriteEntity
import com.melyseev.cocktails2023.data.cache.entity.SubcategoryEntity
import com.melyseev.cocktails2023.data.details_cocktail.DetailsCocktailDto
import com.melyseev.cocktails2023.data.list_category.MapDrinkToDomain
import com.melyseev.cocktails2023.data.short_cocktail.Drink
import com.melyseev.cocktails2023.data.short_cocktail.ShortDto
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
        return object : DetailsCocktailDto.Mapper<DetailsCocktailDomain>{
        override fun map(drinks: List<com.melyseev.cocktails2023.data.details_cocktail.Drink>): DetailsCocktailDomain {
            val drink = drinks[0]
            return DetailsCocktailDomain(
                title = drink.strDrink ?: "",
                image = drink.strDrinkThumb?:"",
                ingredients = emptyList(),
                instructions = drink.strInstructions ?: ""
            )
        }
     }
    }

    @Provides
    fun provideMapCocktailShortDtoToDomain(): ShortDto.Mapper<List<CocktailShortDomain> > {
        return object : ShortDto.Mapper<List<CocktailShortDomain>> {
            override fun mapToDomain(drinks: List<Drink>): List<CocktailShortDomain> {
                return drinks.map {
                    CocktailShortDomain(
                        id = it.idDrink.toInt(),
                        title = it.strDrink,
                        urlImage = it.strDrinkThumb
                    )
                }
            }
        }
    }

    @Provides
    fun provideMapSubcategoryEntityToSubcategoryData() =
        object : SubcategoryEntity.Mapper<SubcategoryData>{
            override fun map(
                idCategory: Int,
                subcategoryName: String,
                subcategoryChecked: Int
            ): SubcategoryData {
                return SubcategoryData(name = subcategoryName, checked = subcategoryChecked==1)
            }
        }


    @Provides
    fun provideMapToSubcategoryData(): MapDrinkToDomain.Mapper<SubcategoryData> {
        return object : MapDrinkToDomain.Mapper<SubcategoryData> {
            override fun map(category: String): SubcategoryData {
                return SubcategoryData(name = category, checked = false)
            }
        }
    }

    @Provides
    fun provideMapSubcategoryDataToSubcategoryDomain(): SubcategoryData.Mapper<SubcategoryDomain> {
        return object : SubcategoryData.Mapper<SubcategoryDomain> {
            override fun map(name: String, checked: Boolean): SubcategoryDomain {
                return SubcategoryDomain(title = name, isSelected = checked)
            }
        }
    }


    @Provides
    fun provideMapFavoriteEntityToFavoriteStateCocktailDomain(): FavoriteEntity.Mapper<FavoriteStateCocktailDomain>{
        return object : FavoriteEntity.Mapper<FavoriteStateCocktailDomain> {

            /* override fun map(cocktailId: Int, like: Int): FavoriteStateCocktailDomain {
                return FavoriteStateCocktailDomain(cocktailId = cocktailId, isFavorite = like == 1)
            }

            */
            override fun map(
                cocktailId: Int,
                cocktailTitle: String,
                cocktailImage: String,
                like: Int
            ): FavoriteStateCocktailDomain {
                return FavoriteStateCocktailDomain(cocktailId, like == 1)
            }
        }
    }

    @Provides
    fun provideMapFavoriteEntityToCocktailShortDomain(): FavoriteEntity.Mapper<CocktailShortDomain>{
        return object : FavoriteEntity.Mapper<CocktailShortDomain> {
            override fun map(
                cocktailId: Int,
                cocktailTitle: String,
                cocktailImage: String,
                like: Int
            ): CocktailShortDomain {
                return CocktailShortDomain(cocktailId,cocktailTitle, cocktailImage)
            }
        }
    }




/*
    @Provides
    fun provideMapDetailsCocktailToUI(): DetailsCocktailDomain.Mapper<DetailsCocktailResultUI>{
        return object : DetailsCocktailDomain.Mapper<DetailsCocktailResultUI>{
            override fun map(title: String, image: String, instructions: String): DetailsCocktailResultUI {
                return  DetailsCocktailResultUI(title, image, instructions)
            }
        }
    }
*/
}