package com.example.audioshopinventorymanagement.authentication.apis

import com.example.audioshopinventorymanagement.authentication.responses.RefreshTokenResponse
import retrofit2.Response
import retrofit2.http.POST

interface RefreshTokenAPI {
    @POST("/api/v1/auth/refresh_token")
    suspend fun refreshTokenUser() : Response<RefreshTokenResponse>
}