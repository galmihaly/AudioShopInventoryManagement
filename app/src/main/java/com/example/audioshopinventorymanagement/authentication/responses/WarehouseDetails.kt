package com.example.audioshopinventorymanagement.authentication.responses

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Required

data class WarehouseDetails(

    @Required
    @SerializedName("id")
    val id: Int? = null,

    @Required
    @SerializedName("warehouse_id")
    val warehouseId: String? = "",

    @Required
    @SerializedName("name")
    val name: String? = "",

    @Required
    @SerializedName("address")
    val address: String? = "",

    @Required
    @SerializedName("current_stock_capacity")
    val currentStockCapacity: Int? = 0,

    @Required
    @SerializedName("stock_max_capacity")
    val stockMaxCapacity: Int? = 0,
)
