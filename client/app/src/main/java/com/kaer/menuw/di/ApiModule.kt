package com.kaer.menuw.di

import com.kaer.menuw.data.service.InfoService
import com.kaer.menuw.data.service.IngredientService
import com.kaer.menuw.data.service.LogInService
import com.kaer.menuw.data.service.MenuService
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

    @Provides
    @Singleton
    fun provideMenuService(@MenuwRetrofit retrofit: Retrofit): MenuService =
        retrofit.create(MenuService::class.java)

    @Provides
    @Singleton
    fun provideLogInService(@MenuwRetrofit retrofit: Retrofit): LogInService =
        retrofit.create(LogInService::class.java)

    @Provides
    @Singleton
    fun provideInfoService(@MenuwRetrofit retrofit: Retrofit): InfoService =
        retrofit.create(InfoService::class.java)
}