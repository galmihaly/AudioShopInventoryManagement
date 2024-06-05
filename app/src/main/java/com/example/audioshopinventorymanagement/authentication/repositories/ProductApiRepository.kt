package com.example.audioshopinventorymanagement.authentication.repositories

import com.example.audioshopinventorymanagement.authentication.requests.SaveProductListRequest
import com.example.audioshopinventorymanagement.authentication.responses.sealed.ProductApiResponse

interface ProductApiRepository {
    suspend fun getAllBrand() : ProductApiResponse
    suspend fun getAllCategory() : ProductApiResponse
    suspend fun getAllModel() : ProductApiResponse
    suspend fun sendProductList(request: SaveProductListRequest) : ProductApiResponse
    suspend fun getAllWarehouse() : ProductApiResponse
    suspend fun getStoragesByWarehouseId(warehouseId : Int) : ProductApiResponse
}