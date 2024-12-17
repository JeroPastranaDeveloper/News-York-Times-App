package com.example.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewEntity(
    val title: String,
    val description: String,
    val imageUrl: String,
    val author: String,
    @PrimaryKey val articleUrl: String
)