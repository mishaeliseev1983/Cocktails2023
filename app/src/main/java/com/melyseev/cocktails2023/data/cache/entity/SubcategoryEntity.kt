package com.melyseev.cocktails2023.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = SubcategoryEntity.TABLE_SUBCATEGORY)
data class SubcategoryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = ID_CATEGORY) val idCategory: Int,
    @ColumnInfo(name = SUBCATEGORY_NAME) var subcategoryName: String,
    @ColumnInfo(name = SUBCATEGORY_CHECKED) var subcategoryChecked: Int,
){
    companion object {
        const val TABLE_SUBCATEGORY= "TABLE_SUBCATEGORY"
        const val ID_CATEGORY = "ID_CATEGORY"
        const val SUBCATEGORY_NAME = "SUBCATEGORY_NAME"
        const val SUBCATEGORY_CHECKED = "SUBCATEGORY_CHECKED"
    }

    interface Mapper<T>{
        fun map(idCategory: Int, subcategoryName: String, subcategoryChecked: Int): T
    }
    fun <T> mapToData(mapper: Mapper<T>) = mapper.map(idCategory, subcategoryName, subcategoryChecked)
}