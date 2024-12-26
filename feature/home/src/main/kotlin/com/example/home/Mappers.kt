package com.example.home

import com.example.model.New
import com.example.model.NewDetail

fun NewDetail.toNew(): New =
    New(
        title = title,
        description = snippet,
        author = author,
        articleUrl = webUrl,
        imageUrl = imageUrl
    )

fun List<New>.fixNoImage(): List<New> =
    this.map { new ->
        new.copy(imageUrl = new.imageUrl.ifEmpty { "https://upload.wikimedia.org/wikipedia/commons/1/14/No_Image_Available.jpg" })
    }