package com.example.navigation

import org.koin.dsl.module

val navigationModule = module {
    single<AppComposeNavigator<NewsScreen>> {
        NewsComposeNavigator()
    }
}
