package com.example.details.di

import com.example.details.NewDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsViewModelModule = module {
    viewModel { NewDetailViewModel(get(), get(), get(), get()) }
}