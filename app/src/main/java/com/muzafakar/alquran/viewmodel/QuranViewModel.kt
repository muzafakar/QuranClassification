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

    fun getTopic(topicId: Int) = topicDao.getTopic(topicId)

    fun getQuranByChapter(chapterId: Int) = verseDao.getVerseByChapter(chapterId)

    fun getQuranByTopicId(topicIndex: Int) =
            when (topicIndex) {
                1 -> verseDao.getAqidahVerses()
                2 -> verseDao.getAkhlakVerses()
                3 -> verseDao.getSyariahVerses()
                4 -> verseDao.getIlmuVerses()
                5 -> verseDao.getKisahVerses()
                6 -> verseDao.getAlamDuniaVerses()
                7 -> verseDao.getAlamGhaibVerses()
                8 -> verseDao.getAlamAkhiratVerses()
                else -> verseDao.getAqidahVerses()
            }

    // TODO getChapters()
}