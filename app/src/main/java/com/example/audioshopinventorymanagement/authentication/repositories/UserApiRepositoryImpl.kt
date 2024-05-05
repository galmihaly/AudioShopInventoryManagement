package com.example.audioshopinventorymanagement.authentication.repositories

import com.example.audioshopinventorymanagement.authentication.apis.UserAPI
import com.example.audioshopinventorymanagement.authentication.requests.UserLoginRequest
import com.example.audioshopinventorymanagement.authentication.responses.sealed.UserApiResponse
import javax.inject.Inject

class UserApiRepositoryImpl @Inject constructor(private val userAPI: UserAPI) : UserApiRepository {

    override suspend fun getUserDetails(email: String): UserApiResponse {
        val uRequest = UserLoginRequest(email = email)

        val response = userAPI.getUserDetails(uRequest)
        return try {
            val body = response.body()
            if (response.isSuccessful && body != null){
                return UserApiResponse.Success(body)
            }
            else{
                return UserApiResponse.Error(body!!)
            }
        }catch (e: Exception){
            return UserApiResponse.Exception(e.message!!)
        }
    }
}