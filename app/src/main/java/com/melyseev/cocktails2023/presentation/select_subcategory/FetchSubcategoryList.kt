package com.melyseev.cocktails2023.presentation.select_subcategory

interface FetchSubcategoryList {
    fun fetchListSubcategory()
    fun updateSelectSubcategory(subcategory: String, isSelect: Boolean)
}