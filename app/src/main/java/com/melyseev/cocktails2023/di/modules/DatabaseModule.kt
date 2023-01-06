package com.melyseev.cocktails2023.di.modules


import com.melyseev.cocktails2023.data.cache.CacheDataSource
import com.melyseev.cocktails2023.data.cache.CacheModule
import com.melyseev.cocktails2023.data.cache.SubcategoriesDataBase
import com.melyseev.cocktails2023.data.cache.dao.CategoriesDao
import com.melyseev.cocktails2023.data.cache.dao.SubcategoriesDao
import com.melyseev.cocktails2023.presentation.App
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule {

    @Provides
    fun provideDataBase(app: App): SubcategoriesDataBase {
        return CacheModule.Base(context = app).provideDataBase()
    }

    @Provides
    fun provideCategoriesDao(subcategoriesDataBase: SubcategoriesDataBase): CategoriesDao {
        return subcategoriesDataBase.categoriesDao()
    }

    @Provides
    fun provideSubcategoriesDao(subcategoriesDataBase: SubcategoriesDataBase): SubcategoriesDao {
        return subcategoriesDataBase.subcategoriesDao()
    }

    @Provides
    fun provideCacheDataSource(param: CacheDataSource.Base): CacheDataSource{
        return param
    }

    /*
    @Provides
    fun provideMapperToNumberCache(): NumberData.Mapper<NumberCache> {
        return object : NumberData.Mapper<NumberCache> {
            override fun map(id: String, fact: String): NumberCache {
                return NumberCache(id = id, fact = fact, date = System.currentTimeMillis())
            }
        }
    }

    @Provides
    fun provideCacheDataSource(param: CacheDataSource.Base): CacheDataSource{
        return param
    }

     */
}