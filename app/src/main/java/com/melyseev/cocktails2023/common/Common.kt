package com.melyseev.cocktails2023.common

const val BEGIN_G ="g"
const val BEGIN_A ="a"
const val BEGIN_I ="i"
const val BEGIN_C ="c"

const val CATEGORY_BY_ALCOHOLIC ="Alcoholic"
const val CATEGORY_BY_GLASSES ="Glasses"
const val CATEGORY_BY_INGREDIENTS ="Ingredients"
const val CATEGORY_BY_CATEGORIES ="Categories"
const val CATEGORY_BY_FAVORITE ="Favorites"

const val SUBCATEGORY_DEFAULT = "Gin"
const val CATEGORY_DEFAULT = CATEGORY_BY_INGREDIENTS

const val CATEGORY = "category"
const val SUBCATEGORY = "subcategory"

fun Boolean.toInt() = if (this) 1 else 0


