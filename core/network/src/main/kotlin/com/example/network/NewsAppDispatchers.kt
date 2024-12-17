package com.example.network

import kotlin.annotation.AnnotationRetention.RUNTIME

@Retention(RUNTIME)
annotation class Dispatcher(val newsAppDispatchers: NewsAppDispatchers)

enum class NewsAppDispatchers {
    IO
}