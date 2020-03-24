package com.muzafakar.alquran.di

import android.content.Context
import com.muzafakar.alquran.util.DatabaseCopier
import com.muzafakar.alquran.viewmodel.QuranViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by muzafakar at 25/03/20
 */
val appModule = module {
    // Database
    single { getDatabase(get()) }
    single { getDatabase(get()).topicDao() }
    single { getDatabase(get()).verseDao() }

    //viewmodel
    viewModel { QuranViewModel(get(), get()) }

}

fun getDatabase(context: Context) = DatabaseCopier(context).getRoomDatabase()