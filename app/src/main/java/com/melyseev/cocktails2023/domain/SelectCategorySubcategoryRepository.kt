package com.melyseev.cocktails2023.domain

interface SelectCategorySubcategoryRepository {
    fun changeCategory(category: String)
    fun getCategory(): String

    fun changeSubcategory(category: String)
    fun getSubcategory(): String
}