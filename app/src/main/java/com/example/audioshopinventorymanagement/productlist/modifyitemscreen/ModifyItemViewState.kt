package com.example.audioshopinventorymanagement.productlist.modifyitemscreen

import com.example.audioshopinventorymanagement.authentication.responses.BrandDetails
import com.example.audioshopinventorymanagement.authentication.responses.CategoryDetails
import com.example.audioshopinventorymanagement.authentication.responses.ModelDetails

data class ModifyItemViewState(
    val warehouseTFValue : String = "",
    val storageTFValue : String = "",

    val brandDDValue : String = "",
    val brandDDList : List<String> = ArrayList(),
    val brandDetailsList : List<BrandDetails> = ArrayList(),
    val brandExpandedDD : Boolean = false,

    val categoryDDValue : String = "",
    val categoryDDList : List<String> = ArrayList(),
    val categoryDetailsList : List<CategoryDetails> = ArrayList(),
    val categoryExpandedDD : Boolean = false,

    val modelDDValue : String = "",
    val modelDDList : List<String> = ArrayList(),
    val modelDetailsList : List<ModelDetails> = ArrayList(),
    val modelExpandedDD : Boolean = false,

    val barcodeTFValue : String = "",
    val basePriceTFValue : String = "",
    val wholeSalePriceTFValue : String = "",

    val isShowErrorDialog : Boolean = false,
    val textShowErrorDialog : String = "",

    val modifyItemScreenArgument : String = ""
)