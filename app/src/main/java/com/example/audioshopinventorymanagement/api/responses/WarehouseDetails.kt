package com.example.audioshopinventorymanagement.api.responses

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Required

data class WarehouseDetails(

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

    @Required
    @SerializedName("netto_value")
    val nettoValue: Int? = 0,

    @Required
    @SerializedName("brutto_value")
    val bruttoValue: Int? = 0,
)
