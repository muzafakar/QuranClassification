package com.muzafakar.alquran.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PrefManager(context: Context) {
    private val sp = context.getSharedPreferences("quran", Context.MODE_PRIVATE)
    private val spe = sp.edit()

    companion object {
        private const val TOPIC_ASSET = "TOPIC_ASSET"
    }

    var topicAsset: List<String>
        get() {
            return try {
                val tmp: List<String>? = Gson().fromJson(
                    sp.getString(TOPIC_ASSET, ""),
                    object : TypeToken<List<String>?>() {}.type
                )
                tmp.orEmpty()
            } catch (ex: Exception) {
                ex.printStackTrace()
                emptyList()
            }
        }
        set(value) {
            spe.putString(TOPIC_ASSET, Gson().toJson(value)).commit()
        }

}