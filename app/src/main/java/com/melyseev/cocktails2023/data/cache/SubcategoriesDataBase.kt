package com.melyseev.cocktails2023.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.melyseev.cocktails2023.data.cache.dao.CategoriesDao
import com.melyseev.cocktails2023.data.cache.dao.SubcategoriesDao
import com.melyseev.cocktails2023.data.cache.entity.CategoryEntity
import com.melyseev.cocktails2023.data.cache.entity.SubcategoryEntity


@Database(entities = [CategoryEntity::class, SubcategoryEntity::class], version = 1, exportSchema = false)
abstract class SubcategoriesDataBase  : RoomDatabase() {
    abstract fun subcategoriesDao(): SubcategoriesDao
    abstract fun categoriesDao(): CategoriesDao
    companion object{
        val DATABASE_NAME = "cocktailsdb2023"
    }
}