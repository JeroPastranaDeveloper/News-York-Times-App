package com.example.model

import android.os.Parcelable
import com.example.utils.emptyString
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class NewDetail(
    val title: String = emptyString(),
    val snippet: String = emptyString(),
    val printPage: String = emptyString(),
    val sectionName: String = emptyString(),
    val abstract: String = emptyString(),
    val imageUrl: String = emptyString(),
    val source: String = emptyString(),
    val uri: String = emptyString(),
    val newsDesk: String = emptyString(),
    val pubDate: String = emptyString(),
    val wordCount: Int = 0,
    val leadParagraph: String = emptyString(),
    val typeOfMaterial: String = emptyString(),
    val webUrl: String = emptyString(),
    val printSection: String = emptyString(),
    val id: String = emptyString(),
    val subsectionName: String = emptyString(),
    val author: String = emptyString(),
    val documentType: String = emptyString(),
    val isFavorite: Boolean = false
) : Parcelable
