package com.example.network.service

import com.example.core.network.BuildConfig
import com.example.network.model.ApiNewDetailResponse
import com.example.network.model.ApiNewsResponse
import com.skydoves.sandwich.ApiResponse
import kotlinx.serialization.InternalSerializationApi
import okhttp3.HttpUrl
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(InternalSerializationApi::class)
class NewClient(private val newService: NewService) : NewService {

    override suspend fun fetchNewList(apiKey: String): ApiResponse<ApiNewsResponse> =
        newService.fetchNewList()

    override suspend fun fetchNewDetail(url: String): ApiResponse<ApiNewDetailResponse> {
        val query = "web_url:(\"$url\")"

        val httpUrl = HttpUrl.Builder()
            .scheme("https")
            .host("api.nytimes.com")
            .addPathSegments("svc/search/v2/articlesearch.json")
            // 👇 EncodedQuery is used to construct a keyless query, as required by the NYT API.
            .encodedQuery(URLEncoder.encode(query, StandardCharsets.UTF_8.toString()))
            .addQueryParameter("api-key", BuildConfig.API_KEY)
            .build()

        return newService.fetchNewDetail(httpUrl.toString())
    }
}
