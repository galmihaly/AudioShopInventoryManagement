package com.example.audioshopinventorymanagement.authentication.repositories

import android.system.ErrnoException
import com.example.audioshopinventorymanagement.authentication.apis.UserAPI
import com.example.audioshopinventorymanagement.authentication.requests.UserLoginRequest
import com.example.audioshopinventorymanagement.authentication.responses.sealed.UserApiResponse
import okhttp3.internal.http2.ConnectionShutdownException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class UserApiRepositoryImpl @Inject constructor(private val userAPI: UserAPI) : UserApiRepository {

    override suspend fun getUserDetails(userEmail: String): UserApiResponse {
        return try {
            val uRequest = UserLoginRequest(email = userEmail)
            val response = userAPI.getUserDetails(uRequest)

            val body = response.body()
            if (response.isSuccessful && body != null){
                return UserApiResponse.Success(body)
            }
            else{
                return UserApiResponse.Error(body!!)
            }
        }catch (e: Exception){
            when (e) {
                is SocketTimeoutException -> {
                    UserApiResponse.Exception("Timeout - Please check your internet connection!")
                }
                is UnknownHostException -> {
                    UserApiResponse.Exception("Unable to make a connection. Please check your internet!")
                }
                /*is ErrnoException -> {
                    UserApiResponse.Exception("Cannot connect to the server. Please check your internet or state of the server!")
                }*/
                is ConnectException -> {
                    UserApiResponse.Exception("Cannot connect to the server. Please check your internet or state of the server!")
                }
                is ConnectionShutdownException -> {
                    UserApiResponse.Exception("Connection lost to the server. Please check your internet!")
                }
                is IOException -> {
                    UserApiResponse.Exception("Server is unreachable. Please try again later!")
                }
                is IllegalStateException -> {
                    UserApiResponse.Exception("${e.message}")
                }
                else -> {
                    UserApiResponse.Exception("${e.message}")
                }
            }
        }
    }
}