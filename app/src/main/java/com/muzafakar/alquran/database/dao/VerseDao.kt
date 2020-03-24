package com.muzafakar.alquran.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.muzafakar.alquran.model.Verse

/**
 * Created by muzafakar at 25/03/20
 */
@Dao
interface VerseDao {
    @Query("SELECT * FROM verse WHERE chapter= :chapterId")
    fun getVerseByChapter(chapterId: Int): LiveData<List<Verse>>

    @Query("SELECT * FROM verse WHERE aqidah=1")
    fun getAqidahVerses(): LiveData<List<Verse>>

    @Query("SELECT * FROM verse WHERE kisah=1")
    fun getKisahVerses(): LiveData<List<Verse>>

    @Query("SELECT * FROM verse WHERE syariah=1")
    fun getSyariahVerses(): LiveData<List<Verse>>

    @Query("SELECT * FROM verse WHERE akhlak=1")
    fun getAkhlakVerses(): LiveData<List<Verse>>

    @Query("SELECT * FROM verse WHERE ilmu=1")
    fun getIlmuVerses(): LiveData<List<Verse>>

    @Query("SELECT * FROM verse WHERE dunia=1")
    fun getAlamDuniaVerses(): LiveData<List<Verse>>

    @Query("SELECT * FROM verse WHERE ghaib=1")
    fun getAlamGhaibVerses(): LiveData<List<Verse>>

    @Query("SELECT * FROM verse WHERE akhirat=1")
    fun getAlamAkhiratVerses(): LiveData<List<Verse>>

}