package com.example.data.repository.roomdatabase

import com.example.database.NewDao
import com.example.domain.repository.roomdatabase.GetNewsRepository
import com.example.model.NewDetail

class GetNewsRepositoryImpl(
    private val newDao: NewDao
) : GetNewsRepository {
    override suspend fun getNews(): List<NewDetail> =
        newDao.getNews().map {
            it.asNewDetail()
        }
    }
