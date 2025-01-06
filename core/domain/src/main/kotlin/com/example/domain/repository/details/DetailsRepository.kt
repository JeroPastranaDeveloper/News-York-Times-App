package com.example.domain.repository.details

import com.example.model.NewDetail
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {
    fun fetchNewDetail(url: String, imageUrl: String): Flow<NewDetail>
}