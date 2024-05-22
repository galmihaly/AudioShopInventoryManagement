package com.example.audioshopinventorymanagement.authentication.repositories

import android.system.ErrnoException
import android.util.Log
import com.example.audioshopinventorymanagement.authentication.requests.LoginAuthRequest
import com.example.audioshopinventorymanagement.authentication.apis.LoginAuthAPI
import com.example.audioshopinventorymanagement.authentication.responses.sealed.LoginApiResponse
import com.example.audioshopinventorymanagement.authentication.responses.sealed.UserApiResponse
import okhttp3.internal.http2.ConnectionShutdownException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class AuthApiRepositoryImpl @Inject constructor(private val loginAuthAPI: LoginAuthAPI) : AuthApiRepository {

    override suspend fun authenticateUser(loginAuthRequest: LoginAuthRequest) : LoginApiResponse {
        return try {
            val response = loginAuthAPI.authenticateUser(loginAuthRequest)

            val body = response.body()
            if (response.isSuccessful && body != null){
                LoginApiResponse.Success(body)
            } else{
                LoginApiResponse.Error(body!!)
            }
        }catch (e: Exception){
            Log.e("e", e.message!!)
            when (e) {
                is SocketTimeoutException -> {
                    LoginApiResponse.Exception("Timeout - Please check your internet connection!")
                }
                is UnknownHostException -> {
                    LoginApiResponse.Exception("Unable to make a connection. Please check your internet!")
                }
                /*is ErrnoException -> {
                    LoginApiResponse.Exception("Cannot connect to the server. Please check your internet or state of the server!")
                }*/
                is ConnectException -> {
                    LoginApiResponse.Exception("Cannot connect to the server. Please check your internet or state of the server!")
                }
                is ConnectionShutdownException -> {
                    LoginApiResponse.Exception("Connection lost to the server. Please check your internet!")
                }
                is IOException -> {
                    LoginApiResponse.Exception("Server is unreachable. Please try again later!")
                }
                is IllegalStateException -> {
                    LoginApiResponse.Exception("${e.message}")
                }
                else -> {
                    LoginApiResponse.Exception("${e.message}")
                }
            }
        }
    }

}