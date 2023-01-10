package com.melyseev.cocktails2023.presentation.select_subcategory_ui_objects

sealed class SubcategoryResultUI{
    data class Success(val list: List<SubcategoryUI>): SubcategoryResultUI()
    data class Failure(val message: String): SubcategoryResultUI()
}