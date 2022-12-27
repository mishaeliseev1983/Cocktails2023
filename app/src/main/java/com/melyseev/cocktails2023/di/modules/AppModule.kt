package com.melyseev.cocktails2023.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.melyseev.cocktails2023.data.SharedPreferencesData
import com.melyseev.cocktails2023.di.ApplicationScope
import com.melyseev.cocktails2023.presentation.App
import dagger.Module
import dagger.Provides

@Module
object AppModule {

    @ApplicationScope
    @Provides
    fun provideApplication(app: Context): App {
        return app as App
    }
}