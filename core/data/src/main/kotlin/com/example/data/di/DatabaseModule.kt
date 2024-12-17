package com.example.data.di

import androidx.room.Room
import com.example.data.repository.roomdatabase.DeleteFavoriteNewImpl
import com.example.data.repository.roomdatabase.GetNewsRepositoryImpl
import com.example.data.repository.roomdatabase.SaveNewRepositoryImpl
import com.example.database.NewDatabase
import com.example.domain.repository.roomdatabase.DeleteFavoriteNew
import com.example.domain.repository.roomdatabase.GetNewsRepository
import com.example.domain.repository.roomdatabase.SaveNewRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            NewDatabase::class.java,
            "New.db"
        ).fallbackToDestructiveMigration().build()
    }

    single { get<NewDatabase>().newDao() }

    single<SaveNewRepository> {
        SaveNewRepositoryImpl(
            get()
        )
    }

    single<GetNewsRepository> {
        GetNewsRepositoryImpl(
            get()
        )
    }

    single<DeleteFavoriteNew> {
        DeleteFavoriteNewImpl(get())
    }
}
