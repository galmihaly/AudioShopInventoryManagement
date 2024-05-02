package com.example.audioshopinventorymanagement.authentication.api

import com.example.audioshopinventorymanagement.authentication.responses.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UserAPI {
    @GET("/api/v1/demo_controller")
    suspend fun getSayHelloString(
        @Header("Authorization") token: String
    ) : Response<UserResponse>
}