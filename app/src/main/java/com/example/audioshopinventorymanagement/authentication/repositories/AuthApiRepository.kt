package com.example.audioshopinventorymanagement.authentication.repositories

import com.example.audioshopinventorymanagement.authentication.requests.LoginAuthRequest
import com.example.audioshopinventorymanagement.authentication.responses.sealed.LoginApiResponse

interface AuthApiRepository {
    suspend fun authenticateUser(loginAuthRequest: LoginAuthRequest) : LoginApiResponse
}