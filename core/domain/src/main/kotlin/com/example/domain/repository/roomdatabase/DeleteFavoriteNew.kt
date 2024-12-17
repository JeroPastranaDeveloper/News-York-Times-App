package com.example.domain.repository.roomdatabase

interface DeleteFavoriteNew {
    suspend fun deleteFavoriteNew(articleUrl: String)
}