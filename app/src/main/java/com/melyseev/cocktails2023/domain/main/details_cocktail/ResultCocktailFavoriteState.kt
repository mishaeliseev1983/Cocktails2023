package com.melyseev.cocktails2023.domain.main.details_cocktail

sealed class ResultCocktailFavoriteState {
    class Success(private val isFavorite: Boolean) : ResultCocktailFavoriteState() {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.mapToUI(isFavorite, "")
        }

    }

    class Error(val message: String) : ResultCocktailFavoriteState() {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.mapToUI(false, message)
        }

    }

    interface Mapper<T> {
        fun mapToUI(isFavorite: Boolean, message: String): T
    }

    abstract fun <T> map(mapper: Mapper<T>): T
}