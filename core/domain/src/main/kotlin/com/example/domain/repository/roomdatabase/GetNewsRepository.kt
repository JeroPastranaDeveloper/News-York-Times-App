package com.example.domain.repository.roomdatabase

import com.example.model.NewDetail

interface GetNewsRepository {
    suspend fun getNews() : List<NewDetail>
}