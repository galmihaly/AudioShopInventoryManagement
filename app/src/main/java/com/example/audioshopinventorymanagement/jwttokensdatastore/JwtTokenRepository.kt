package com.example.audioshopinventorymanagement.jwttokensdatastore

interface JwtTokenRepository {
    suspend fun saveAccessJwt(token: String)
    suspend fun saveRefreshJwt(token: String)
    suspend fun getAccessJwt(): JwtTokens
    suspend fun getRefreshJwt(): JwtTokens
    suspend fun clearAllTokens()
}