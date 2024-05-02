package com.example.audioshopinventorymanagement.jwttokensdatastore

import android.util.Log
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class JwtTokenRepositoryImpl @Inject constructor(
    private val jwtTokenDataStore: DataStore<JwtTokens>,
) : JwtTokenRepository {
    override suspend fun saveAccessJwt(token: String) {
        Log.e("1", "hello")
        jwtTokenDataStore.updateData {
            it.copy(
                accessToken = token
            )
        }
    }
    override suspend fun saveRefreshJwt(token: String) {
        jwtTokenDataStore.updateData {
            it.copy(
                accessToken = token
            )
        }
    }

    override suspend fun getAccessJwt(): String {
        val token = jwtTokenDataStore.data.first().accessToken
        Log.e("JWT From DataStore", token)
        return token
    }

    override suspend fun getRefreshJwt(): String {
        return jwtTokenDataStore.data.first().refreshToken
    }

    override suspend fun clearAllTokens() {
        jwtTokenDataStore.updateData {
            it.copy(
                accessToken = "",
                refreshToken = ""
            )
        }
    }
}