package com.example.audioshopinventorymanagement.authentication.apis

import com.example.audioshopinventorymanagement.authentication.requests.UserLoginRequest
import com.example.audioshopinventorymanagement.authentication.responses.UserLoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {
    @POST("/api/v1/user/userdetails")
    suspend fun getUserDetails(@Body body: UserLoginRequest) : Response<UserLoginResponse>
}