package com.muzafakar.alquran.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by muzafakar at 25/03/20
 */
@Parcelize
@Entity(tableName = "topic")
data class Topic(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
) : Parcelable