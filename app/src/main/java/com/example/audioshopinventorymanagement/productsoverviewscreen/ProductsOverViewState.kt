package com.example.audioshopinventorymanagement.productsoverviewscreen

import com.example.audioshopinventorymanagement.api.responses.ProductDetails

data class ProductsOverViewState(

    var productList: MutableList<ProductDetails> = ArrayList(),
    var searchedProductList: MutableList<ProductDetails> = ArrayList(),
    val isExpandCard: Boolean = false,
    val searchFieldValue: String = "",
    val allMatches: Int = 0,

    val isShowErrorDialog : Boolean = false,
    val textShowErrorDialog : String = "",
)
