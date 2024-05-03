package com.example.audioshopinventorymanagement.authentication

import com.example.audioshopinventorymanagement.authentication.requests.LoginRequest
import com.example.audioshopinventorymanagement.authentication.api.AuthAPI
import com.example.audioshopinventorymanagement.authentication.responses.AuthApiResponse
import com.example.audioshopinventorymanagement.authentication.responses.LoginResponse
import javax.inject.Inject

class AuthApiRepositoryImpl @Inject constructor(
    private val authAPI: AuthAPI
) : AuthApiRepository {
    override suspend fun authenticateUser(loginRequest: LoginRequest): AuthApiResponse  {
        val response = authAPI.authenticateUser(loginRequest)
        return try {
            val body = response.body()
            if (response.isSuccessful && body != null){
                return AuthApiResponse.Success(body)
            }
            else{
                return AuthApiResponse.Error(response.code(), response.message())
            }
            return AuthApiResponse.Error(response.code(), response.message())
        }catch (e: Exception){
            return AuthApiResponse.Exception(e.message!!)
        }
    }

}