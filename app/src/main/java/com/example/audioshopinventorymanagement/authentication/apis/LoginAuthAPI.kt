package com.example.audioshopinventorymanagement.authentication.apis

import com.example.audioshopinventorymanagement.authentication.requests.LoginAuthRequest
import com.example.audioshopinventorymanagement.authentication.responses.LoginAuthResponse
import com.example.audioshopinventorymanagement.authentication.responses.RefreshTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAuthAPI {
    @POST("/api/user/login")
    suspend fun authenticateUser(@Body body: LoginAuthRequest): Response<LoginAuthResponse>

    @POST("/api/user/refreshtoken")
    suspend fun refreshTokenUser() : Response<RefreshTokenResponse>
}