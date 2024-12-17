package com.example.data.repository.roomdatabase

import com.example.database.NewDao
import com.example.domain.repository.roomdatabase.SaveNewRepository
import com.example.model.NewDetail

class SaveNewRepositoryImpl(
    private val newDao: NewDao
) : SaveNewRepository {
    override suspend fun saveNew(new: NewDetail) {
        newDao.insertNew(new.asEntity())
    }
}
