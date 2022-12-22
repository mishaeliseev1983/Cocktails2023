package com.melyseev.cocktails2023.data.list_category

open class MapDrinkToDomain(val str: String) {
    fun <T> mapToDomain(mapper: Mapper<T>): T = mapper.map(str)
    interface Mapper<T> {
        fun map(category: String): T
    }
}