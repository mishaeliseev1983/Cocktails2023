package com.melyseev.cocktails2023.presentation.list_cocktails

sealed class CocktailResultUI {
    data class Success(val list: List<CocktailUI>) : CocktailResultUI()
    data class Failure(val message: String) : CocktailResultUI()
}