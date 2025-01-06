package com.example.data.repository.details

import androidx.annotation.WorkerThread
import com.example.database.NewDao
import com.example.domain.repository.details.DetailsRepository
import com.example.model.NewDetail
import com.example.network.model.mappers.toNewDetail
import com.example.network.service.NewClient
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DetailsRepositoryImpl(
    private val newClient: NewClient,
    private val newDao: NewDao
) : DetailsRepository {

    @WorkerThread
    override fun fetchNewDetail(url: String, imageUrl: String): Flow<NewDetail> = flow {
        retryWithDelay {
            newClient.fetchNewDetail(url).suspendOnSuccess {
                val newDetail = data.response?.docs?.firstOrNull()?.toNewDetail(imageUrl)
                val isFavoriteNew = newDao.getNewByUrl(url) != null
                emit(newDetail?.copy(isFavorite = isFavoriteNew) ?: NewDetail())
            }
        }
    }

    private suspend fun <T> retryWithDelay(
        times: Int = 3,
        initialDelay: Long = 1000,
        factor: Double = 2.0,
        block: suspend () -> T
    ): T {
        var currentDelay = initialDelay
        repeat(times - 1) {
            try {
                return block()
            } catch (e: Exception) {
                delay(currentDelay)
                currentDelay = (currentDelay * factor).toLong()
            }
        }
        return block()
    }
}
