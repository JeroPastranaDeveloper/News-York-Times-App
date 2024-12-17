package com.example.domain.repository.details

import com.example.model.NewDetail
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {
    fun fetchNewsList(url: String, imageUrl: String): Flow<NewDetail>
}