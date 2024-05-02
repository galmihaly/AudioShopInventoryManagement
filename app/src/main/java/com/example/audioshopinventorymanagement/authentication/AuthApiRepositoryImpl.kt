package com.example.audioshopinventorymanagement.authentication

import com.example.audioshopinventorymanagement.authentication.requests.LoginRequest
import com.example.audioshopinventorymanagement.authentication.responses.AuthApiResponse
import com.example.audioshopinventorymanagement.authentication.responses.UserServiceResponse
import com.example.audioshopinventorymanagement.authentication.api.AuthAPI
import javax.inject.Inject

class AuthApiRepositoryImpl @Inject constructor(
    private val authAPI: AuthAPI
) : AuthApiRepository {
    override suspend fun authenticateWorker(loginRequest: LoginRequest): AuthApiResponse {
        val response = authAPI.authenticateUser(loginRequest)
        return try {
            val body = response.body()
            if (response.isSuccessful && body != null){
                return AuthApiResponse.Success(body)
            }
            return AuthApiResponse.Error(response.code(), response.message())
        }catch (e: Exception){
            return AuthApiResponse.Exception(e.message!!)
        }
    }

    override suspend fun getSayHello(): UserServiceResponse {
        val response = authAPI.getSayHelloString()
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