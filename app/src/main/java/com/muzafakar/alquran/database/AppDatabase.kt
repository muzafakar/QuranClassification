package com.muzafakar.alquran.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.muzafakar.alquran.database.dao.TopicDao
import com.muzafakar.alquran.database.dao.VerseDao
import com.muzafakar.alquran.model.Topic
import com.muzafakar.alquran.model.Verse

/**
 * Created by muzafakar at 25/03/20
 */
@Database(
    entities = [
        Verse::class,
        Topic::class
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun verseDao(): VerseDao
    abstract fun topicDao(): TopicDao

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                val verseSql = "CREATE TABLE verse(" +
                        "`id` INTEGER PRIMARY KEY," +
                        "`chapter` INTEGER," +
                        "`verse` INTEGER," +
                        "`translation` TEXT," +
                        "`prep` TEXT," +
                        "`aqidah` INTEGER," +
                        "`akhlak` INTEGER," +
                        "`syariah` INTEGER," +
                        "`ilmu` INTEGER," +
                        "`kisah` INTEGER," +
                        "`dunia` INTEGER," +
                        "`ghaib` INTEGER," +
                        "`akhirat` INTEGER," +
                        ")"

                val topicSql = "CREATE TABLE topic(" +
                        "`id` INTEGER PRIMARY KEY" +
                        "`name` TEXT" +
                        ")"

                database.execSQL(verseSql)
                database.execSQL(topicSql)
            }
        }
    }
}