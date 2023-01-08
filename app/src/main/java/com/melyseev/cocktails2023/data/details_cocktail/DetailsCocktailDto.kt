package com.melyseev.cocktails2023.data.details_cocktail

class DetailsCocktailDto (val drinks: List<Drink>){
    interface Mapper<T> {
        fun map(drinks: List<Drink>): T
    }
    fun <T> map(mapper: Mapper<T>) = mapper.map(drinks)
}