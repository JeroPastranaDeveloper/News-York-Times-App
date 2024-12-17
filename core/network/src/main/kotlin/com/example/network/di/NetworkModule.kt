package com.example.network.di

import com.example.network.service.NewClient
import com.example.network.service.NewService
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val networkModule = module {
    single {
        Json {
            ignoreUnknownKeys = true
        }
    }

    single {
        OkHttpClient.Builder().build()
    }

    single {
        val json: Json = get()
        val okHttpClient: OkHttpClient = get()

        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.nytimes.com/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
    }

    single {
        val retrofit: Retrofit = get()
        retrofit.create(NewService::class.java)
    }

    single {
        val newService: NewService = get()
        NewClient(newService)
    }
}
