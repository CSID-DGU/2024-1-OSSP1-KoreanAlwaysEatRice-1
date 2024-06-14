package com.kaer.menuw.data.repository

import androidx.datastore.core.DataStore
import com.kaer.menuw.domain.entity.UserInfo
import com.kaer.menuw.domain.repository.StoredUserRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class StoredUserRepositoryImpl @Inject constructor(private val dataStore: DataStore<UserInfo>) :
    StoredUserRepository {

    override suspend fun setUserInfo(userName: String, userImg: String) {
        dataStore.updateData { UserInfo(userName, userImg) }
    }

    override suspend fun getUserInfo(): UserInfo = dataStore.data.first()
}