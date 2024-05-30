package com.example.audioshopinventorymanagement.authentication.repositories

import android.util.Log
import com.example.audioshopinventorymanagement.authentication.requests.LoginAuthRequest
import com.example.audioshopinventorymanagement.authentication.apis.LoginAuthAPI
import com.example.audioshopinventorymanagement.authentication.responses.LoginAuthResponse
import com.example.audioshopinventorymanagement.authentication.responses.sealed.LoginApiResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.internal.http2.ConnectionShutdownException
import org.json.JSONObject
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
            val errorBody = response.errorBody()
            val responseCode = response.code()

            if (response.isSuccessful && body != null){
                LoginApiResponse.Success(body)
            }
            else if(responseCode == 401 && errorBody != null)
            {
                //Deserialize the ErrorResponse Body
                val gson = Gson()
                val type = object : TypeToken<LoginAuthResponse>() {}.type
                val errorResponse: LoginAuthResponse = gson.fromJson(errorBody.charStream(), type)
                LoginApiResponse.Error(errorResponse)
            }
            else{
                LoginApiResponse.Error(body!!)
            }
        }catch (e: Exception){
            when (e) {
                is SocketTimeoutException -> {
                    LoginApiResponse.Exception("Timeout - Please check your internet connection or the server!")
                }
                is UnknownHostException -> {
                    LoginApiResponse.Exception("Unable to make a connection. Please check your internet!")
                }
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