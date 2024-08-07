package com.example.audioshopinventorymanagement.di

import android.content.Context
import com.example.audioshopinventorymanagement.BuildConfig
import com.example.audioshopinventorymanagement.api.AuthAuthenticator
import com.example.audioshopinventorymanagement.api.apiinterfaces.LoginAuthAPI
import com.example.audioshopinventorymanagement.api.apiinterfaces.ProductAPI
import com.example.audioshopinventorymanagement.api.interceptors.AccessTokenInterceptor
import com.example.audioshopinventorymanagement.api.repositories.AuthApiRepository
import com.example.audioshopinventorymanagement.api.repositories.AuthApiRepositoryImpl
import com.example.audioshopinventorymanagement.api.repositories.ProductApiRepository
import com.example.audioshopinventorymanagement.api.repositories.ProductApiRepositoryImpl
import com.example.audioshopinventorymanagement.api.responses.BaseResponse
import com.example.audioshopinventorymanagement.api.responses.BrandListResponse
import com.example.audioshopinventorymanagement.api.responses.CategoryListResponse
import com.example.audioshopinventorymanagement.api.responses.ModelListResponse
import com.example.audioshopinventorymanagement.jwttokensdatastore.JwtTokenRepository
import com.example.audioshopinventorymanagement.utils.ApiResponseDeserializer
import com.google.gson.Gson
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


@Module
@InstallIn(SingletonComponent::class)
class RetrofitProvider {

    @Singleton
    @Provides
    fun provideAccessTokenInterceptor(
        jwtTokenRepository: JwtTokenRepository,
        @ApplicationContext context: Context
    ): AccessTokenInterceptor {
        return AccessTokenInterceptor(jwtTokenRepository, context)
    }

    @Provides
    @Singleton
    fun provideGson() : Gson {
        return GsonBuilder()
            .registerTypeAdapter(
                BrandListResponse::class.java,
                ApiResponseDeserializer(BrandListResponse::class.java)
            )
            .registerTypeAdapter(
                CategoryListResponse::class.java,
                ApiResponseDeserializer(CategoryListResponse::class.java)
            )
            .registerTypeAdapter(
                ModelListResponse::class.java,
                ApiResponseDeserializer(ModelListResponse::class.java)
            )
            .registerTypeAdapter(
                BaseResponse::class.java,
                ApiResponseDeserializer(BaseResponse::class.java)
            )
            .create()
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
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }


    @Provides
    @Singleton
    fun provideUserApi(
        @AuthenticatedClient okHttpClient: OkHttpClient,
        gson: Gson
    ): ProductAPI {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_KEY)
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
            .baseUrl(BuildConfig.API_KEY)
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
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthenticationApi(
        @PublicClient okHttpClient: OkHttpClient
    ): LoginAuthAPI {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_KEY)
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

    @Singleton
    @Provides
    fun providesAuthAuthenticator(
        jwtTokenRepository: JwtTokenRepository,
        loginAuthAPI: LoginAuthAPI,
        @ApplicationContext context: Context
    ) : AuthAuthenticator {
        return AuthAuthenticator(jwtTokenRepository, loginAuthAPI, context)
    }
}

