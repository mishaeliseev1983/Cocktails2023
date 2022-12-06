package com.melyseev.cocktails2023.domain

interface CocktailsInteractor {
    fun fetchListSubcategory(category: String): ResultSubcategory
    //fun fetchListCocktail(subcategory: String): List<SubcategoryDomain>

    fun <T> mapToUI(mapper: Mapper<T>): T

    interface Mapper<T>{
        fun map(value: ResultSubcategory) : T
    }
    class Base: CocktailsInteractor{
        override fun fetchListSubcategory(category: String): ResultSubcategory {
            return ResultSubcategory.Success(emptyList())
        }

        override fun <T> mapToUI(mapper: Mapper<T>): T {
            return mapper.map()
        }


    }
}