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
    version = 2,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun verseDao(): VerseDao
    abstract fun topicDao(): TopicDao

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                val verse =
                    "CREATE TABLE \"verse\" ( `id` INTEGER NOT NULL, `chapter` INTEGER NOT NULL, `verse` INTEGER NOT NULL, `translation` TEXT NOT NULL, `prep` TEXT NOT NULL, `aqidah` INTEGER NOT NULL, `akhlak` INTEGER NOT NULL, `syariah` INTEGER NOT NULL, `ilmu` INTEGER NOT NULL, `kisah` INTEGER NOT NULL, `dunia` INTEGER NOT NULL, `ghaib` INTEGER NOT NULL, `akhirat` INTEGER NOT NULL, PRIMARY KEY(`id`) )"

                val topic =
                    "CREATE TABLE `topic` ( `id` INTEGER NOT NULL, `name` TEXT NOT NULL, PRIMARY KEY(`id`) )"

                database.execSQL(verse)
                database.execSQL(topic)
            }
        }
    }
}