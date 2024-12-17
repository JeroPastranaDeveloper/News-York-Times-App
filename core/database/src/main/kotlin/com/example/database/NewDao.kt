package com.example.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entity.NewEntity

@Dao
interface NewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNew(newEntity: NewEntity)

    @Query("SELECT * FROM NewEntity")
    suspend fun getNews(): List<NewEntity>

    @Query("SELECT * FROM NewEntity WHERE articleUrl = :url")
    suspend fun getNewByUrl(url: String): NewEntity?

    @Query("DELETE FROM NewEntity WHERE articleUrl = :url")
    suspend fun deleteFavoriteNew(url: String)
}
