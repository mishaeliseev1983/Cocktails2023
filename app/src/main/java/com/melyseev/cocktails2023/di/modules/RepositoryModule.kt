package com.melyseev.cocktails2023.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.melyseev.cocktails2023.data.SharedPreferencesData
import com.melyseev.cocktails2023.data.list_category.MapDrinkToDomain
import com.melyseev.cocktails2023.di.ApplicationScope
import com.melyseev.cocktails2023.domain.main.subcategories.SubcategoryDomain
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

    @Provides
    fun getSharedData(app: Context): SharedPreferences {
        return app.getSharedPreferences("PREFERENCE_NAME11123", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideSharedData(sharedPreferences: SharedPreferences) : SharedPreferencesData {
        return SharedPreferencesData(sharedPreferences)
    }

}