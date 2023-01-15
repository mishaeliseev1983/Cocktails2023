package com.melyseev.cocktails2023.data

import com.melyseev.cocktails2023.data.cache.CacheDataSource
import com.melyseev.cocktails2023.data.cache.entity.CategoryEntity
import com.melyseev.cocktails2023.data.cache.entity.FavoriteEntity
import com.melyseev.cocktails2023.data.cache.entity.SubcategoryEntity
import com.melyseev.cocktails2023.data.cloud.CloudDataSource
import com.melyseev.cocktails2023.data.details_cocktail.DetailsCocktailDto
import com.melyseev.cocktails2023.data.list_category.subcategory_alcoholic.ListAlcoholicDto
import com.melyseev.cocktails2023.data.list_category.subcategory_category.ListCategoryDto
import com.melyseev.cocktails2023.data.list_category.subcategory_glass.ListGlassDto
import com.melyseev.cocktails2023.data.list_category.subcategory_ingredient.ListIngredientDto
import com.melyseev.cocktails2023.data.short_cocktail.ShortDto
import com.melyseev.cocktails2023.domain.*
import com.melyseev.cocktails2023.domain.main.CocktailsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class TestCocktailsRepositoryImpl {

    private lateinit var repository: CocktailsRepository
    lateinit var cloudDataSource: TestCloudDataSource
    lateinit var cacheDataSource: TestCacheDataSource

    @Before
    fun setUp() {
        cloudDataSource = TestCloudDataSource()
        cacheDataSource = TestCacheDataSource()
        repository = getRepositoryImpl()
    }

    private fun getRepositoryImpl() : CocktailsRepositoryImpl {
        val map1 = mapDrinkToDetailsCocktailDomain()
        val map2 = mapSubcategoryDataToSubcategoryDomain()
        val map3 = mapDrinkToSubcategoryData()
        val map4 = mapSubcategoryEntityToSubcategoryData()
        val map5 = mapFavoriteEntityToCocktailShortDomain()
        val map6 = mapShortDtoToCocktailShortDomain()
        val map7 = mapFavoriteEntityToFavoriteStateCocktailDomain()
        val handleErrorToDomainException = HandleErrorToDomainException.Base()

        return CocktailsRepositoryImpl(
            cloudDataSource, cacheDataSource,
            map1, map2, map3, map4, map5, map6, map7, handleErrorToDomainException
        )
    }


    @Test
    fun getDetailsCocktailById_Success(): Unit = runBlocking{


        repository.getDetailsCocktailById(1)

    }

    @Test
    fun getCocktailsList(): Unit = runBlocking{


        repository.getListCocktails("", "")
    }




}


class TestCloudDataSource : CloudDataSource {
    override suspend fun getCocktailsBySubcategory(
        category: String,
        subcategory: String
    ): ShortDto {
        TODO("Not yet implemented")
    }

    override suspend fun getSubcategoriesByCategory(): ListCategoryDto {
        TODO("Not yet implemented")
    }

    override suspend fun getSubcategoriesByGlass(): ListGlassDto {
        TODO("Not yet implemented")
    }

    override suspend fun getSubcategoriesByIngredient(): ListIngredientDto {
        TODO("Not yet implemented")
    }

    override suspend fun getSubcategoriesByAlcoholic(): ListAlcoholicDto {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailsCocktailById(cocktailId: Int): DetailsCocktailDto {
        TODO("Not yet implemented")
    }

}

class TestCacheDataSource : CacheDataSource {
    override suspend fun insertListSubcategories(list: List<SubcategoryEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun getListSubcategories(idCategory: Int): List<SubcategoryEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun changeCheckSubcategory(
        idCategory: Int,
        subcategory: String,
        checkValue: Int
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun getListCategories(): List<CategoryEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun fillTableCategory() {
        TODO("Not yet implemented")
    }

    override suspend fun getIdCategory(category: String): Int {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCocktailFavorites(): List<FavoriteEntity> {
        TODO("Not yet implemented")
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
        cocktailId: Int,
        cocktailTitle: String,
        cocktailImage: String
    ) {
        TODO("Not yet implemented")
    }

}