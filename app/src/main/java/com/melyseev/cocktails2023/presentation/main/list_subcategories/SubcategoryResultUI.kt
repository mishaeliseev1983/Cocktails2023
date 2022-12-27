package com.melyseev.cocktails2023.presentation.main.list_subcategories

sealed class SubcategoryResultUI{
    data class Success(val list: List<SubcategoryUI>): SubcategoryResultUI()
    data class Failure(val message: String): SubcategoryResultUI()
}