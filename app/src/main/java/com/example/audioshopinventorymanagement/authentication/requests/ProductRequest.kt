package com.example.audioshopinventorymanagement.authentication.requests

import com.google.gson.annotations.SerializedName

data class ProductRequest(

    @SerializedName("product_id")
    val productId: String?,

    @SerializedName("brand_name")
    val brandName: String?,

    @SerializedName("category_name")
    val categoryName: String?,

    @SerializedName("model_name")
    val modelName: String?,

    @SerializedName("warehouse_id")
    val warehouseId: String?,

    @SerializedName("storage_id")
    val storageId: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("basePrice")
    val basePrice: Int?,

    @SerializedName("wholesale_price")
    val wholeSalePrice: Int?,

    @SerializedName("barcode")
    val barcode: String?,
)
