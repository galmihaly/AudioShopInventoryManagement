package com.example.audioshopinventorymanagement.jwttokensdatastore

import okhttp3.Response
import retrofit2.http.POST


interface RefreshTokenService {
    @POST("v3/auth/refresh-token")
    suspend fun refreshToken(): Response<AuthNetworkResponse>
}