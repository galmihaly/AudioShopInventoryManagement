package com.example.audioshopinventorymanagement.warehousesscreen

import com.example.audioshopinventorymanagement.api.responses.WarehouseDetails

data class WarehouseViewState(
    var warehouseList: MutableList<WarehouseDetails> = ArrayList(),
    val isShowErrorDialog : Boolean = false,
    val textShowErrorDialog : String = "",
)
