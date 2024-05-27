package com.example.audioshopinventorymanagement.authentication.repositories

import android.util.Log
import com.example.audioshopinventorymanagement.authentication.apis.ProductAPI
import com.example.audioshopinventorymanagement.authentication.responses.BrandDetails
import com.example.audioshopinventorymanagement.authentication.responses.BrandListResponse
import com.example.audioshopinventorymanagement.authentication.responses.CategoryListResponse
import com.example.audioshopinventorymanagement.authentication.responses.LoginAuthResponse
import com.example.audioshopinventorymanagement.authentication.responses.ModelListResponse
import com.example.audioshopinventorymanagement.authentication.responses.sealed.LoginApiResponse
import com.example.audioshopinventorymanagement.authentication.responses.sealed.ProductApiResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.internal.http2.ConnectionShutdownException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.Arrays
import javax.inject.Inject

class ProductApiRepositoryImpl @Inject constructor(private val productAPI: ProductAPI) : ProductApiRepository {

    override suspend fun getAllBrand(): ProductApiResponse {
        Log.e("c", "c")

        return try {
            Log.e("g", "g")
            val response = productAPI.getAllBrand()

            Log.e("1", "1")
            val body = response.body()
            val errorBody = response.errorBody()
            val responseCode = response.code()

            Log.e("f", "f")
            if (response.isSuccessful && body != null){
                Log.e("a", "a")

                ProductApiResponse.BrandSuccess(body)
            }
            else if(responseCode == 401 && errorBody != null)
            {
                //Deserialize the ErrorResponse Body
                val gson = Gson()
                val type = object : TypeToken<BrandListResponse>() {}.type
                val errorResponse: BrandListResponse = gson.fromJson(errorBody.charStream(), type)
                ProductApiResponse.BrandError(errorResponse)
            }
            else{
                ProductApiResponse.BrandError(body!!)
            }
        }catch (e: Exception){
            Log.e("2", "2")
            getExceptionMessage(e)
        }
    }

    override suspend fun getAllCategory(): ProductApiResponse {
        return try {
            val response = productAPI.getAllCategory()
            val body = response.body()
            val errorBody = response.errorBody()
            val responseCode = response.code()

            if (response.isSuccessful && body != null){
                ProductApiResponse.CategorySuccess(body)
            }
            else if(responseCode == 401 && errorBody != null)
            {
                //Deserialize the ErrorResponse Body
                val gson = Gson()
                val type = object : TypeToken<CategoryListResponse>() {}.type
                val errorResponse: CategoryListResponse = gson.fromJson(errorBody.charStream(), type)
                ProductApiResponse.CategoryError(errorResponse)
            }
            else{
                ProductApiResponse.CategoryError(body!!)
            }
        }catch (e: Exception){
            getExceptionMessage(e)
        }
    }

    override suspend fun getAllModel(): ProductApiResponse {
        return try {
            val response = productAPI.getAllModel()
            val body = response.body()
            val errorBody = response.errorBody()
            val responseCode = response.code()

            if (response.isSuccessful && body != null){
                ProductApiResponse.ModelSuccess(body)
            }
            else if(responseCode == 401 && errorBody != null)
            {
                //Deserialize the ErrorResponse Body
                val gson = Gson()
                val type = object : TypeToken<ModelListResponse>() {}.type
                val errorResponse: ModelListResponse = gson.fromJson(errorBody.charStream(), type)
                ProductApiResponse.ModelError(errorResponse)
            }
            else{
                ProductApiResponse.ModelError(body!!)
            }
        }catch (e: Exception){
            getExceptionMessage(e)
        }
    }

    private fun getExceptionMessage(e : Exception) : ProductApiResponse {
        return when (e) {
            is SocketTimeoutException -> {
                ProductApiResponse.Exception("Timeout - Please check your internet connection!")
            }
            is UnknownHostException -> {
                ProductApiResponse.Exception("Unable to make a connection. Please check your internet!")
            }
            is ConnectException -> {
                ProductApiResponse.Exception("Cannot connect to the server. Please check your internet or state of the server!")
            }
            is ConnectionShutdownException -> {
                ProductApiResponse.Exception("Connection lost to the server. Please check your internet!")
            }
            is IOException -> {
                ProductApiResponse.Exception("Server is unreachable. Please try again later!")
            }
            is IllegalStateException -> {
                ProductApiResponse.Exception("${e.message}")
            }
            else -> {
                ProductApiResponse.Exception("${e.message}")
            }
        }
    }
}