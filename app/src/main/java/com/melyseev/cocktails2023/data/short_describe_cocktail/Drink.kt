package com.melyseev.cocktails2023.data.short_describe_cocktail

data class Drink(
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String
){
    fun <T> mapToDomain(mapper: Mapper<T>): T = mapper.map(idDrink, strDrink, strDrinkThumb)

    interface  Mapper<T>{
        fun map(idDrink: String, strDrink: String, strDrinkThumb: String): T
    }
}