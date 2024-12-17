package com.example.domain.repository.home

import com.example.model.New
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun fetchNewsList(): Flow<List<New>>
}