package com.kaer.menuw.data.datasource

import androidx.datastore.core.Serializer
import com.kaer.menuw.domain.entity.UserInfo
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class StoredUserDataSource @Inject constructor() : Serializer<UserInfo> {

    override val defaultValue: UserInfo
        get() = UserInfo()

    override suspend fun readFrom(input: InputStream): UserInfo {
        return input.reader().use {
            try {
                Json.decodeFromString(UserInfo.serializer(), it.readText())
            } catch (e: SerializationException) {
                e.printStackTrace()
                defaultValue
            }
        }
    }

    override suspend fun writeTo(t: UserInfo, output: OutputStream) {
        output.writer().use { writer ->
            Json.encodeToString(UserInfo.serializer(), t).also { json ->
                writer.write(json)
            }
        }
    }


}