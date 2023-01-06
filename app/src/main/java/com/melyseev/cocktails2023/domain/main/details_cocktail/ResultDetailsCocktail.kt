package com.melyseev.cocktails2023.domain.main.details_cocktail

sealed class ResultDetailsCocktail {

    abstract fun <T> map(mapper: Mapper<T>): T
    interface Mapper<T>{
        fun mapToUI(detailsCocktailDomain: DetailsCocktailDomain, message: String): T
    }

    data class Success(val detailsCocktailDomain: DetailsCocktailDomain) : ResultDetailsCocktail(){
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.mapToUI(detailsCocktailDomain, "")
        }
    }

    data class Error(val message: String) : ResultDetailsCocktail(){
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.mapToUI(detailsCocktailDomain = DetailsCocktailDomain.Empty(), message)
        }

    }
}