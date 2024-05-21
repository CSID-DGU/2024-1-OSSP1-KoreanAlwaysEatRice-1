package com.kaer.menuw

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.kaer.menuw.BuildConfig.NATIVE_APP_KEY
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, NATIVE_APP_KEY)

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}