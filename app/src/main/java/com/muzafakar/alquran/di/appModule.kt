package com.muzafakar.alquran.di

import android.content.Context
import androidx.room.Room
import com.muzafakar.alquran.database.AppDatabase
import com.muzafakar.alquran.util.PrefManager
import com.muzafakar.alquran.viewmodel.QuranViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by muzafakar at 25/03/20
 */
val appModule = module {
    // Prefmanager
    single { PrefManager(get()) }

    // Database
    single { getDatabase(get()) }
    single { getDatabase(get()).topicDao() }
    single { getDatabase(get()).verseDao() }

    //viewmodel
    viewModel { QuranViewModel(get(), get()) }

}

//fun getDatabase(context: Context) = DatabaseCopier(context).getRoomDatabase()

fun getDatabase(context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, "quran.db")
        .createFromAsset("database/main.db")
        .fallbackToDestructiveMigration()
        .build()
