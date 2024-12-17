package com.example.home

import com.example.model.New
import com.example.model.NewDetail

fun NewDetail.toNew() : New =
    New(
        title = title,
        description = snippet,
        author = author,
        articleUrl = webUrl,
        imageUrl = imageUrl
    )