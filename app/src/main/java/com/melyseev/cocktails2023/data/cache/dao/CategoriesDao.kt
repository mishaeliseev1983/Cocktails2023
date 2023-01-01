package com.melyseev.cocktails2023.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.melyseev.cocktails2023.data.cache.entity.CategoryEntity

@Dao
interface CategoriesDao {
    @Insert
    suspend fun insert(entity: CategoryEntity)

    @Query("SELECT * from ${CategoryEntity.TABLE_CATEGORY}")
    suspend fun getAllCategories(): List<CategoryEntity>
}