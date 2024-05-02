package com.example.audioshopinventorymanagement.di

import com.example.audioshopinventorymanagement.authentication.AuthAuthenticator
import com.example.audioshopinventorymanagement.authentication.AuthApiRepository
import com.example.audioshopinventorymanagement.authentication.AuthApiRepositoryImpl
import com.example.audioshopinventorymanagement.authentication.interceptors.AccessTokenInterceptor
import com.example.audioshopinventorymanagement.authentication.api.AuthAPI
import com.example.audioshopinventorymanagement.authentication.api.RefreshTokenAPI
import com.example.audioshopinventorymanagement.authentication.interceptors.RefreshTokenInterceptor
import com.example.audioshopinventorymanagement.jwttokensdatastore.JwtTokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitProvider {

    /*--------------------------------------------------------*/

//
//    @Provides
//    @Singleton
//    @TokenRefreshClient
//    fun provideRefreshOkHttpClient(
//        refreshTokenInterceptor: RefreshTokenInterceptor
//    ): OkHttpClient {
//
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        return OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .addInterceptor(refreshTokenInterceptor)
//            .connectTimeout(60, TimeUnit.SECONDS)
//            .readTimeout(60, TimeUnit.SECONDS)
//            .writeTimeout(60, TimeUnit.SECONDS)
//            .build()
//    }

    /*--------------------------------------------------------*/

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AuthenticatedClient

    @Singleton
    @Provides
    fun provideAccessTokenInterceptor(
        jwtTokenRepository: JwtTokenRepository
    ): AccessTokenInterceptor {
        return AccessTokenInterceptor(jwtTokenRepository)
    }

    @Provides
    @Singleton
    @AuthenticatedClient
    fun provideAccessOkHttpClient(
        accessTokenInterceptor: AccessTokenInterceptor,
        authAuthenticator: AuthAuthenticator
    ): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .authenticator(authAuthenticator)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(accessTokenInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthenticateApi(
        jwtTokenRepository: JwtTokenRepository,
        refreshTokenAPI: RefreshTokenAPI
    ): AuthAuthenticator {
        return AuthAuthenticator(jwtTokenRepository, refreshTokenAPI)
    }

    @Provides
    @Singleton
    fun provideAuthenticationApi(
        @AuthenticatedClient okHttpClient: OkHttpClient
    ): AuthAPI {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.153:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(AuthAPI::class.java)
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class TokenRefreshClient

    @Provides
    @Singleton
    @TokenRefreshClient
    fun provideRefreshOkHttpClient(
        refreshTokenInterceptor: RefreshTokenInterceptor
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(refreshTokenInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        @TokenRefreshClient okHttpClient: OkHttpClient
    ): RefreshTokenAPI {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.153:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RefreshTokenAPI::class.java)
    }

    /*-------------*/

    @Singleton
    @Provides
    fun providesWorkerRepository(
        authAPI: AuthAPI,
    ) : AuthApiRepository {
        return AuthApiRepositoryImpl(authAPI)
    }
}

