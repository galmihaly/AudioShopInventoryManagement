package com.example.audioshopinventorymanagement.authentication.repositories

import com.example.audioshopinventorymanagement.authentication.requests.LoginAuthRequest
import com.example.audioshopinventorymanagement.authentication.apis.AuthAPI
import com.example.audioshopinventorymanagement.authentication.responses.sealed.LoginApiResponse
import javax.inject.Inject

class AuthApiRepositoryImpl @Inject constructor(private val authAPI: AuthAPI) : AuthApiRepository {

    override suspend fun authenticateUser(loginAuthRequest: LoginAuthRequest) : LoginApiResponse {
        val response = authAPI.authenticateUser(loginAuthRequest)
        return try {
            val body = response.body()
            if (response.isSuccessful && body != null){
                return LoginApiResponse.Success(body)
            }
            else{
                return LoginApiResponse.Error(body!!)
            }
        }catch (e: Exception){
            return LoginApiResponse.Exception(e.message!!)
        }
    }

}