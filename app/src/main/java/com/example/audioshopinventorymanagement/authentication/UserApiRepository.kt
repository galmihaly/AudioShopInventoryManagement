package com.example.audioshopinventorymanagement.authentication

import com.example.audioshopinventorymanagement.authentication.responses.UserServiceResponse

interface UserApiRepository {
    suspend fun getSayHello(token: String) : UserServiceResponse
}