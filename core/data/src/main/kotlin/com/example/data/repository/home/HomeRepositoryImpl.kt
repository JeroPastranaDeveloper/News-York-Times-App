package com.example.data.repository.home

import androidx.annotation.WorkerThread
import com.example.model.New
import com.example.network.model.mappers.toNew
import com.example.network.service.NewClient
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.InternalSerializationApi

class HomeRepositoryImpl(
    private val newClient: NewClient
) : com.example.domain.repository.home.HomeRepository {
    @OptIn(InternalSerializationApi::class)
    @WorkerThread
    override fun fetchNewsList(): Flow<List<New>> {
        return flow {
            newClient.fetchNewList().suspendOnSuccess {
                val newsList = data.articles.map {
                    it.toNew()
                }
                emit(newsList)
            }
        }
    }
}