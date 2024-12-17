package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.entity.NewEntity

@Database(
    entities = [NewEntity::class],
    version = 1,
    exportSchema = true
)

abstract class NewDatabase : RoomDatabase() {
    abstract fun newDao(): NewDao
}
