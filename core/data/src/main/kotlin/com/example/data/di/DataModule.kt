package com.example.data.di

import com.example.domain.repository.details.DetailsRepository
import com.example.data.repository.details.DetailsRepositoryImpl
import com.example.domain.repository.home.HomeRepository
import com.example.data.repository.home.HomeRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<HomeRepository> { HomeRepositoryImpl(get()) }
    single<DetailsRepository> { DetailsRepositoryImpl(get(), get()) }
}