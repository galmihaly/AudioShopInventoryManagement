package com.example.audioshopinventorymanagement.jwttokensdatastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class JwtTokenRepositoryImpl @Inject constructor(
    private val appSettingsDataStore: DataStore<JwtTokens>,
) : JwtTokenRepository {
    override suspend fun saveAccessJwt(newAccessToken: String) {
        appSettingsDataStore.updateData {
            it.copy(
                accessToken = newAccessToken
            )
        }
    }

    override suspend fun saveRefreshJwt(newRefreshToken: String) {
        appSettingsDataStore.updateData {
            it.copy(
                accessToken = newRefreshToken
            )
        }
    }

    override suspend fun getAccessJwt(): String? {
        return appSettingsDataStore.data.first().accessToken
    }

    override suspend fun getRefreshJwt(): String? {
        return appSettingsDataStore.data.first().refreshJwtTokens
    }

    override suspend fun clearAllTokens() {
        appSettingsDataStore.updateData {
            it.copy(
                accessToken = "",
                refreshJwtTokens = ""
            )
        }
    }
}