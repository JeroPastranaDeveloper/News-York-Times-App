package com.example.data.repository.details

import com.example.model.NewDetail
import com.example.network.model.mappers.toNewDetail
import com.example.network.service.NewClient
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import androidx.annotation.WorkerThread
import com.example.database.NewDao
import com.example.domain.repository.details.DetailsRepository

class DetailsRepositoryImpl(
    private val newClient: NewClient,
    private val newDao: NewDao
) : DetailsRepository {
    @WorkerThread
    override fun fetchNewsList(url: String, imageUrl: String): Flow<NewDetail> =
        flow {
            newClient.fetchNewDetail(url).suspendOnSuccess {
                val newDetail = data.response?.docs?.map { it.toNewDetail(imageUrl) }?.first()
                val isFavoriteNew = newDao.getNewByUrl(url) != null
                emit(newDetail?.copy(isFavorite = isFavoriteNew) ?: NewDetail())
            }
        }
}
