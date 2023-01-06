package com.melyseev.cocktails2023.data.cache

import android.content.Context
import androidx.room.Room

interface CacheModule {

    fun provideDataBase(): SubcategoriesDataBase

    class Base(private val context: Context) : CacheModule {

        private val database by lazy {
            return@lazy Room.databaseBuilder(
                context,
                SubcategoriesDataBase::class.java,
                DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }

        override fun provideDataBase(): SubcategoriesDataBase = database

        companion object{
            const val DATABASE_NAME = "cocktails_db"
        }
    }
}