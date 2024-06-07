package com.example.audioshopinventorymanagement.authentication.responses.sealed

import com.example.audioshopinventorymanagement.authentication.responses.BaseResponse
import com.example.audioshopinventorymanagement.authentication.responses.BrandListResponse
import com.example.audioshopinventorymanagement.authentication.responses.CategoryListResponse
import com.example.audioshopinventorymanagement.authentication.responses.ModelListResponse
import com.example.audioshopinventorymanagement.authentication.responses.ProductListResponse
import com.example.audioshopinventorymanagement.authentication.responses.StoragesListResponse
import com.example.audioshopinventorymanagement.authentication.responses.WarehouseListResponse

sealed class ProductApiResponse(){
    class BrandSuccess(val data: BrandListResponse) : ProductApiResponse()
    class BrandError(val data: BrandListResponse) : ProductApiResponse()
    class CategorySuccess(val data: CategoryListResponse) : ProductApiResponse()
    class CategoryError(val data: CategoryListResponse) : ProductApiResponse()
    class ModelSuccess(val data: ModelListResponse) : ProductApiResponse()
    class ModelError(val data: ModelListResponse) : ProductApiResponse()
    class ProductSaveSuccess(val data: BaseResponse) : ProductApiResponse()
    class ProductSaveError(val data: BaseResponse) : ProductApiResponse()
    class ProductListSuccess(val data: ProductListResponse) : ProductApiResponse()
    class ProductListError(val data: ProductListResponse) : ProductApiResponse()
    class WarehouseSuccess(val data: WarehouseListResponse) : ProductApiResponse()
    class WarehouseError(val data: WarehouseListResponse) : ProductApiResponse()
    class StoragesSuccess(val data: StoragesListResponse) : ProductApiResponse()
    class StoragesError(val data: StoragesListResponse) : ProductApiResponse()
    class Exception(val exceptionMessage: String) : ProductApiResponse()
}