package com.kaer.menuw.di

import com.kaer.menuw.data.repository.InfoRepositoryImpl
import com.kaer.menuw.data.repository.IngredientRepositoryImpl
import com.kaer.menuw.data.repository.LogInRepositoryImpl
import com.kaer.menuw.data.repository.MenuRepositoryImpl
import com.kaer.menuw.data.repository.TokenRepositoryImpl
import com.kaer.menuw.data.repository.StoredUserRepositoryImpl
import com.kaer.menuw.domain.repository.InfoRepository
import com.kaer.menuw.domain.repository.IngredientRepository
import com.kaer.menuw.domain.repository.LogInRepository
import com.kaer.menuw.domain.repository.MenuRepository
import com.kaer.menuw.domain.repository.TokenRepository
import com.kaer.menuw.domain.repository.StoredUserRepository
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

    @Singleton
    @Binds
    abstract fun providesMenuRepository(repositoryImpl: MenuRepositoryImpl): MenuRepository

    @Singleton
    @Binds
    abstract fun providesLogInRepository(repositoryImpl: LogInRepositoryImpl): LogInRepository

    @Singleton
    @Binds
    abstract fun providesTokenRepository(repositoryImpl: TokenRepositoryImpl): TokenRepository

    @Singleton
    @Binds
    abstract fun providesInfoRepository(repositoryImpl: InfoRepositoryImpl): InfoRepository

    @Singleton
    @Binds
    abstract fun providesUserRepository(repositoryImpl: StoredUserRepositoryImpl): StoredUserRepository
}