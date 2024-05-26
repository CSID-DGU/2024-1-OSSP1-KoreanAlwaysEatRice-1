package com.kaer.menuw.di

import android.util.Log
import com.kaer.menuw.BuildConfig
import com.kaer.menuw.util.isJsonArray
import com.kaer.menuw.util.isJsonObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private const val BASE_URL = BuildConfig.BASE_URL
    private val json = Json { ignoreUnknownKeys = true }
    private const val APPLICATION_JSON = "application/json"

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: Interceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()


    @Provides
    @Singleton
    fun provideLoggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor { message ->
            when {
                message.isJsonObject() ->
                    Log.d("retrofit", JSONObject(message).toString(4))

                message.isJsonArray() ->
                    Log.d("retrofit", JSONArray(message).toString(4))

                else -> {
                    Log.d("retrofit", "CONNECTION INFO -> $message")
                }
            }
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    @MenuwRetrofit
    fun provideMENUWRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
//            .addConverterFactory(json.asConverterFactory(APPLICATION_JSON.toMediaType()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}