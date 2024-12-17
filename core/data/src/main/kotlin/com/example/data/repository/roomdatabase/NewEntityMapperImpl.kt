package com.example.data.repository.roomdatabase

import com.example.database.entity.NewEntity
import com.example.database.entity.mapper.NewEntityMapper
import com.example.model.NewDetail

object NewEntityMapperImpl : NewEntityMapper<NewDetail, NewEntity> {
    override fun asEntity(domain: NewDetail): NewEntity = NewEntity(
        title = domain.title,
        description = domain.snippet,
        imageUrl = domain.imageUrl,
        author = domain.author,
        articleUrl = domain.webUrl
    )

    override fun asDomain(entity: NewEntity): NewDetail {
        return NewDetail(
            title = entity.title,
            snippet = entity.description,
            imageUrl = entity.imageUrl,
            author = entity.author,
            webUrl = entity.articleUrl
        )
    }
}

fun NewDetail.asEntity(): NewEntity = NewEntityMapperImpl.asEntity(this)
fun NewEntity.asNewDetail(): NewDetail = NewEntityMapperImpl.asDomain(this)
