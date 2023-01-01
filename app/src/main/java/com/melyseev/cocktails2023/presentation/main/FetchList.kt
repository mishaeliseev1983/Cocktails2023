package com.melyseev.cocktails2023.presentation.main

interface FetchList {
    suspend fun fetchListSubcategory()
    suspend fun fetchListCocktails()
    fun fetchCategoryName()
}