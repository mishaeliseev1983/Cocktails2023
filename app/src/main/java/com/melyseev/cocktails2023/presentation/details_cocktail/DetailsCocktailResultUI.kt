package com.melyseev.cocktails2023.presentation.details_cocktail

sealed class DetailsCocktailResultUI{
    data class Success(val title: String, val image: String, val instructions: String): DetailsCocktailResultUI()
    data class Error(val message: String): DetailsCocktailResultUI()
}