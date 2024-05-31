package com.example.audioshopinventorymanagement.authentication.apis

import com.example.audioshopinventorymanagement.authentication.requests.LoginAuthRequest
import com.example.audioshopinventorymanagement.authentication.requests.ProductRequest
import com.example.audioshopinventorymanagement.authentication.responses.BrandListResponse
import com.example.audioshopinventorymanagement.authentication.responses.CategoryListResponse
import com.example.audioshopinventorymanagement.authentication.responses.LoginAuthResponse
import com.example.audioshopinventorymanagement.authentication.responses.ModelListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductAPI {

    @GET("/api/brand/all")
    suspend fun getAllBrand(): Response<BrandListResponse>

    @GET("/api/category/all")
    suspend fun getAllCategory(): Response<CategoryListResponse>

    @GET("/api/model/all")
    suspend fun getAllModel(): Response<ModelListResponse>

    @POST("/api/product/save")
    suspend fun sendProductList(@Body body: List<ProductRequest>)
}