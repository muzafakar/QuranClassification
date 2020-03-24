package com.muzafakar.alquran.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.muzafakar.alquran.model.Topic

/**
 * Created by muzafakar at 25/03/20
 */
@Dao
interface TopicDao {
    @Query("SELECT * FROM topic")
    fun getTopics(): LiveData<List<Topic>>
}