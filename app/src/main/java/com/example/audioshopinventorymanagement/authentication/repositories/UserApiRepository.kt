package com.example.audioshopinventorymanagement.authentication.repositories

import com.example.audioshopinventorymanagement.authentication.responses.sealed.UserApiResponse

interface UserApiRepository {
    suspend fun getUserDetails(email: String) : UserApiResponse
}