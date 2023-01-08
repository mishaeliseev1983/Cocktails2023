package com.melyseev.cocktails2023.domain

interface SelectCategorySubcategoryRepository {
    fun changeCategory(category: String)
    fun getCategory(): String

    fun changeSubcategory(subcategory: String)
    fun getSubcategory(): String
}