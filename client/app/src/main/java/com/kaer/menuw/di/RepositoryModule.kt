package com.kaer.menuw.di

import com.kaer.menuw.data.repository.IngredientRepositoryImpl
import com.kaer.menuw.domain.repository.IngredientRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun providesIngredientRepository(repositoryImpl: IngredientRepositoryImpl): IngredientRepository
}