package com.melyseev.cocktails2023.presentation.select_subcategory_ui_objects

open class SubcategoryUI(val title: String, var isSelected: Boolean = false)
object SubcategoryUIEmpty: SubcategoryUI("", false)
