package com.example.audioshopinventorymanagement.authentication

import com.example.audioshopinventorymanagement.authentication.api.UserAPI
import com.example.audioshopinventorymanagement.authentication.responses.UserServiceResponse
import javax.inject.Inject

class UserApiRepositoryImpl @Inject constructor(
    private val userAPI: UserAPI
) : UserApiRepository {

    override suspend fun getSayHello(token: String): UserServiceResponse {
        val response = userAPI.getSayHelloString(token)
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