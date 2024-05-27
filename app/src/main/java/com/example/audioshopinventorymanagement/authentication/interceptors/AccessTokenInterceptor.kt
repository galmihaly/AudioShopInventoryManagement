package com.example.audioshopinventorymanagement.authentication.interceptors

import android.util.Log
import com.example.audioshopinventorymanagement.jwttokensdatastore.JwtTokenRepository
import com.example.audioshopinventorymanagement.utils.Network
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class AccessTokenInterceptor @Inject constructor(
    private val jwtTokenRepository: JwtTokenRepository,
    private val network: Network
) : Interceptor {
    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
    }
    override fun intercept(chain: Interceptor.Chain): Response {
        var token = runBlocking {
            jwtTokenRepository.getAccessJwt().accessToken
        }

        if(token == ""){
            token = runBlocking {
                jwtTokenRepository.getRefreshJwt().accessToken
            }
        }

        if (network.isOnline()){
            val request = chain.request().newBuilder()
            request.addHeader(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
            return chain.proceed(request.build())
        }
        else{
            throw IOException("No Internet Connection!")
        }
    }
}