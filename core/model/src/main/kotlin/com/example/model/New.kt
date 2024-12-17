package com.example.model

import android.os.Parcelable
import com.example.utils.emptyString
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class New(
    val title: String = emptyString(),
    val description: String = emptyString(),
    val author: String = emptyString(),
    val articleUrl: String = emptyString(),
    val imageUrl: String = emptyString()
) : Parcelable

@Parcelize
@Serializable
data class ArticleMedia(
    val type: String = emptyString(),
    val subtype: String = emptyString(),
    val mediaMetadata: List<ArticleMediaMetadata> = emptyList()
) : Parcelable {
    fun getLargestImage(): String? =
        mediaMetadata.maxByOrNull { it.height * it.width }?.url
}

@Parcelize
@Serializable
data class ArticleMediaMetadata(
    val url: String = emptyString(),
    val format: String = emptyString(),
    val height: Int = 0,
    val width: Int = 0
) : Parcelable