package com.example.audioshopinventorymanagement.productlist.productlistscreen

data class ProductListItem(
    val barcode : String = "",
    val productId : String = "",
    val productName : String = "",
    val productType : String = "",
    val basePrice : Int = 0,
    val wholeSalePrice : Int = 0,
    val warehouseId : String = "",
    val storageId : String = "",
    val deviceId : String = "",
    val recorderName : String = "",
    val recordingDate : String = "",
)