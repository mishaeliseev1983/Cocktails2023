package com.melyseev.cocktails2023.data

class SubcategoryData(val name: String, var checked: Boolean){
    fun <T> mapToDomain(mapper: Mapper<T>): T = mapper.map(name, checked)
    interface Mapper<T> {
        fun map(name: String, checked: Boolean): T
    }
}