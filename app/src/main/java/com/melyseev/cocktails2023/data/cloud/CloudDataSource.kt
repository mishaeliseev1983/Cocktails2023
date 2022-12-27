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
import com.melyseev.cocktails2023.domain.DomainException
import javax.inject.Inject

interface CloudDataSource {

    suspend fun getCocktailsBySubcategory(category: String, subcategory: String): ShortDto
    suspend fun getSubcategoriesByCategory(): ListCategoryDto
    suspend fun getSubcategoriesByGlass(): ListGlassDto
    suspend fun getSubcategoriesByIngredient(): ListIngredientDto
    suspend fun getSubcategoriesByAlcoholic(): ListAlcoholicDto

    class Base @Inject constructor(private val service: CocktailsService) : CloudDataSource {

        override suspend fun getCocktailsBySubcategory(category: String, subcategory: String): ShortDto {
            return when (category.first().lowercase()) {
                BEGIN_C -> service.getCocktailListByCategory(subcategory = subcategory)
                BEGIN_G -> service.getCocktailListByGlass(subcategory = subcategory)
                BEGIN_A -> service.getCocktailListByAlcoholic(subcategory = subcategory)
                BEGIN_I -> service.getCocktailListByIngredient(subcategory = subcategory)
                else -> throw DomainException.UnknownErrorException
            }
        }

        override suspend fun getSubcategoriesByCategory() = service.getSubcategoriesByCategory()
        override suspend fun getSubcategoriesByGlass() = service.getSubcategoriesByGlass()
        override suspend fun getSubcategoriesByIngredient() = service.getSubcategoriesByIngredient()
        override suspend fun getSubcategoriesByAlcoholic() = service.getSubcategoriesByAlcoholic()
    }
}