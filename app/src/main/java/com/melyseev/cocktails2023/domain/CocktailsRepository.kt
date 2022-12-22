package com.melyseev.cocktails2023.domain

import com.melyseev.cocktails2023.domain.cocktails.CocktailDomain
import com.melyseev.cocktails2023.domain.subcategories.SubcategoryDomain

interface CocktailsRepository {
    suspend fun fetchListSubcategory(category: String): List<SubcategoryDomain>
    suspend fun fetchListCocktails(category: String, subcategory: String): List<CocktailDomain>
}