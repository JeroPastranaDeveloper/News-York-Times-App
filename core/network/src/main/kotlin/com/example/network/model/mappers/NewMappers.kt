package com.example.network.model.mappers

import com.example.model.ArticleMedia
import com.example.model.ArticleMediaMetadata
import com.example.model.New
import com.example.model.NewDetail
import com.example.network.model.ApiMedia
import com.example.network.model.ApiMediaMetadata
import com.example.network.model.ApiNew
import com.example.network.model.ApiNewDetail
import com.example.utils.orZero
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
fun ApiNew.toNew(): New =
    New(
        title = title.orEmpty(),
        articleUrl = articleUrl.orEmpty(),
        author = author.orEmpty(),
        description = description.orEmpty(),
        imageUrl = media?.toMedia()?.firstOrNull()?.getLargestImage().orEmpty()
    )

@OptIn(InternalSerializationApi::class)
fun ApiNewDetail.toNewDetail(imageUrl: String): NewDetail =
    NewDetail(
        snippet = this.snippet.orEmpty(),
        printPage = this.printPage.orEmpty(),
        sectionName = this.sectionName.orEmpty(),
        abstract = this.abstract.orEmpty(),
        imageUrl = imageUrl,
        source = this.source.orEmpty(),
        uri = this.uri.orEmpty(),
        newsDesk = this.newsDesk.orEmpty(),
        pubDate = this.pubDate.orEmpty(),
        wordCount = this.wordCount.orZero(),
        leadParagraph = this.leadParagraph.orEmpty(),
        typeOfMaterial = this.typeOfMaterial.orEmpty(),
        webUrl = this.webUrl.orEmpty(),
        printSection = this.printSection.orEmpty(),
        id = this.id.orEmpty(),
        subsectionName = this.subsectionName.orEmpty(),
        author = this.buildAuthor(),
        documentType = this.documentType.orEmpty(),
    )

@OptIn(InternalSerializationApi::class)
fun ApiNewDetail.buildAuthor(): String =
    this.byline?.person
        ?.takeIf { it.isNotEmpty() }
        ?.let { "${it[0].firstName.orEmpty()} ${it[0].lastName.orEmpty()}" }
        .orEmpty()

@OptIn(InternalSerializationApi::class)
fun List<ApiMedia>.toMedia(): List<ArticleMedia> =
    this.map {
        ArticleMedia(
            type = it.type.orEmpty(),
            subtype = it.subtype.orEmpty(),
            mediaMetadata = it.mediaMetadata?.toArticleMediaMetadata().orEmpty()
        )
    }

@OptIn(InternalSerializationApi::class)
fun List<ApiMediaMetadata>.toArticleMediaMetadata() =
    this.map {
        ArticleMediaMetadata(
            url = it.url.orEmpty(),
            format = it.format.orEmpty(),
            height = it.height.orZero(),
            width = it.width.orZero()
        )
    }
