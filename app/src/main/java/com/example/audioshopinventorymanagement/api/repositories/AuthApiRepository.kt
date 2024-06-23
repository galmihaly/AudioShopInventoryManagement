package com.example.audioshopinventorymanagement.api.repositories

import com.example.audioshopinventorymanagement.api.requests.LoginAuthRequest
import com.example.audioshopinventorymanagement.api.responses.sealed.LoginApiResponse

interface AuthApiRepository {
    suspend fun authenticateUser(loginAuthRequest: LoginAuthRequest) : LoginApiResponse
}