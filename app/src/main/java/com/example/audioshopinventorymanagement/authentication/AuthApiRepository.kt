package com.example.audioshopinventorymanagement.authentication

import com.example.audioshopinventorymanagement.authentication.requests.LoginRequest
import com.example.audioshopinventorymanagement.authentication.responses.AuthApiResponse
import com.example.audioshopinventorymanagement.authentication.responses.UserServiceResponse

interface AuthApiRepository {
    suspend fun authenticateWorker(loginRequest: LoginRequest) : AuthApiResponse
    suspend fun getSayHello() : UserServiceResponse
}