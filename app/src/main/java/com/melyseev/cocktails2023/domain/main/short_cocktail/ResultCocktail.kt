package com.melyseev.cocktails2023.domain.main.short_cocktail

sealed class ResultCocktail {

    abstract fun <T> map(mapper: Mapper<T>): T
    interface Mapper<T> {
        fun mapToUi(cocktailShortDomain: List<CocktailShortDomain>, message: String): T
    }

    data class Success(val listCocktails: List<CocktailShortDomain>) : ResultCocktail() {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.mapToUi(listCocktails, "")
        }
    }

    data class Error(val message: String) : ResultCocktail() {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.mapToUi(emptyList(), message)
        }
    }
}