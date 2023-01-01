package com.melyseev.cocktails2023.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.melyseev.cocktails2023.data.cache.entity.SubcategoryEntity


@Dao
interface SubcategoriesDao {
    @Insert
    suspend fun insert(entity: SubcategoryEntity)

    @Query("SELECT * from ${SubcategoryEntity.TABLE_SUBCATEGORY}")
    suspend fun getAllCategories(): List<SubcategoryEntity>
}