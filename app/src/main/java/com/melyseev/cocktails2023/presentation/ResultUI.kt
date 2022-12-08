package com.melyseev.cocktails2023.presentation

sealed class ResultUI{
    data class Success(val list: List<SubcategoryUI>): ResultUI()
    data class Failure(val message: String): ResultUI()
}