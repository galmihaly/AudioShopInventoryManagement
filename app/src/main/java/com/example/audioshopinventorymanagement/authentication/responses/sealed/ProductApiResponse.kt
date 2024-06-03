package com.example.audioshopinventorymanagement.authentication.responses.sealed

import com.example.audioshopinventorymanagement.authentication.responses.BaseResponse
import com.example.audioshopinventorymanagement.authentication.responses.BrandListResponse
import com.example.audioshopinventorymanagement.authentication.responses.CategoryListResponse
import com.example.audioshopinventorymanagement.authentication.responses.ModelListResponse
import kotlinx.coroutines.Deferred

sealed class ProductApiResponse(){
    class BrandSuccess(val data: BrandListResponse) : ProductApiResponse()
    class BrandError(val data: BrandListResponse) : ProductApiResponse()
    class CategorySuccess(val data: CategoryListResponse) : ProductApiResponse()
    class CategoryError(val data: CategoryListResponse) : ProductApiResponse()
    class ModelSuccess(val data: ModelListResponse) : ProductApiResponse()
    class ModelError(val data: ModelListResponse) : ProductApiResponse()
    class ProductSuccess(val data: BaseResponse) : ProductApiResponse()
    class ProductError(val data: BaseResponse) : ProductApiResponse()
    class Exception(val exceptionMessage: String) : ProductApiResponse()
}