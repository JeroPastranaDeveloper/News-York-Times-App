package com.example.domain.repository.roomdatabase

import com.example.model.NewDetail

interface SaveNewRepository {
    suspend fun saveNew(new: NewDetail)
}