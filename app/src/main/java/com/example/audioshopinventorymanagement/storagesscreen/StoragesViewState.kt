package com.example.audioshopinventorymanagement.storagesscreen

import androidx.compose.ui.graphics.Color
import com.example.audioshopinventorymanagement.authentication.responses.StoragesDetails
import com.example.audioshopinventorymanagement.ui.theme.BLUE
import com.example.audioshopinventorymanagement.ui.theme.GREEN

data class StoragesViewState(
    var storagesList: MutableList<StoragesDetails> = ArrayList(),
    var searchedStoragesList: MutableList<StoragesDetails> = ArrayList(),
    var currentCapacityColor : Color = BLUE,
    var maxCapacityColor : Color = GREEN,

    val searchFieldValue: String = "",
    val allMatches: Int = 0,

    val isShowErrorDialog : Boolean = false,
    val textShowErrorDialog : String = "",
)
