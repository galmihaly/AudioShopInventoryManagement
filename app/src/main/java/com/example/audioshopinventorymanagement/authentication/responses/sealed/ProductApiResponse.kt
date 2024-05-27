package com.example.audioshopinventorymanagement.authentication.responses.sealed

import com.example.audioshopinventorymanagement.authentication.responses.BrandListResponse
import com.example.audioshopinventorymanagement.authentication.responses.CategoryListResponse
import com.example.audioshopinventorymanagement.authentication.responses.ModelListResponse

sealed class ProductApiResponse () {
    class BrandSuccess(val data: BrandListResponse) : ProductApiResponse()
    class BrandError(val data: BrandListResponse) : ProductApiResponse()
    class CategorySuccess(val data: CategoryListResponse) : ProductApiResponse()
    class CategoryError(val data: CategoryListResponse) : ProductApiResponse()
    class ModelSuccess(val data: ModelListResponse) : ProductApiResponse()
    class ModelError(val data: ModelListResponse) : ProductApiResponse()
    class Exception(val exceptionMessage: String) : ProductApiResponse()
}