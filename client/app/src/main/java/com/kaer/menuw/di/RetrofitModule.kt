package com.kaer.menuw.di

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kaer.menuw.BuildConfig
import com.kaer.menuw.util.isJsonArray
import com.kaer.menuw.util.isJsonObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private const val MENUW_BASE_URL = BuildConfig.BASE_URL

    @Provides
    @Singleton
//    @MenuwRetrofit
    fun provideOkHttpClient(
//        loggingInterceptor: HttpLoggingInterceptor,
//        @MenuwRetrofit interceptor: Interceptor,
        interceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
//            addInterceptor(tokenInterceptor)
        }.build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): Interceptor {
//        val loggingInterceptor = HttpLoggingInterceptor { message ->
//            when {
//                message.isJsonObject() ->
//                    Log.d("retrofit", JSONObject(message).toString(4))
//
//                message.isJsonArray() ->
//                    Log.d("retrofit", JSONArray(message).toString(4))
//
//                else -> {
//                    Log.d("retrofit", "CONNECTION INFO -> $message")
//                }
//            }
//        }
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d("retrofit message", message)
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Singleton
    @Provides
    @MenuwRetrofit
    fun provideMenuwRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl(MENUW_BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(okHttpClient)
            .build()
    }
//        Retrofit.Builder()
//            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
//            .baseUrl(MENUW_BASE_URL)
//            .client(okHttpClient)
//            .build()
}