package com.example.audioshopinventorymanagement.jwttokensdatastore

import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val tokenRepository: JwtTokenRepository,
    private val refreshTokenService: RefreshTokenService
) : Authenticator {
    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
    }
    override fun authenticate(route: Route?, response: Response): Request? {
        val currentToken = runBlocking {
            tokenRepository.getAccessJwt()
        }
        synchronized(this) {
            val updatedToken = runBlocking {
                tokenRepository.getAccessJwt()
            }
            val token = if (currentToken != updatedToken) updatedToken else {

                val newSessionResponse = runBlocking { refreshTokenService.refreshToken() }

                val body = newSessionResponse.body()
                if (newSessionResponse.isSuccessful && body != null) {

                    runBlocking {
                        tokenRepository.saveAccessJwt(body.accessToken)
                        tokenRepository.saveRefreshJwt(body.refreshToken)
                    }
                    body.accessToken

//                    newSessionResponse.body()?.let { s ->
//                        runBlocking {
//                            tokenRepository.saveAccessJwt(s.accessToken)
//                            tokenRepository.saveRefreshJwt(s.refreshToken)
//                        }
//                        s.accessToken
//                    }
                } else null
            }
            return if (token != null) response.request.newBuilder()
                .header(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
                .build() else null
        }
    }
}