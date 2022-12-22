package com.melyseev.cocktails2023.data.list_category.subcategory_category

import com.melyseev.cocktails2023.data.list_category.MapDrinkToDomain

data class Drink(val strCategory: String) : MapDrinkToDomain(str = strCategory)