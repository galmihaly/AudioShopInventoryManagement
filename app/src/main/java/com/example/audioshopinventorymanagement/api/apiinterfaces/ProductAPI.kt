package com.example.audioshopinventorymanagement.api.apiinterfaces

import com.example.audioshopinventorymanagement.api.requests.SaveProductListRequest
import com.example.audioshopinventorymanagement.api.responses.BaseResponse
import com.example.audioshopinventorymanagement.api.responses.BrandListResponse
import com.example.audioshopinventorymanagement.api.responses.CategoryListResponse
import com.example.audioshopinventorymanagement.api.responses.ModelListResponse
import com.example.audioshopinventorymanagement.api.responses.ProductListResponse
import com.example.audioshopinventorymanagement.api.responses.StoragesListResponse
import com.example.audioshopinventorymanagement.api.responses.WarehouseListResponse
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