package com.example.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiNewsResponse(
    @SerialName("status") val status: String? = null,
    @SerialName("num_results") val totalResults: Int? = null,
    @SerialName("results") val articles: List<ApiNew> = emptyList()
)

@Serializable
data class ApiNewDetailResponse(
    @SerialName("response") val response: ApiDocs? = null
)

@Serializable
data class ApiDocs(
    @SerialName("docs") val docs: List<ApiNewDetail>? = null
)

@Serializable
data class ApiNew(
    @SerialName("title") val title: String? = null,
    @SerialName("abstract") val description: String? = null,
    @SerialName("url") val articleUrl: String? = null,
    @SerialName("byline") val author: String? = null,
    @SerialName("media") val media: List<ApiMedia>? = null
)

@Serializable
data class ApiNewDetail(
    @SerialName("snippet") val snippet: String? = null,
    @SerialName("print_page") val printPage: String? = null,
    @SerialName("section_name") val sectionName: String? = null,
    @SerialName("abstract") val abstract: String? = null,
    @SerialName("source") val source: String? = null,
    @SerialName("uri") val uri: String? = null,
    @SerialName("news_desk") val newsDesk: String? = null,
    @SerialName("pub_date") val pubDate: String? = null,
    @SerialName("word_count") val wordCount: Int? = null,
    @SerialName("lead_paragraph") val leadParagraph: String? = null,
    @SerialName("type_of_material") val typeOfMaterial: String? = null,
    @SerialName("web_url") val webUrl: String? = null,
    @SerialName("print_section") val printSection: String? = null,
    @SerialName("id") val id: String? = null,
    @SerialName("subsection_name") val subsectionName: String? = null,
    @SerialName("byline") val byline: ApiByLine? = null,
    @SerialName("document_type") val documentType: String? = null
)

@Serializable
data class ApiByLine(
    @SerialName("original") val original: String? = null,
    @SerialName("person") val person: List<ApiPerson>? = null
)

@Serializable
data class ApiPerson(
    @SerialName("firstname") val firstName: String? = null,
    @SerialName("middlename") val middleName: String? = null,
    @SerialName("lastname") val lastName: String? = null,
    @SerialName("role") val role: String? = null
)

@Serializable
data class ApiMedia(
    @SerialName("type") val type: String? = null,
    @SerialName("subtype") val subtype: String? = null,
    @SerialName("media-metadata") val mediaMetadata: List<ApiMediaMetadata>? = null
)

@Serializable
data class ApiMediaMetadata(
    @SerialName("url") val url: String? = null,
    @SerialName("format") val format: String? = null,
    @SerialName("height") val height: Int? = null,
    @SerialName("width") val width: Int? = null
)
