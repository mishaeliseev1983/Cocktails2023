package com.melyseev.cocktails2023.data.cache

import com.melyseev.cocktails2023.common.*
import com.melyseev.cocktails2023.data.cache.dao.CategoriesDao
import com.melyseev.cocktails2023.data.cache.dao.FavoritesDao
import com.melyseev.cocktails2023.data.cache.dao.SubcategoriesDao
import com.melyseev.cocktails2023.data.cache.entity.CategoryEntity
import com.melyseev.cocktails2023.data.cache.entity.FavoriteEntity
import com.melyseev.cocktails2023.data.cache.entity.SubcategoryEntity
import javax.inject.Inject

interface CacheDataSource {
    suspend fun insertListSubcategories(list: List<SubcategoryEntity>)
    suspend fun getListSubcategories(idCategory: Int): List<SubcategoryEntity>
    suspend fun changeCheckSubcategory(idCategory: Int, subcategory: String, checkValue: Int)

    suspend fun getListCategories(): List<CategoryEntity>

    suspend fun fillTableCategory()
    suspend fun getIdCategory(category: String): Int

    suspend fun getAllCocktailFavorites(): List<FavoriteEntity>
    suspend fun updateAndGetCocktailFavoriteState(cocktailId: Int): FavoriteEntity
    suspend fun getCocktailFavoriteState(cocktailId: Int): FavoriteEntity
    suspend fun isSavedCocktailFavoriteState(cocktailId: Int): Boolean
    suspend fun insertCocktailFavoriteState(
        cocktailId: Int,
        cocktailTitle: String,
        cocktailImage: String
    )

    class Base @Inject constructor(
        val daoCategoriesDao: CategoriesDao,
        val daoSubcategoriesDao: SubcategoriesDao,
        val daoFavoritesDao: FavoritesDao
    ) : CacheDataSource {
        override suspend fun insertListSubcategories(
            list: List<SubcategoryEntity>
        ) = list.forEach {
            daoSubcategoriesDao.insert(it)
        }

        override suspend fun getListSubcategories(idCategory: Int): List<SubcategoryEntity> =
            daoSubcategoriesDao.getAllSubcategories(idCategory = idCategory)


        override suspend fun changeCheckSubcategory(
            idCategory: Int,
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
            daoCategoriesDao.insert(CategoryEntity(1, CATEGORY_BY_ALCOHOLIC))
            daoCategoriesDao.insert(CategoryEntity(2, CATEGORY_BY_CATEGORIES))
            daoCategoriesDao.insert(CategoryEntity(3, CATEGORY_BY_GLASSES))
            daoCategoriesDao.insert(CategoryEntity(4, CATEGORY_BY_INGREDIENTS))
            daoCategoriesDao.insert(CategoryEntity(5, CATEGORY_BY_FAVORITE))
        }

        override suspend fun getIdCategory(category: String): Int {
            val categories = daoCategoriesDao.getAllCategories()
            return categories.firstOrNull { it.categoryName == category }?.id ?: 1
        }

        override suspend fun getAllCocktailFavorites(): List<FavoriteEntity> {
            return daoFavoritesDao.getListFavorite()
        }

        override suspend fun updateAndGetCocktailFavoriteState(cocktailId: Int): FavoriteEntity {
            val favoriteEntity = daoFavoritesDao.searchById(cocktailId = cocktailId)
            favoriteEntity?.let {
                if (it.like == 0) it.like = 1
                else
                    it.like = 0
                daoFavoritesDao.update(it)
                return it
            }
            return FavoriteEntity.Empty
        }

        override suspend fun getCocktailFavoriteState(cocktailId: Int): FavoriteEntity{
            var favoriteEntity = daoFavoritesDao.searchById(cocktailId = cocktailId)
            if(favoriteEntity == null)
                favoriteEntity= FavoriteEntity.Empty
           return favoriteEntity
        }

        override suspend fun isSavedCocktailFavoriteState(cocktailId: Int) =
            daoFavoritesDao.searchById(cocktailId) != null

        override suspend fun insertCocktailFavoriteState(
            cocktailId: Int,
            cocktailTitle: String,
            cocktailImage: String
        ) {
            daoFavoritesDao.insert(
                FavoriteEntity(
                    cocktailId = cocktailId,
                    cocktailTitle = cocktailTitle,
                    cocktailImage = cocktailImage
                )
            )
        }


    }
}