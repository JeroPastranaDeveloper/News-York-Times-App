package com.example.network.service

import com.example.network.model.ApiNewDetailResponse
import com.example.network.model.ApiNewsResponse
import com.skydoves.sandwich.ApiResponse

class NewClient(private val newService: NewService) : NewService {
    override suspend fun fetchNewList(apiKey: String): ApiResponse<ApiNewsResponse> =
        newService.fetchNewList()

    override suspend fun fetchNewDetail(url: String, apiKey: String): ApiResponse<ApiNewDetailResponse> {
        val fq = "web_url:(\"$url\")"
        return newService.fetchNewDetail(fq)
    }
}
