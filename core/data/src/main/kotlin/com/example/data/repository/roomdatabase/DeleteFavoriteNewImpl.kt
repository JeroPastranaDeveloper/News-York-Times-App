package com.example.data.repository.roomdatabase

import com.example.database.NewDao
import com.example.domain.repository.roomdatabase.DeleteFavoriteNew

class DeleteFavoriteNewImpl(
    private val newDao: NewDao
) : DeleteFavoriteNew {
    override suspend fun deleteFavoriteNew(articleUrl: String) {
        newDao.deleteFavoriteNew(articleUrl)
    }
}