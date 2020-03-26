package com.muzafakar.alquran.util

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.muzafakar.alquran.BuildConfig

object TopicAssetChecker {
    private val mFirebaseRemoteConfig by lazy {
        val cacheExpiration: Long = if (BuildConfig.DEBUG) 0L else 360L
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(cacheExpiration)
            .build()


        return@lazy FirebaseRemoteConfig.getInstance().apply {
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(topicDefaulAsset)
        }
    }

    fun check(prefManager: PrefManager): FirebaseRemoteConfig {
        return mFirebaseRemoteConfig
    }
}

