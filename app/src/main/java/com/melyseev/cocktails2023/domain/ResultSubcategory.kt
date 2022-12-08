package com.melyseev.cocktails2023.domain

sealed class ResultSubcategory {
    abstract fun <T> map(mapper: Mapper<T> ): T
    interface  Mapper<T> {
        fun mapToUi(listSubcategoryDomain: List<SubcategoryDomain>, message: String): T
    }
    data class Success(val listSubcategory: List<SubcategoryDomain>): ResultSubcategory(){
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.mapToUi(listSubcategory, "")
        }
    }
    data class Error(val message: String): ResultSubcategory(){
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.mapToUi(emptyList(), message)
        }

    }
}