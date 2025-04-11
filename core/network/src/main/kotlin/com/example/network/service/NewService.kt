package com.example.network.service

import com.example.core.network.BuildConfig
import com.example.network.model.ApiNewDetailResponse
import com.example.network.model.ApiNewsResponse
import com.skydoves.sandwich.ApiResponse
import kotlinx.serialization.InternalSerializationApi
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

@OptIn(InternalSerializationApi::class)
interface NewService {
    @GET("svc/mostpopular/v2/viewed/7.json")
    suspend fun fetchNewList(
        @Query("api-key") apiKey: String = BuildConfig.API_KEY
    ): ApiResponse<ApiNewsResponse>

    @GET
    suspend fun fetchNewDetail(
        @Url url: String
    ): ApiResponse<ApiNewDetailResponse>
}
