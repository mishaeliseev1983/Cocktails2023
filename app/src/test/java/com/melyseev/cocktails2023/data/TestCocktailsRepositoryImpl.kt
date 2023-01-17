package com.melyseev.cocktails2023.data

import com.melyseev.cocktails2023.common.CATEGORY_BY_GLASSES
import com.melyseev.cocktails2023.data.cache.entity.FavoriteEntity
import com.melyseev.cocktails2023.data.cache.entity.SubcategoryEntity
import com.melyseev.cocktails2023.data.details_cocktail.DetailsCocktailDto
import com.melyseev.cocktails2023.data.details_cocktail.Drink
import com.melyseev.cocktails2023.data.list_category.subcategory_glass.ListGlassDto
import com.melyseev.cocktails2023.data.short_cocktail.ShortDto
import com.melyseev.cocktails2023.domain.*
import com.melyseev.cocktails2023.domain.main.CocktailsRepository
import com.melyseev.cocktails2023.domain.main.short_cocktail.CocktailShortDomain
import com.melyseev.cocktails2023.domain.main.subcategories.SubcategoryDomain
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class TestCocktailsRepositoryImpl {

    private lateinit var repository: CocktailsRepository
    lateinit var cloudDataSource: TestCloudDataSource
    lateinit var cacheDataSource: TestCacheDataSource
    val map1 = mapDrinkToDetailsCocktailDomain()
    val map2 = mapSubcategoryDataToSubcategoryDomain()
    val map3 = mapDrinkToSubcategoryData()
    val map4 = mapSubcategoryEntityToSubcategoryData()
    val map5 = mapFavoriteEntityToCocktailShortDomain()
    val map6 = mapShortDtoToCocktailShortDomain()
    val map7 = mapFavoriteEntityToFavoriteStateCocktailDomain()

    @Before
    fun setUp() {
        cloudDataSource = TestCloudDataSource()
        cacheDataSource = TestCacheDataSource()
        val handleErrorToDomainException = HandleErrorToDomainException.Base()

        repository = CocktailsRepositoryImpl(
            cloudDataSource,
            cacheDataSource,
            map1,
            map2,
            map3,
            map4,
            map5,
            map6,
            map7,
            handleErrorToDomainException
        )
    }

    @Test
    fun getDetailsCocktailById_Success(): Unit = runBlocking {
        cloudDataSource.changeExpectedDetailsDto(
            DetailsCocktailDto(
                drinks = listOf(
                    Drink(idDrink = "12", strDrink = "drink1", strCategory = "category1")
                )
            )
        )
        val expected = map1.map(cloudDataSource.detailsCocktailDto.drinks)
        val actual = repository.getDetailsCocktailById(1)
        assertEquals(expected, actual)
    }

    @Test(expected = DomainException.NoInternetConnectionException::class)
    fun getDetailsCocktailById_Error(): Unit = runBlocking {
        cloudDataSource.changeNoInternetConnection(true)
        repository.getDetailsCocktailById(1)
    }

    @Test
    fun getCocktailsList_Success_EmptySubcategory(): Unit = runBlocking {

        val actual = repository.getListCocktails("12", "")
        val expected = emptyList<CocktailShortDomain>()
        assertEquals(expected, actual)
    }


    @Test
    fun getCocktailsList_Success_NotFavoriteCategory(): Unit = runBlocking {

        cloudDataSource.changeExpectedShortDto(
            ShortDto(
                listOf(
                    com.melyseev.cocktails2023.data.short_cocktail.Drink("1", "1", "1"),
                    com.melyseev.cocktails2023.data.short_cocktail.Drink("2", "21", "22"),
                    com.melyseev.cocktails2023.data.short_cocktail.Drink("3", "31", "32")
                )
            )
        )

        val actual = repository.getListCocktails("Glasses", "Something")
        val expected = map6.mapToDomain(cloudDataSource.expectedShortDto.drinks)

        assertEquals(expected, actual)
    }

    @Test(expected = DomainException.NoInternetConnectionException::class)
    fun getCocktailsList_Error_NotFavoriteCategory(): Unit = runBlocking {
        cloudDataSource.noInternetConnection = true
        repository.getListCocktails("Glasses", "Something")
    }


    @Test
    fun getCocktailsList_Success_FavoriteCategory(): Unit = runBlocking {

        cloudDataSource.changeExpectedShortDto(
            ShortDto(
                listOf(
                    com.melyseev.cocktails2023.data.short_cocktail.Drink("1", "1", "1"),
                    com.melyseev.cocktails2023.data.short_cocktail.Drink("2", "21", "22"),
                    com.melyseev.cocktails2023.data.short_cocktail.Drink("3", "31", "32")
                )
            )
        )
        val expectedList = listOf(
            FavoriteEntity(id = 1, cocktailId = 1, "1", "2", 1),
            FavoriteEntity(id = 2, cocktailId = 2, "2", "3", 1),
        )
        cacheDataSource.setExpectedListFavoritesEntity(expectedList)
        val actual = repository.getListCocktails("Favorites", "Something")
        assertEquals(expectedList.size, actual.size)
        for (i in actual.indices) {
            val expectedValue = expectedList[i]
            val expected = map5.map(
                expectedValue.id,
                expectedValue.cocktailTitle,
                expectedValue.cocktailImage,
                expectedValue.like
            )
            assertEquals(expected, actual[i])
        }
    }

    @Test
    fun getListSubcategories_Success_Is_Cache() = runBlocking {


        val expectedListSubcategoryEntity =
            listOf<SubcategoryEntity>(SubcategoryEntity(1, 1, "Gin", 1))

        cacheDataSource.changeExpectedListSubcategoryEntity(expectedListSubcategoryEntity)
        var expected: MutableList<SubcategoryDomain> = mutableListOf()
        for (i in expectedListSubcategoryEntity.indices) {
            val entity = expectedListSubcategoryEntity[i]
            val subcategoryData =
                map4.map(entity.idCategory, entity.subcategoryName, entity.subcategoryChecked)
            expected.add(map2.map(subcategoryData.name, subcategoryData.checked))
        }

        val actual = repository.getListSubcategories(CATEGORY_BY_GLASSES)
        assertEquals(expected.size, actual.size)
        for (i in actual.indices) {
            assertEquals(expected[i].title, actual[i].title)
        }

    }

    @Test
    fun getListSubcategories_Success_Not_Cache() = runBlocking {


        val listGlassDto = ListGlassDto(
            listOf(
                com.melyseev.cocktails2023.data.list_category.subcategory_glass.Drink(strGlass = "Stakan")
            )
        )
        cloudDataSource.changeExpectListSubcategoriesByGlass(listGlassDto)


        var expected = mutableListOf<SubcategoryDomain>()
        for (i in listGlassDto.drinks.indices) {
            val subcategoryData = map3.map(listGlassDto.drinks[i].strGlass)
            val subcategoryDomain = map2.map(subcategoryData.name, subcategoryData.checked)
            expected.add(subcategoryDomain)
        }
        val actual = repository.getListSubcategories(CATEGORY_BY_GLASSES)

        assertEquals(expected.size, actual.size)
        for (i in actual.indices) {
            assertEquals(expected[i].title, actual[i].title)
        }


        val expectedListSubcategoryEntity =
            listOf<SubcategoryEntity>(
                SubcategoryEntity(
                    idCategory = 1,
                    subcategoryName = "Stakan2",
                    subcategoryChecked = 0
                )
            )
        cacheDataSource.changeExpectedListSubcategoryEntity(expectedListSubcategoryEntity)

        val actualSaveDb = repository.getListSubcategories(CATEGORY_BY_GLASSES)
        var expectedSaveDb = mutableListOf<SubcategoryDomain>()
        for (i in expectedListSubcategoryEntity.indices) {
            val subcategoryData = map4.map(
                expectedListSubcategoryEntity[i].idCategory,
                expectedListSubcategoryEntity[i].subcategoryName,
                expectedListSubcategoryEntity[i].subcategoryChecked
            )
            expectedSaveDb.add(map2.map(subcategoryData.name, subcategoryData.checked))
        }

        assertEquals(expectedSaveDb.size, actualSaveDb.size)
        for (i in actual.indices) {
            assertEquals(expectedSaveDb[i].title, actualSaveDb[i].title)
        }
    }

    /*
    @Test
    fun insertListSubcategories_And_GetList_Is_Cache() = runBlocking {

        val actualEmpty = repository.getListSubcategories(CATEGORY_BY_GLASSES)


        val expectedListSubcategoryEntity =
            listOf<SubcategoryEntity>(
                SubcategoryEntity(
                    idCategory = 1,
                    subcategoryName = "Stakan2",
                    subcategoryChecked = 0
                )
            )
        cacheDataSource.changeExpectedListSubcategoryEntity(expectedListSubcategoryEntity)

        val expected = mutableListOf<SubcategoryDomain>()
        for (i in expectedListSubcategoryEntity.indices){
            val subcategoryEntity =  expectedListSubcategoryEntity[i]
            val subcategoryData = map4.map(idCategory = subcategoryEntity.idCategory, subcategoryName = subcategoryEntity.subcategoryName, subcategoryChecked = subcategoryEntity.subcategoryChecked)
            expected.add( map2.map(subcategoryData.name, subcategoryData.checked))
        }

        val actual = repository.getListSubcategories(CATEGORY_BY_GLASSES)

        assertEquals(expected, actual)
    }
*/


}