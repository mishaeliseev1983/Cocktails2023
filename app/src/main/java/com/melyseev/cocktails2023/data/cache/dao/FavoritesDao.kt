package com.melyseev.cocktails2023.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.melyseev.cocktails2023.data.cache.entity.FavoriteEntity

@Dao
interface FavoritesDao {

    @Insert
    suspend fun insert(entity: FavoriteEntity)

    @Query("SELECT * from ${FavoriteEntity.TABLE_FAVORITE} where  `${FavoriteEntity.COCKTAIL_ID}` = :cocktailId ")
    suspend fun searchById(cocktailId: Int): FavoriteEntity?

    @Query("SELECT * from ${FavoriteEntity.TABLE_FAVORITE} where  `${FavoriteEntity.LIKE}` == 1 ")
    suspend fun getListFavorite(): List<FavoriteEntity>

    @Update
    suspend fun update(entity: FavoriteEntity)
}
