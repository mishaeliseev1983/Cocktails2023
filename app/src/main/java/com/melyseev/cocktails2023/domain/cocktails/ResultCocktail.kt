package com.melyseev.cocktails2023.domain.cocktails

sealed class ResultCocktail {
    data class Success(val listCocktails: List<CocktailDomain>) : ResultCocktail()
    data class Error(val message: String) : ResultCocktail()
}