package com.melyseev.cocktails2023.presentation.details_cocktail.ui_objects

sealed class DetailsCocktailResultUI {
    data class Success(
        val title: String,
        val image: String,
        val instructions: String,
        val ingredients: List<IngredientCocktailUI>
    ) : DetailsCocktailResultUI()

    data class Error(val message: String) : DetailsCocktailResultUI()
}