package com.example.audioshopinventorymanagement.authentication.repositories

import com.example.audioshopinventorymanagement.authentication.requests.LoginAuthRequest
import com.example.audioshopinventorymanagement.authentication.requests.ProductRequest
import com.example.audioshopinventorymanagement.authentication.requests.SaveProductListRequest
import com.example.audioshopinventorymanagement.authentication.responses.BrandListResponse
import com.example.audioshopinventorymanagement.authentication.responses.CategoryListResponse
import com.example.audioshopinventorymanagement.authentication.responses.ModelListResponse
import com.example.audioshopinventorymanagement.authentication.responses.sealed.LoginApiResponse
import com.example.audioshopinventorymanagement.authentication.responses.sealed.ProductApiResponse
import kotlinx.coroutines.Deferred

interface ProductApiRepository {
    suspend fun getAllBrand() : ProductApiResponse
    suspend fun getAllCategory() : ProductApiResponse
    suspend fun getAllModel() : ProductApiResponse
    suspend fun sendProductList(request: SaveProductListRequest) : ProductApiResponse
}