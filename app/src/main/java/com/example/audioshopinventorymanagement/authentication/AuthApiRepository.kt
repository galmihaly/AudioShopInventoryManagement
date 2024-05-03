package com.example.audioshopinventorymanagement.authentication

import com.example.audioshopinventorymanagement.authentication.requests.LoginRequest
import com.example.audioshopinventorymanagement.authentication.responses.AuthApiResponse

interface AuthApiRepository {
    suspend fun authenticateUser(loginRequest: LoginRequest) : AuthApiResponse
}