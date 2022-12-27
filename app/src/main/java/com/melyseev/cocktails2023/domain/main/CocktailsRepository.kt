package com.melyseev.cocktails2023.domain.main

import com.melyseev.cocktails2023.domain.main.cocktails.CocktailDomain
import com.melyseev.cocktails2023.domain.main.subcategories.SubcategoryDomain

interface CocktailsRepository {
    suspend fun fetchListSubcategory(category: String): List<SubcategoryDomain>
    suspend fun fetchListCocktails(category: String, subcategory: String): List<CocktailDomain>
}