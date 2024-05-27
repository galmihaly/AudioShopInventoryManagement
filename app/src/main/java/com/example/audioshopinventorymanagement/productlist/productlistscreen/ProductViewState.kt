package com.example.audioshopinventorymanagement.productlist.productlistscreen

data class ProductViewState(
    var productList: List<ProductListItem> = ArrayList(),
    val searchFieldValue: String = "",
    val isExpandCard: Boolean = false,
    val allMatches: Int = 0
)