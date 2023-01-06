package com.melyseev.cocktails2023.di.modules

import com.melyseev.cocktails2023.data.SubcategoryData
import com.melyseev.cocktails2023.data.list_category.MapDrinkToDomain
import com.melyseev.cocktails2023.domain.main.subcategories.SubcategoryDomain
import dagger.Module
import dagger.Provides

@Module
class MappersModules {

    @Provides
    fun provideMapToSubcategoryDomain(): MapDrinkToDomain.Mapper<SubcategoryDomain> {
        return object : MapDrinkToDomain.Mapper<SubcategoryDomain> {
            override fun map(category: String): SubcategoryDomain {
                return SubcategoryDomain(title = category)
            }
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