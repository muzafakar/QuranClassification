package com.muzafakar.alquran.util

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.muzafakar.alquran.database.AppDatabase
import timber.log.Timber
import java.io.*


/**
 * Created by muzafakar at 25/03/20
 */
class DatabaseCopier(context: Context) {
    private val appDatabase: AppDatabase
    private val databaseName = "quran.db"

    init {
        copyAttachedDatabase(context)
        appDatabase =
            Room.databaseBuilder<AppDatabase>(context, AppDatabase::class.java, databaseName)
                .addMigrations(AppDatabase.MIGRATION_1_2)
                .build()
    }

    fun getRoomDatabase(): AppDatabase {
        return appDatabase
    }

    private fun copyAttachedDatabase(context: Context?) {
        val databaseName = databaseName
        val dbPath: File = context!!.getDatabasePath(databaseName)
        // If the database already exists, return
        if (dbPath.exists()) {
            return
        }
        // Make sure we have a path to the file
        dbPath.parentFile?.mkdirs()
        // Try to copy database file
        try {
            val inputStream: InputStream = context.assets.open("databases/$databaseName")
            val output: OutputStream = FileOutputStream(dbPath)
            val buffer = ByteArray(8192)
            var length: Int
            while (inputStream.read(buffer, 0, 8192).also { length = it } > 0) {
                output.write(buffer, 0, length)
            }
            output.flush()
            output.close()
            inputStream.close()
        } catch (e: IOException) {
            Timber.e("Failed to open file $e")
            e.printStackTrace()
        }
    }

}