package com.melyseev.cocktails2023.presentation.details_cocktail

sealed class CocktailFavoriteStateResultUI {
    class Success(val isFavorite: Boolean) : CocktailFavoriteStateResultUI()
    class Error(val message: String) : CocktailFavoriteStateResultUI()
}