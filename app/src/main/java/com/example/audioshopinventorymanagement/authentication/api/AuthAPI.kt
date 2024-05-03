package com.example.audioshopinventorymanagement.authentication.api

import com.example.audioshopinventorymanagement.authentication.requests.LoginRequest
import com.example.audioshopinventorymanagement.authentication.responses.LoginResponse
import com.example.audioshopinventorymanagement.authentication.responses.UserDetailsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {
    @POST("/api/v1/auth/authenticate")
    suspend fun authenticateUser(@Body body: LoginRequest): Response<LoginResponse>
}