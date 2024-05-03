package com.example.audioshopinventorymanagement.authentication

import android.util.Log
import com.example.audioshopinventorymanagement.authentication.api.UserAPI
import com.example.audioshopinventorymanagement.authentication.requests.UserDetailsRequest
import com.example.audioshopinventorymanagement.authentication.responses.UserServiceResponse
import javax.inject.Inject

class UserApiRepositoryImpl @Inject constructor(
    private val userAPI: UserAPI
) : UserApiRepository {

    override suspend fun getUserDetails(email: String): UserServiceResponse {
        val uRequest = UserDetailsRequest(email = email)

        val response = userAPI.getUserDetails(uRequest)
        return try {
            val body = response.body()
            if (response.isSuccessful && body != null){
                return UserServiceResponse.Success(body)
            }
            return UserServiceResponse.Error(response.code(), response.message())
        }catch (e: Exception){
            return UserServiceResponse.Exception(e.message!!)
        }
    }
}