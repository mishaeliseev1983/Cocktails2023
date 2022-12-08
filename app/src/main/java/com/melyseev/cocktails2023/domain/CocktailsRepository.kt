package com.melyseev.cocktails2023.domain

interface CocktailsRepository {
    suspend fun fetchListSubcategory(category: String): List<SubcategoryDomain>
}