package com.melyseev.cocktails2023.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CategoryEntity.TABLE_CATEGORY)
class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = CATEGORY_NAME) var categoryName: String,
){

    companion object {
        const val TABLE_CATEGORY = "TABLE_CATEGORY"
        const val CATEGORY_NAME = "CATEGORY_NAME"
    }
}