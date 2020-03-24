package com.muzafakar.alquran.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.muzafakar.alquran.database.dao.TopicDao
import com.muzafakar.alquran.database.dao.VerseDao
import com.muzafakar.alquran.model.TopicEnum
import com.muzafakar.alquran.model.TopicEnum.*
import com.muzafakar.alquran.model.Verse

/**
 * Created by muzafakar at 25/03/20
 */
class QuranViewModel(
    private val verseDao: VerseDao,
    private val topicDao: TopicDao
) : ViewModel() {

    fun getTopics() = topicDao.getTopics()

    fun getQuranByChapter(chapterId: Int) = verseDao.getVerseByChapter(chapterId)

    fun getQuranByTopic(topicEnum: TopicEnum) =
        when (topicEnum) {
            AQIDAH -> verseDao.getAqidahVerses()
            AKHLAK -> verseDao.getAkhlakVerses()
            ILMU -> verseDao.getIlmuVerses()
            KISAH -> verseDao.getKisahVerses()
            DUNIA -> verseDao.getAlamDuniaVerses()
            GHAIB -> verseDao.getAlamGhaibVerses()
            AKHIRAT -> verseDao.getAlamAkhiratVerses()
        }

    // TODO getChapters()
}