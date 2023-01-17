package com.melyseev.cocktails2023.data

import com.melyseev.cocktails2023.data.cache.CacheDataSource
import com.melyseev.cocktails2023.data.cache.entity.CategoryEntity
import com.melyseev.cocktails2023.data.cache.entity.FavoriteEntity
import com.melyseev.cocktails2023.data.cache.entity.SubcategoryEntity


class TestCacheDataSource : CacheDataSource {

    var expectedListFavoriteEntity: List<FavoriteEntity> = emptyList()
    fun setExpectedListFavoritesEntity(value: List<FavoriteEntity>) {
        expectedListFavoriteEntity = value
    }


    var expectedListSubcategoryEntity: List<SubcategoryEntity>  = emptyList()
    fun changeExpectedListSubcategoryEntity(value: List<SubcategoryEntity>) {
        expectedListSubcategoryEntity = value
    }

    override suspend fun insertListSubcategories(list: List<SubcategoryEntity>) = Unit

    override suspend fun getListSubcategories(idCategory: Int): List<SubcategoryEntity> {
        return expectedListSubcategoryEntity
    }



    override suspend fun changeCheckSubcategory(
        idCategory: Int, subcategory: String, checkValue: Int
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun getListCategories(): List<CategoryEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun fillTableCategory() = Unit

    override suspend fun getIdCategory(category: String) = 0

    override suspend fun getAllCocktailFavorites(): List<FavoriteEntity> {
        return expectedListFavoriteEntity
    }

    override suspend fun updateAndGetCocktailFavoriteState(cocktailId: Int): FavoriteEntity {
        TODO("Not yet implemented")
    }

    override suspend fun getCocktailFavoriteState(cocktailId: Int): FavoriteEntity {
        TODO("Not yet implemented")
    }

    override suspend fun isSavedCocktailFavoriteState(cocktailId: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun insertCocktailFavoriteState(
        cocktailId: Int, cocktailTitle: String, cocktailImage: String
    ) {
        TODO("Not yet implemented")
    }




}