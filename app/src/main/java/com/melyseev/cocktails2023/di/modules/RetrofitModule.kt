package com.melyseev.cocktails2023.di.modules

import com.melyseev.cocktails2023.data.cloud.CloudDataSource
import com.melyseev.cocktails2023.data.cloud.CloudeModule
import com.melyseev.cocktails2023.data.cloud.CocktailsService
import dagger.Module
import dagger.Provides

@Module
class RetrofitModule {
    @Provides
    fun provideCloudDataService(): CocktailsService{
        return CloudeModule.Base().getService(CocktailsService::class.java)
    }

    @Provides
    fun provideCloudDataSource(cocktailService: CocktailsService): CloudDataSource{
        return CloudDataSource.Base(cocktailService)
    }
}