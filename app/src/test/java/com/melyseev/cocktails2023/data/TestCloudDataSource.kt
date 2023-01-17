package com.melyseev.cocktails2023.data

import com.melyseev.cocktails2023.data.cloud.CloudDataSource
import com.melyseev.cocktails2023.data.details_cocktail.DetailsCocktailDto
import com.melyseev.cocktails2023.data.list_category.subcategory_alcoholic.ListAlcoholicDto
import com.melyseev.cocktails2023.data.list_category.subcategory_category.ListCategoryDto
import com.melyseev.cocktails2023.data.list_category.subcategory_glass.ListGlassDto
import com.melyseev.cocktails2023.data.list_category.subcategory_ingredient.ListIngredientDto
import com.melyseev.cocktails2023.data.short_cocktail.ShortDto
import java.net.UnknownHostException

class TestCloudDataSource : CloudDataSource {
    lateinit var detailsCocktailDto: DetailsCocktailDto
    var noInternetConnection = false


    lateinit var expectedShortDto: ShortDto

    fun changeExpectedShortDto(value: ShortDto) {
        expectedShortDto = value
    }

    fun changeExpectedDetailsDto(value: DetailsCocktailDto) {
        detailsCocktailDto = value
    }

    fun changeNoInternetConnection(value: Boolean) {
        noInternetConnection = value
    }

    lateinit var expectListSubcategoriesByCategory: ListCategoryDto
    fun changeExpectListSubcategoriesByCategory(list: ListCategoryDto){
        expectListSubcategoriesByCategory = list
    }

    lateinit var expectListSubcategoriesByGlass: ListGlassDto
    fun changeExpectListSubcategoriesByGlass(list: ListGlassDto){
        expectListSubcategoriesByGlass = list
    }

    lateinit var expectListSubcategoriesByIngredient: ListIngredientDto
    fun changeExpectListSubcategoriesByIngredient(list: ListIngredientDto){
        expectListSubcategoriesByIngredient = list
    }

    lateinit var expectListSubcategoriesByAlcoholic: ListAlcoholicDto
    fun changeExpectListSubcategoriesByAlcoholic(list: ListAlcoholicDto){
        expectListSubcategoriesByAlcoholic = list
    }

    override suspend fun getCocktailsBySubcategory(
        category: String, subcategory: String
    ): ShortDto {
        if (noInternetConnection) throw UnknownHostException()
        return expectedShortDto
    }

    override suspend fun getSubcategoriesByCategory(): ListCategoryDto {
        if (noInternetConnection) throw UnknownHostException()
        return expectListSubcategoriesByCategory
    }

    override suspend fun getSubcategoriesByGlass(): ListGlassDto {
        if (noInternetConnection) throw UnknownHostException()
        return expectListSubcategoriesByGlass
    }

    override suspend fun getSubcategoriesByIngredient(): ListIngredientDto {
        if (noInternetConnection) throw UnknownHostException()
        return expectListSubcategoriesByIngredient
    }

    override suspend fun getSubcategoriesByAlcoholic(): ListAlcoholicDto {
        if (noInternetConnection) throw UnknownHostException()
        return expectListSubcategoriesByAlcoholic
    }

    override suspend fun getDetailsCocktailById(cocktailId: Int): DetailsCocktailDto {
        if (noInternetConnection) throw UnknownHostException()
        return detailsCocktailDto
    }

}