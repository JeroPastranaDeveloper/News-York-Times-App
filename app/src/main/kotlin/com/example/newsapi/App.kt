package com.example.newsapi

import android.app.Application
import com.example.data.di.dataModule
import com.example.data.di.databaseModule
import com.example.details.di.detailsViewModelModule
import com.example.home.di.homeViewModelModule
import com.example.navigation.navigationModule
import com.example.network.di.dispatchersModule
import com.example.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                navigationModule,
                dispatchersModule,
                networkModule,
                dataModule,
                databaseModule,
                homeViewModelModule,
                detailsViewModelModule
            )
        }
    }
}