package com.example.audioshopinventorymanagement.authentication.api

import com.example.audioshopinventorymanagement.authentication.requests.LoginRequest
import com.example.audioshopinventorymanagement.authentication.requests.UserDetailsRequest
import com.example.audioshopinventorymanagement.authentication.responses.UserDetailsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserAPI {

    companion object{
        private const val HEADER_AUTHORIZATION = "Authorization"
    }
    @POST("/api/v1/user/userdetails")
    suspend fun getUserDetails(
//        @Header(HEADER_AUTHORIZATION) token: String,
        @Body body: UserDetailsRequest
    ) : Response<UserDetailsResponse>
}