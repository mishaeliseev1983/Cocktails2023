package com.melyseev.cocktails2023.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.melyseev.cocktails2023.data.cache.entity.SubcategoryEntity


@Dao
interface SubcategoriesDao {
    @Insert
    suspend fun insert(entity: SubcategoryEntity)

    @Query("SELECT * from ${SubcategoryEntity.TABLE_SUBCATEGORY} WHERE ${SubcategoryEntity.ID_CATEGORY} = :idCategory")
    suspend fun getAllSubcategories(idCategory: Int): List<SubcategoryEntity>

    @Query("UPDATE ${SubcategoryEntity.TABLE_SUBCATEGORY} SET ${SubcategoryEntity.SUBCATEGORY_CHECKED} = :checkValue WHERE ${SubcategoryEntity.ID_CATEGORY} = :idCategory AND  ${SubcategoryEntity.SUBCATEGORY_NAME} = :subcategory")
    suspend fun updateSubcategory(idCategory: Int, subcategory: String, checkValue: Int)
}