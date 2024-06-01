package com.example.audioshopinventorymanagement.di

import android.content.Context
import com.example.audioshopinventorymanagement.authentication.AuthAuthenticator
import com.example.audioshopinventorymanagement.authentication.apis.LoginAuthAPI
import com.example.audioshopinventorymanagement.authentication.apis.ProductAPI
import com.example.audioshopinventorymanagement.authentication.interceptors.AccessTokenInterceptor
import com.example.audioshopinventorymanagement.authentication.repositories.AuthApiRepository
import com.example.audioshopinventorymanagement.authentication.repositories.AuthApiRepositoryImpl
import com.example.audioshopinventorymanagement.authentication.repositories.ProductApiRepository
import com.example.audioshopinventorymanagement.authentication.repositories.ProductApiRepositoryImpl
import com.example.audioshopinventorymanagement.authentication.responses.BrandListResponse
import com.example.audioshopinventorymanagement.jwttokensdatastore.JwtTokenRepository
import com.example.audioshopinventorymanagement.room.ProductDatabase
import com.example.audioshopinventorymanagement.utils.ApiResponseDeserializer
import com.example.audioshopinventorymanagement.utils.Formatter
import com.example.audioshopinventorymanagement.utils.Network
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton
import java.lang.StringBuilder


@Module
@InstallIn(SingletonComponent::class)
class RetrofitProvider {

    @Singleton
    @Provides
    fun provideAccessTokenInterceptor(
        jwtTokenRepository: JwtTokenRepository,
        network: Network
    ): AccessTokenInterceptor {
        return AccessTokenInterceptor(jwtTokenRepository, network)
    }

    /*@Singleton
    @Provides
    fun provideRefreshTokenInterceptor(
        jwtTokenRepository: JwtTokenRepository,
        network: Network
    ): RefreshTokenInterceptor {
        return RefreshTokenInterceptor(jwtTokenRepository, network)
    }*/

    /*-------------*/

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AuthenticatedClient

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
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideUserApi(
        @AuthenticatedClient okHttpClient: OkHttpClient
    ): ProductAPI {

        val gson = GsonBuilder()
            .registerTypeAdapter(
                BrandListResponse::class.java,
                ApiResponseDeserializer(BrandListResponse::class.java)
            )
            .create()

        return Retrofit.Builder()
            .baseUrl("http://192.168.1.153:5255")
            /*.addConverterFactory(GsonConverterFactory.create())*/
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(ProductAPI::class.java)
    }

    /*-------------*/

    /*@Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class TokenRefreshClient*/

    /*@Provides
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
    }*/

    /*@Provides
    @Singleton
    fun provideRetrofit(
        @TokenRefreshClient okHttpClient: OkHttpClient
    ): RefreshTokenAPI {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.153:5255")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RefreshTokenAPI::class.java)
    }*/

    /*-------------*/

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class PublicClient

    @Provides
    @Singleton
    @PublicClient
    fun provideUnauthenticatedOkHttpClient(): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthenticationApi(
        @PublicClient okHttpClient: OkHttpClient
    ): LoginAuthAPI {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.153:5255")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(LoginAuthAPI::class.java)
    }

    /*-------------*/

    @Singleton
    @Provides
    fun providesAuthApiRepository(
        loginAuthAPI: LoginAuthAPI,
    ) : AuthApiRepository {
        return AuthApiRepositoryImpl(loginAuthAPI)
    }

    @Singleton
    @Provides
    fun providesProductApiRepository(
        productAPI: ProductAPI,
    ) : ProductApiRepository {
        return ProductApiRepositoryImpl(productAPI)
    }

    @Provides
    fun provideNetwork(
        @ApplicationContext context: Context
    ) : Network {
        return Network(context)
    }

    @Provides
    fun provideFormatter(
        stringBuilder: StringBuilder
    ) : Formatter {
        return Formatter(stringBuilder)
    }

    @Singleton
    @Provides
    fun provideStringBuilder() = StringBuilder()
}

