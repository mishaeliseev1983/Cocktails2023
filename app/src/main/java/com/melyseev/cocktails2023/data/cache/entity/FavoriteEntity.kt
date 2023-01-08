package com.melyseev.cocktails2023.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = FavoriteEntity.TABLE_FAVORITE)
open class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = COCKTAIL_ID)   var cocktailId: Int,
    @ColumnInfo(name = COCKTAIL_TITLE)   var cocktailTitle: String,
    @ColumnInfo(name = COCKTAIL_IMAGE)   var cocktailImage: String,
    @ColumnInfo(name = LIKE)   var like: Int = 0,
){
    companion object {
        const val TABLE_FAVORITE = "TABLE_FAVORITE"
        const val COCKTAIL_ID = "COCKTAIL_ID"
        const val LIKE = "LIKE"
        const val COCKTAIL_TITLE = "COCKTAIL_TITLE"
        const val COCKTAIL_IMAGE = "COCKTAIL_IMAGE"
    }
    object Empty: FavoriteEntity(-1, -1,  "","", 0)

    interface Mapper<T>{
        fun map(cocktailId: Int, cocktailTitle: String, cocktailImage: String, like: Int) : T
    }
    fun <T> mapToDomain(mapper: Mapper<T>) = mapper.map(cocktailId, cocktailTitle, cocktailImage, like)
}

