package com.example.audioshopinventorymanagement.api.interceptors

import android.content.Context
import android.content.res.Resources
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.jwttokensdatastore.JwtTokenRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AccessTokenInterceptor @Inject constructor(
    private val jwtTokenRepository: JwtTokenRepository,
    private val context: Context
) : Interceptor {

    private var HEADER_AUTHORIZATION = context.getString(R.string.LOGIN_AUTHORIZATION_HEADER)
    private var TOKEN_TYPE = context.getString(R.string.LOGIN_TOKEN_TYPE)
    override fun intercept(chain: Interceptor.Chain): Response {
        var token = runBlocking {
            jwtTokenRepository.getAccessJwt().accessToken
        }

        if(token == ""){
            token = runBlocking {
                jwtTokenRepository.getRefreshJwt().accessToken
            }
        }

        val request = chain.request().newBuilder()
        request.addHeader(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
        return chain.proceed(request.build())
    }
}