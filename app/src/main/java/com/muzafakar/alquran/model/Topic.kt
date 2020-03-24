package com.muzafakar.alquran.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by muzafakar at 25/03/20
 */
@Entity(tableName = "topic")
data class Topic(
    @PrimaryKey
    val id: Int,
    val name: String
)