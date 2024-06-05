package com.example.audioshopinventorymanagement.authentication.responses

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Required

data class StoragesDetails(

    @Required
    @SerializedName("id")
    val id: Int? = 0,

    @Required
    @SerializedName("warehouse_id")
    val warehouseId: Int? = 0,

    @Required
    @SerializedName("storage_id")
    val storageId: String? = "",

    @Required
    @SerializedName("quantity")
    val quantity: Int? = 0,

    @Required
    @SerializedName("max_quantity")
    val maxQuantity: Int? = 0,
)
