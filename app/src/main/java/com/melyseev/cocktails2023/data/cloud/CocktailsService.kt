package com.melyseev.cocktails2023.data.cloud

import com.melyseev.cocktails2023.common.BEGIN_A
import com.melyseev.cocktails2023.common.BEGIN_C
import com.melyseev.cocktails2023.common.BEGIN_G
import com.melyseev.cocktails2023.common.BEGIN_I
import com.melyseev.cocktails2023.data.list_category.subcategory_alcoholic.ListAlcoholicDto
import com.melyseev.cocktails2023.data.list_category.subcategory_category.ListCategoryDto
import com.melyseev.cocktails2023.data.list_category.subcategory_glass.ListGlassDto
import com.melyseev.cocktails2023.data.list_category.subcategory_ingredient.ListIngredientDto
import com.melyseev.cocktails2023.data.short_describe_cocktail.ShortDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailsService {

    /*
    https://www.thecocktaildb.com/api/json/v1/1/filter.php?a=Non_alcoholic
     */
    @GET("filter.php")
    suspend fun getCocktailListByAlcoholic(
        @Query(BEGIN_A) subcategory: String
    ): ShortDto

    @GET("filter.php")
    suspend fun getCocktailListByGlass(
        @Query(BEGIN_G)  subcategory: String
    ): ShortDto

    @GET("filter.php")
    suspend fun getCocktailListByCategory(
        @Query(BEGIN_C) subcategory: String
    ): ShortDto

    @GET("filter.php")
    suspend fun getCocktailListByIngredient(
        @Query(BEGIN_I)  subcategory: String
    ): ShortDto


    //get subcategories
    @GET("list.php")
    suspend fun getSubcategoriesByCategory(
        @Query(BEGIN_C) list: String = "list"
    ): ListCategoryDto

    @GET("list.php")
    suspend fun getSubcategoriesByGlass(
        @Query(BEGIN_G) list: String = "list"
    ): ListGlassDto

    @GET("list.php")
    suspend fun getSubcategoriesByIngredient(
        @Query(BEGIN_I) list: String = "list"
    ): ListIngredientDto

    @GET("list.php")
    suspend fun getSubcategoriesByAlcoholic(
        @Query(BEGIN_A) list: String = "list"
    ): ListAlcoholicDto

}