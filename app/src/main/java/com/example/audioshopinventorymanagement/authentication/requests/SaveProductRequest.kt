package com.example.audioshopinventorymanagement.authentication.requests

import com.google.gson.annotations.SerializedName

data class SaveProductRequest(

    @SerializedName("brand_id")
    val brandId: String?,

    @SerializedName("category_id")
    val categoryId: String?,

    @SerializedName("model_id")
    val modelId: String?,

    @SerializedName("warehouse_id")
    val warehouseId: String?,

    @SerializedName("storage_id")
    val storageId: String?,

    @SerializedName("base_price")
    val basePrice: Int?,

    @SerializedName("wholesale_price")
    val wholeSalePrice: Int?,

    @SerializedName("barcode")
    val barcode: String?,
)