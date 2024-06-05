package com.example.audioshopinventorymanagement.warehousesscreen

import com.example.audioshopinventorymanagement.authentication.responses.WarehouseDetails
import com.example.audioshopinventorymanagement.room.entities.ProductEntity

data class WarehouseViewState(
    var warehouseList: MutableList<WarehouseDetails> = ArrayList(),

    val isShowErrorDialog : Boolean = false,
    val textShowErrorDialog : String = "",
)
