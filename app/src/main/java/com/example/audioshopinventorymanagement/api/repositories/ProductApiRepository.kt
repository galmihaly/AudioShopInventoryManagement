package com.example.audioshopinventorymanagement.api.repositories

import com.example.audioshopinventorymanagement.api.requests.SaveProductListRequest
import com.example.audioshopinventorymanagement.api.responses.sealed.ProductApiResponse

interface ProductApiRepository {
    suspend fun getAllBrand() : ProductApiResponse
    suspend fun getAllCategory() : ProductApiResponse
    suspend fun getAllModel() : ProductApiResponse
    suspend fun sendProductList(request: SaveProductListRequest) : ProductApiResponse
    suspend fun getAllWarehouse() : ProductApiResponse
    suspend fun getStoragesByWarehouseId(warehouseId : String) : ProductApiResponse
    suspend fun getProductsByStorageId(storageId : String) : ProductApiResponse
}