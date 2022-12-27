package com.melyseev.cocktails2023.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.melyseev.cocktails2023.common.CATEGORY
import com.melyseev.cocktails2023.common.CATEGORY_DEFAULT
import com.melyseev.cocktails2023.common.SUBCATEGORY
import com.melyseev.cocktails2023.common.SUBCATEGORY_DEFAULT

class SharedPreferencesData(private val data: SharedPreferences) {

    fun getCategory(): String {
        return data.getString(CATEGORY, CATEGORY_DEFAULT) ?: CATEGORY_DEFAULT
    }

    fun getSubcategory(): String {
        return data.getString(SUBCATEGORY, SUBCATEGORY_DEFAULT) ?: SUBCATEGORY_DEFAULT
    }

    fun changeCategory(newCategory: String) {
        data.edit {
            putString(CATEGORY, newCategory)
        }
    }

    fun changeSubcategory(newSubcategory: String) {
        data.edit {
            putString(SUBCATEGORY, newSubcategory)
        }
    }
}