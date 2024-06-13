package com.kaer.menuw.data.datasource

import androidx.datastore.core.Serializer
import com.kaer.menuw.domain.entity.Token
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class TokenDataSource @Inject constructor() : Serializer<Token> {

    override val defaultValue: Token
        get() = Token()

    override suspend fun readFrom(input: InputStream): Token {
        return input.reader().use {
            try {
                Json.decodeFromString(Token.serializer(), it.readText())
            } catch (e: SerializationException) {
                e.printStackTrace()
                defaultValue
            }
        }
    }

    override suspend fun writeTo(t: Token, output: OutputStream) {
        output.writer().use { writer ->
            Json.encodeToString(Token.serializer(), t).also { json ->
                writer.write(json)
            }
        }
    }
}