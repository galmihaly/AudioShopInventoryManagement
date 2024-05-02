package com.example.audioshopinventorymanagement.jwttokensdatastore

import android.util.Log
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class JwtTokenRepositoryImpl @Inject constructor(
    private val jwtTokenDataStore: DataStore<JwtTokens>,
) : JwtTokenRepository {
    override suspend fun saveAccessJwt(token: String) {
        jwtTokenDataStore.updateData {
            it.copy(
                accessToken = token
            )
        }
    }
    override suspend fun saveRefreshJwt(token: String) {
        jwtTokenDataStore.updateData {
            it.copy(
                refreshToken = token
            )
        }
    }

    override suspend fun getAccessJwt(): JwtTokens {
        return jwtTokenDataStore.data.first()
    }

    override suspend fun getRefreshJwt(): JwtTokens {
        return jwtTokenDataStore.data.first()
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