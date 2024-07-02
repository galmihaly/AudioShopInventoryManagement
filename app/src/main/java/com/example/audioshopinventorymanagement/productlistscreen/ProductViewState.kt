package com.example.audioshopinventorymanagement.productlistscreen

import com.example.audioshopinventorymanagement.room.entities.ProductEntity

data class ProductViewState(
    var productList: MutableList<ProductEntity> = ArrayList(),
    val searchFieldValue: String = "",
    val isExpandCard: Boolean = false,
    val allMatches: Int = 0,

    val isShowErrorDialog : Boolean = false,
    val textShowErrorDialogId : Int = -1,
)