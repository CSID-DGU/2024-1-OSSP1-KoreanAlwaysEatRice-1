package com.kaer.menuw

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.kaer.menuw.BuildConfig.NATIVE_APP_KEY
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, NATIVE_APP_KEY)
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        Timber.d("키 해시 -> ${Utility.getKeyHash(this)}")
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}