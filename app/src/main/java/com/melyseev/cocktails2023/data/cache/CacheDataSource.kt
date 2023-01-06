package com.melyseev.cocktails2023.data.cache

import com.melyseev.cocktails2023.common.*
import com.melyseev.cocktails2023.data.SubcategoryData
import com.melyseev.cocktails2023.data.cache.dao.CategoriesDao
import com.melyseev.cocktails2023.data.cache.dao.SubcategoriesDao
import com.melyseev.cocktails2023.data.cache.entity.CategoryEntity
import com.melyseev.cocktails2023.data.cache.entity.SubcategoryEntity
import javax.inject.Inject

interface CacheDataSource {
    suspend fun insertListSubcategories(list: List<SubcategoryEntity>)
    suspend fun getListSubcategories(idCategory: Long): List<SubcategoryData>
    suspend fun changeCheckSubcategory(idCategory: Long, subcategory: String, checkValue: Int)

    suspend fun getListCategories(): List<CategoryEntity>

    suspend fun fillTableCategory()
    suspend fun getIdCategory(category: String): Long

    class Base @Inject constructor(
        val daoCategoriesDao: CategoriesDao,
        val daoSubcategoriesDao: SubcategoriesDao
    ) : CacheDataSource {
        override suspend fun insertListSubcategories(
            list: List<SubcategoryEntity>
        ) = list.forEach {
            daoSubcategoriesDao.insert(it)
        }

        override suspend fun getListSubcategories(idCategory: Long): List<SubcategoryData> =
            daoSubcategoriesDao.getAllSubcategories(idCategory = idCategory).map{ subcategoryEntity ->
                SubcategoryData(subcategoryEntity.subcategoryName, subcategoryEntity.subcategoryChecked==1)
            }


        override suspend fun changeCheckSubcategory(
            idCategory: Long,
            subcategory: String,
            checkValue: Int
        ) =
            daoSubcategoriesDao.updateSubcategory(
                idCategory = idCategory,
                subcategory = subcategory,
                checkValue = checkValue
            )


        override suspend fun getListCategories() =
            daoCategoriesDao.getAllCategories()


        override suspend fun fillTableCategory() {
            if (daoCategoriesDao.getAllCategories().isNotEmpty()) return
            daoCategoriesDao.insert(CategoryEntity(30, CATEGORY_BY_ALCOHOLIC))
            daoCategoriesDao.insert(CategoryEntity(31, CATEGORY_BY_CATEGORIES))
            daoCategoriesDao.insert(CategoryEntity(32, CATEGORY_BY_GLASSES))
            daoCategoriesDao.insert(CategoryEntity(33, CATEGORY_BY_INGREDIENTS))
            daoCategoriesDao.insert(CategoryEntity(34, CATEGORY_BY_FAVORITE))
        }

        override suspend fun getIdCategory(category: String): Long {
            val categories = daoCategoriesDao.getAllCategories()
            return categories.first { it.categoryName == category }.id
        }
    }
}