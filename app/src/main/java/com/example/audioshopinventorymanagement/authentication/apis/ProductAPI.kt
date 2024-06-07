package com.example.audioshopinventorymanagement.authentication.apis

import com.example.audioshopinventorymanagement.authentication.requests.SaveProductListRequest
import com.example.audioshopinventorymanagement.authentication.responses.BaseResponse
import com.example.audioshopinventorymanagement.authentication.responses.BrandListResponse
import com.example.audioshopinventorymanagement.authentication.responses.CategoryListResponse
import com.example.audioshopinventorymanagement.authentication.responses.ModelListResponse
import com.example.audioshopinventorymanagement.authentication.responses.ProductListResponse
import com.example.audioshopinventorymanagement.authentication.responses.StoragesListResponse
import com.example.audioshopinventorymanagement.authentication.responses.WarehouseListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductAPI {

    @GET("/api/brand/all")
    suspend fun getAllBrand(): Response<BrandListResponse>

    @GET("/api/category/all")
    suspend fun getAllCategory(): Response<CategoryListResponse>

    @GET("/api/model/all")
    suspend fun getAllModel(): Response<ModelListResponse>

    @POST("/api/product/save")
    suspend fun sendProductList(@Body request: SaveProductListRequest) : Response<BaseResponse>

    @GET("/api/warehouse/all")
    suspend fun getAllWarehouse(): Response<WarehouseListResponse>

    @GET("/api/storage/get/{warehouseId}")
    suspend fun getAllStoragesByWarehouseId(@Path("warehouseId") warehouseId : String): Response<StoragesListResponse>

    @GET("/api/product/get/{storageId}")
    suspend fun getAllProductsByStorageId(@Path("storageId") storageId : String): Response<ProductListResponse>
}