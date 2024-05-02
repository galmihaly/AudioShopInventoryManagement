package com.example.audioshopinventorymanagement.jwttokensdatastore

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationException
import androidx.datastore.core.Serializer
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class JwtTokenSerializer @Inject constructor() : Serializer<JwtTokens> {

    override val defaultValue: JwtTokens get() = JwtTokens(
        accessToken = "",
        refreshToken = ""
    )

    override suspend fun readFrom(input: InputStream): JwtTokens {
//        val decryptedBytes = aesCryptoManager.decrypt(input)
//        return try {
//            Json.decodeFromString(
//                deserializer = AppSettings.serializer(),
//                string = decryptedBytes.decodeToString()
//            )
//        } catch(e: SerializationException) {
//            e.printStackTrace()
//            return defaultValue
//        }
        return try {
            Json.decodeFromString(
                deserializer = JwtTokens.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch(e: SerializationException) {
            e.printStackTrace()
            return defaultValue
        }
    }

    override suspend fun writeTo(t: JwtTokens, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = JwtTokens.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }

//        aesCryptoManager.encrypt(
//            bytes = Json.encodeToString(
//                serializer = AppSettings.serializer(),
//                value = t
//            ).encodeToByteArray(),
//            outputStream = output
//        )
    }
}