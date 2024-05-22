package com.example.audioshopinventorymanagement.authentication.repositories

import com.example.audioshopinventorymanagement.authentication.requests.LoginAuthRequest
import com.example.audioshopinventorymanagement.authentication.apis.LoginAuthAPI
import com.example.audioshopinventorymanagement.authentication.responses.sealed.LoginApiResponse
import javax.inject.Inject

class AuthApiRepositoryImpl @Inject constructor(private val loginAuthAPI: LoginAuthAPI) : AuthApiRepository {

    override suspend fun authenticateUser(loginAuthRequest: LoginAuthRequest) : LoginApiResponse {
        val response = loginAuthAPI.authenticateUser(loginAuthRequest)

        return try {
            val body = response.body()
            if (response.isSuccessful && body != null){
                LoginApiResponse.Success(body)
            } else{
                LoginApiResponse.Error(body!!)
            }
        }catch (e: Exception){
            LoginApiResponse.Exception(e.message!!)
        }
    }

}