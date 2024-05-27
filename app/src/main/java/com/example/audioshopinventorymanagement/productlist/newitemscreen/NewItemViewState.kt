package com.example.audioshopinventorymanagement.productlist.newitemscreen

data class NewItemViewState(
    val warehouseTFValue : String = "",
    val storageTFValue : String = "",

    val brandDropDownValue : String = "",
    val brandDropDownList : List<String> = ArrayList(),
    val brandExpandedDropDown : Boolean = false,

    val categoryDropDownValue : String = "",
    val categoryDropDownList : List<String> = ArrayList(),
    val categoryExpandedDropDown : Boolean = false,

    val modelDropDownValue : String = "",
    val modelDropDownList : List<String> = ArrayList(),
    val modelExpandedDropDown : Boolean = false,

    val barcodeTFValue : String = "",
    val basePriceTFValue : String = "",
    val wholeSalePriceTFValue : String = "",

    val isShowErrorDialog : Boolean = false,
    val textShowErrorDialog : String = ""
)