package com.example.audioshopinventorymanagement.api.repositories

import android.content.res.Resources
import android.util.Log
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.api.apiinterfaces.ProductAPI
import com.example.audioshopinventorymanagement.api.requests.SaveProductListRequest
import com.example.audioshopinventorymanagement.api.responses.BaseResponse
import com.example.audioshopinventorymanagement.api.responses.BrandListResponse
import com.example.audioshopinventorymanagement.api.responses.CategoryListResponse
import com.example.audioshopinventorymanagement.api.responses.ModelListResponse
import com.example.audioshopinventorymanagement.api.responses.ProductListResponse
import com.example.audioshopinventorymanagement.api.responses.StoragesListResponse
import com.example.audioshopinventorymanagement.api.responses.WarehouseListResponse
import com.example.audioshopinventorymanagement.api.responses.sealed.ProductApiResponse
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
            else {
                var errorResponse = BrandListResponse()
                if(errorBody != null){
                    val gson = Gson()
                    val type = object : TypeToken<BrandListResponse>() {}.type
                    errorResponse = gson.fromJson(errorBody.charStream(), type)
                    ProductApiResponse.BrandError(errorResponse)
                }
                ProductApiResponse.BrandError(errorResponse)
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
            else {
                var errorResponse = CategoryListResponse()
                if(errorBody != null){
                    val gson = Gson()
                    val type = object : TypeToken<CategoryListResponse>() {}.type
                    errorResponse = gson.fromJson(errorBody.charStream(), type)
                    ProductApiResponse.CategoryError(errorResponse)
                }
                ProductApiResponse.CategoryError(errorResponse)
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
            else {
                var errorResponse = ModelListResponse()
                if(errorBody != null){
                    val gson = Gson()
                    val type = object : TypeToken<ModelListResponse>() {}.type
                    errorResponse = gson.fromJson(errorBody.charStream(), type)
                    ProductApiResponse.ModelError(errorResponse)
                }
                ProductApiResponse.ModelError(errorResponse)
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
            else {
                var errorResponse = BaseResponse()
                if(errorBody != null){
                    val gson = Gson()
                    val type = object : TypeToken<BaseResponse>() {}.type
                    errorResponse = gson.fromJson(errorBody.charStream(), type)
                    ProductApiResponse.ProductSaveError(errorResponse)
                }
                ProductApiResponse.ProductSaveError(errorResponse)
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
            else {
                var errorResponse = WarehouseListResponse()
                if(errorBody != null){
                    val gson = Gson()
                    val type = object : TypeToken<WarehouseListResponse>() {}.type
                    errorResponse = gson.fromJson(errorBody.charStream(), type)
                    ProductApiResponse.WarehouseError(errorResponse)
                }
                ProductApiResponse.WarehouseError(errorResponse)
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
            else {
                var errorResponse = StoragesListResponse()
                if(errorBody != null){
                    val gson = Gson()
                    val type = object : TypeToken<StoragesListResponse>() {}.type
                    errorResponse = gson.fromJson(errorBody.charStream(), type)
                    ProductApiResponse.StoragesError(errorResponse)
                }
                ProductApiResponse.StoragesError(errorResponse)
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
            else {
                var errorResponse = ProductListResponse()
                if(errorBody != null){
                    val gson = Gson()
                    val type = object : TypeToken<ProductListResponse>() {}.type
                    errorResponse = gson.fromJson(errorBody.charStream(), type)
                    ProductApiResponse.ProductListError(errorResponse)
                }
                ProductApiResponse.ProductListError(errorResponse)
            }
        }catch (e: Exception){
            getExceptionMessage(e)
        }
    }

    private fun getExceptionMessage(e : Exception) : ProductApiResponse {
        return when (e) {
            is SocketTimeoutException -> {
                ProductApiResponse.Exception(Resources.getSystem().getString(R.string.API_SOCKET_TIMEOUT))
            }
            is UnknownHostException -> {
                ProductApiResponse.Exception(Resources.getSystem().getString(R.string.API_UNABLE_CONNECTION))
            }
            is ConnectException -> {
                ProductApiResponse.Exception(Resources.getSystem().getString(R.string.API_CANNOT_CONNECTION))
            }
            is ConnectionShutdownException -> {
                ProductApiResponse.Exception(Resources.getSystem().getString(R.string.API_LOST_CONNECTION))
            }
            is IOException -> {
                ProductApiResponse.Exception(Resources.getSystem().getString(R.string.API_UNREACHABLE_SERVER))
            }
            else -> {
                ProductApiResponse.Exception("${e.message}")
            }
        }
    }
}