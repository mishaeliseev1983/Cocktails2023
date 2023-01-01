package com.melyseev.cocktails2023.presentation

sealed class SubcategoryResultUI{
    data class Success(val list: List<SubcategoryUI>): SubcategoryResultUI()
    data class Failure(val message: String): SubcategoryResultUI()
}