package com.melyseev.cocktails2023.domain.main

import com.melyseev.cocktails2023.domain.main.details_cocktail.DetailsCocktailDomain
import com.melyseev.cocktails2023.domain.main.short_cocktail.CocktailShortDomain
import com.melyseev.cocktails2023.domain.main.subcategories.SubcategoryDomain

interface CocktailsRepository {
    suspend fun fetchListSubcategories(category: String): List<SubcategoryDomain>
    suspend fun fetchListCocktails(category: String, subcategory: String): List<CocktailShortDomain>
    suspend fun updateSelectSubcategory(category: String, subcategory: String, isSelected: Boolean)
    suspend fun getDetailsCocktailById(cocktailId: Int): DetailsCocktailDomain
}