package com.example.audioshopinventorymanagement.api.apiinterfaces

import com.example.audioshopinventorymanagement.api.requests.LoginAuthRequest
import com.example.audioshopinventorymanagement.api.responses.LoginAuthResponse
import com.example.audioshopinventorymanagement.api.responses.RefreshTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAuthAPI {
    @POST("/api/user/login")
    suspend fun authenticateUser(@Body body: LoginAuthRequest): Response<LoginAuthResponse>

    @POST("/api/user/refreshtoken")
    suspend fun refreshTokenUser() : Response<RefreshTokenResponse>
}