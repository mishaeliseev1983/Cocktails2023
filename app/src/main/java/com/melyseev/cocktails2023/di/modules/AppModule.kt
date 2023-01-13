package com.melyseev.cocktails2023.di.modules

import android.content.Context
import com.melyseev.cocktails2023.di.ApplicationScope
import com.melyseev.cocktails2023.presentation.App
import com.melyseev.cocktails2023.presentation.main.ManageResources
import dagger.Module
import dagger.Provides

@Module
object AppModule {

    @ApplicationScope
    @Provides
    fun provideApplication(app: Context): App {
        return app as App
    }

    @ApplicationScope
    @Provides
    fun managerResources(app: Context): ManageResources {
        return ManageResources.Base(app)
    }
}