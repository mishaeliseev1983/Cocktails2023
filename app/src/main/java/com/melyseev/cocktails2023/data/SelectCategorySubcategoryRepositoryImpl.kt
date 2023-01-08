package com.melyseev.cocktails2023.data

import com.melyseev.cocktails2023.domain.SelectCategorySubcategoryRepository
import javax.inject.Inject

class SelectCategorySubcategoryRepositoryImpl
    @Inject constructor(val sharedPreferences: SharedPreferencesData): SelectCategorySubcategoryRepository {


    override fun changeCategory(category: String) {
       sharedPreferences.changeCategory(category)
    }
    override fun getCategory(): String  {
        val category = sharedPreferences.getCategory()
        println(category)
        return category
    }

    override fun changeSubcategory(subcategory: String) {
        sharedPreferences.changeSubcategory(subcategory)
    }
    override fun getSubcategory(): String = sharedPreferences.getSubcategory()

}