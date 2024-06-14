package com.kaer.menuw.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.kaer.menuw.data.datasource.TokenDataSource
import com.kaer.menuw.data.datasource.StoredUserDataSource
import com.kaer.menuw.domain.entity.Token
import com.kaer.menuw.domain.entity.UserInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    private const val DATA_STORE_FILE_NAME = "token_prefs.pb"
    private const val USER_INFO_STORE_FILE_NAME = "user_info_prefs.pb"

    @Singleton
    @Provides
    fun provideProtoDataStore(
        @ApplicationContext appContext: Context,
        tokenDataSource: TokenDataSource
    ): DataStore<Token> {
        return DataStoreFactory.create(
            produceFile = {
                appContext.dataStoreFile(DATA_STORE_FILE_NAME)
            },
            serializer = tokenDataSource
        )
    }

    @Singleton
    @Provides
    fun provideUserProtoDataStore(
        @ApplicationContext appContext: Context,
        userDataSource: StoredUserDataSource
    ): DataStore<UserInfo> {
        return DataStoreFactory.create(
            produceFile = {
                appContext.dataStoreFile(USER_INFO_STORE_FILE_NAME)
            },
            serializer = userDataSource
        )
    }
}