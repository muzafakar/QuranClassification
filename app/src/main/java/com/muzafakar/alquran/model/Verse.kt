package com.muzafakar.alquran.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by muzafakar at 25/03/20
 */
@Entity(tableName = "verse")
data class Verse(
    @PrimaryKey
    val id: Int,
    val chapter: Int,
    val verse: Int,
    val translation: String,
    val aqidah: Int,
    val akhlak: Int,
    val syariah: Int,
    val ilmu: Int,
    val kisah: Int,
    val dunia: Int,
    val ghaib: Int,
    val akhirat: Int
)