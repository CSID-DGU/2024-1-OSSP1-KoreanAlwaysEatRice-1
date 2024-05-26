package com.kaer.menuw.di

import com.kaer.menuw.data.service.IngredientService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideIngredientService(@MenuwRetrofit retrofit: Retrofit): IngredientService =
        retrofit.create(IngredientService::class.java)
}