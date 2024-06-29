package com.example.audioshopinventorymanagement.warehousesscreen

import androidx.compose.ui.graphics.Color
import com.example.audioshopinventorymanagement.api.responses.WarehouseDetails
import com.example.audioshopinventorymanagement.ui.theme.BLUE
import com.example.audioshopinventorymanagement.ui.theme.GREEN

data class WarehouseViewState(
    var warehouseList: MutableList<WarehouseDetails> = ArrayList(),
    var currentCapacityColor : Color = BLUE,
    var maxCapacityColor : Color = GREEN,

    val isShowErrorDialog : Boolean = false,
    val textShowErrorDialog : String = "",
)
