package com.melyseev.cocktails2023.data.short_cocktail

data class ShortDto(
    val drinks: List<Drink>){

    interface Mapper<T> {
        fun mapToDomain(drinks: List<Drink>): T
    }
    fun <T> map(mapper: Mapper<T>) =   mapper.mapToDomain(drinks)
}