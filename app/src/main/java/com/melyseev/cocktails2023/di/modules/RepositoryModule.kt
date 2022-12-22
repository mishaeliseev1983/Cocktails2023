package com.melyseev.cocktails2023.di.modules

import com.melyseev.cocktails2023.data.list_category.MapDrinkToDomain
import com.melyseev.cocktails2023.domain.subcategories.SubcategoryDomain
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideMapToSubcategoryDomain(): MapDrinkToDomain.Mapper<SubcategoryDomain> {
        return object : MapDrinkToDomain.Mapper<SubcategoryDomain> {
            override fun map(category: String): SubcategoryDomain {
                return SubcategoryDomain(title = category)
            }
        }
    }
}