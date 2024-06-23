package com.example.audioshopinventorymanagement.api.responses

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Required

data class ProductDetails(

    @Required
    @SerializedName("barcode")
    var barcode: String? = "",

    @Required
    @SerializedName("product_id")
    var productId: String? = "",

    @Required
    @SerializedName("product_name")
    var productName: String? = "",

    @Required
    @SerializedName("product_type")
    var productType: String? = "",

    @Required
    @SerializedName("base_price")
    var basePrice: Int? = 0,

    @Required
    @SerializedName("wholesale_price")
    var wholeSalePrice: Int? = 0,

    @Required
    @SerializedName("warehouse_id")
    var warehouseId: String? = "",

    @Required
    @SerializedName("storage_id")
    var storageId: String? = "",

    @Required
    @SerializedName("device_id")
    var deviceId: String? = "",

    @Required
    @SerializedName("recorder_name")
    var recorderName: String? = "",

    @Required
    @SerializedName("recording_date")
    var recordingDate: String? = "",

)
