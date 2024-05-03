package com.example.audioshopinventorymanagement.authentication

import com.example.audioshopinventorymanagement.authentication.requests.UserDetailsRequest
import com.example.audioshopinventorymanagement.authentication.responses.UserServiceResponse

interface UserApiRepository {
    suspend fun getUserDetails(email: String) : UserServiceResponse
}