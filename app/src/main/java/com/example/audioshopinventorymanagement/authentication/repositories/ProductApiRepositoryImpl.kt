package com.example.audioshopinventorymanagement.authentication.repositories

import com.example.audioshopinventorymanagement.authentication.apis.ProductAPI
import com.example.audioshopinventorymanagement.authentication.requests.SaveProductListRequest
import com.example.audioshopinventorymanagement.authentication.responses.BaseResponse
import com.example.audioshopinventorymanagement.authentication.responses.BrandListResponse
import com.example.audioshopinventorymanagement.authentication.responses.CategoryListResponse
import com.example.audioshopinventorymanagement.authentication.responses.ModelListResponse
import com.example.audioshopinventorymanagement.authentication.responses.ProductListResponse
import com.example.audioshopinventorymanagement.authentication.responses.StoragesListResponse
import com.example.audioshopinventorymanagement.authentication.responses.WarehouseListResponse
import com.example.audioshopinventorymanagement.authentication.responses.sealed.ProductApiResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.internal.http2.ConnectionShutdownException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ProductApiRepositoryImpl @Inject constructor(
    private val productAPI: ProductAPI
) : ProductApiRepository {

    override suspend fun getAllBrand(): ProductApiResponse {
        return try {
            val response = productAPI.getAllBrand()

            val body = response.body()
            val errorBody = response.errorBody()

            if (response.isSuccessful && body != null){
                ProductApiResponse.BrandSuccess(body)
            }
            else if(errorBody != null)
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
            getExceptionMessage(e)
        }
    }

    override suspend fun getAllCategory(): ProductApiResponse {
        return try {
            val response = productAPI.getAllCategory()
            val body = response.body()
            val errorBody = response.errorBody()

            if (response.isSuccessful && body != null){
                ProductApiResponse.CategorySuccess(body)
            }
            else if(errorBody != null)
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

            if (response.isSuccessful && body != null){
                ProductApiResponse.ModelSuccess(body)
            }
            else if(errorBody != null)
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

    override suspend fun sendProductList(request: SaveProductListRequest): ProductApiResponse {
        return try {
            val response = productAPI.sendProductList(request)

            val body = response.body()
            val errorBody = response.errorBody()

            if (response.isSuccessful && body != null){
                ProductApiResponse.ProductSaveSuccess(body)
            }
            else if(errorBody != null)
            {
                val gson = Gson()
                val type = object : TypeToken<BaseResponse>() {}.type
                val errorResponse: BaseResponse = gson.fromJson(errorBody.charStream(), type)
                ProductApiResponse.ProductSaveError(errorResponse)
            }
            else{
                ProductApiResponse.ProductSaveError(body!!)
            }
        }catch (e: Exception){
            getExceptionMessage(e)
        }
    }

    override suspend fun getAllWarehouse(): ProductApiResponse {
        return try {
            val response = productAPI.getAllWarehouse()

            val body = response.body()
            val errorBody = response.errorBody()

            if (response.isSuccessful && body != null){
                ProductApiResponse.WarehouseSuccess(body)
            }
            else if(errorBody != null)
            {
                val gson = Gson()
                val type = object : TypeToken<WarehouseListResponse>() {}.type
                val errorResponse: WarehouseListResponse = gson.fromJson(errorBody.charStream(), type)
                ProductApiResponse.WarehouseError(errorResponse)
            }
            else{
                ProductApiResponse.WarehouseError(body!!)
            }
        }catch (e: Exception){
            getExceptionMessage(e)
        }
    }

    override suspend fun getStoragesByWarehouseId(warehouseId: String): ProductApiResponse {
        return try {
            val response = productAPI.getAllStoragesByWarehouseId(warehouseId)

            val body = response.body()
            val errorBody = response.errorBody()

            if (response.isSuccessful && body != null){
                ProductApiResponse.StoragesSuccess(body)
            }
            else if(errorBody != null)
            {
                val gson = Gson()
                val type = object : TypeToken<StoragesListResponse>() {}.type
                val errorResponse: StoragesListResponse = gson.fromJson(errorBody.charStream(), type)
                ProductApiResponse.StoragesError(errorResponse)
            }
            else{
                ProductApiResponse.StoragesError(body!!)
            }
        }catch (e: Exception){
            getExceptionMessage(e)
        }
    }

    override suspend fun getProductsByStorageId(storageId: String): ProductApiResponse {
        return try {
            val response = productAPI.getAllProductsByStorageId(storageId)

            val body = response.body()
            val errorBody = response.errorBody()

            if (response.isSuccessful && body != null){
                ProductApiResponse.ProductListSuccess(body)
            }
            else if(errorBody != null)
            {
                val gson = Gson()
                val type = object : TypeToken<ProductListResponse>() {}.type
                val errorResponse: ProductListResponse = gson.fromJson(errorBody.charStream(), type)
                ProductApiResponse.ProductListError(errorResponse)
            }
            else{
                ProductApiResponse.ProductListError(body!!)
            }
        }catch (e: Exception){
            getExceptionMessage(e)
        }
    }

    private fun getExceptionMessage(e : Exception) : ProductApiResponse {
        return when (e) {
            is SocketTimeoutException -> {
                ProductApiResponse.Exception("Timeout - Please check your internet connection or the server!")
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