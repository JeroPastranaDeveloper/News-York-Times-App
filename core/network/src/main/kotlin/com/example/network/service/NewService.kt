package com.example.network.service

import com.example.core.network.BuildConfig
import com.example.network.model.ApiNewDetailResponse
import com.example.network.model.ApiNewsResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewService {
    @GET("svc/mostpopular/v2/viewed/7.json")
    suspend fun fetchNewList(
        @Query("api-key") apiKey: String = BuildConfig.API_KEY
    ): ApiResponse<ApiNewsResponse>

    @GET("svc/search/v2/articlesearch.json")
    suspend fun fetchNewDetail(
        @Query("fq", encoded = true) url: String,
        @Query("api-key") apiKey: String = BuildConfig.API_KEY
    ): ApiResponse<ApiNewDetailResponse>
}
