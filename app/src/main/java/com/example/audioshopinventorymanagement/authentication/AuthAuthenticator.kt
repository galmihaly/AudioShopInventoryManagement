package com.example.audioshopinventorymanagement.authentication

import android.util.Log
import com.example.audioshopinventorymanagement.authentication.apis.RefreshTokenAPI
import com.example.audioshopinventorymanagement.jwttokensdatastore.JwtTokenRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val jwtTokenRepository: JwtTokenRepository,
    private val refreshTokenAPI: RefreshTokenAPI
) : Authenticator {
    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
    }
    override fun authenticate(route: Route?, response: Response): Request? {

        val currentToken = runBlocking {
            jwtTokenRepository.getAccessJwt()
        }

        synchronized(this) {

            val updatedToken = runBlocking {
                jwtTokenRepository.getAccessJwt()
            }

            val token = if (currentToken != updatedToken) updatedToken else {
                val newSessionResponse = runBlocking { refreshTokenAPI.refreshTokenUser() }
                val body = newSessionResponse.body()

                if (newSessionResponse.isSuccessful && body != null) {
                    runBlocking {
                        jwtTokenRepository.saveAccessJwt(body.accessToken)
                        jwtTokenRepository.saveRefreshJwt(body.refreshToken)
                    }
                    body.accessToken
                } else null
            }
            return if (token != null) response.request.newBuilder()
                .header(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
                .build() else null
        }
    }
}