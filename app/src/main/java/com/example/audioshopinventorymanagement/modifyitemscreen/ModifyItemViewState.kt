package com.example.audioshopinventorymanagement.modifyitemscreen

import androidx.compose.ui.graphics.Color
import com.example.audioshopinventorymanagement.room.entities.BrandEntity
import com.example.audioshopinventorymanagement.room.entities.CategoryEntity
import com.example.audioshopinventorymanagement.room.entities.ModelEntity
import com.example.audioshopinventorymanagement.ui.theme.GREEN

data class ModifyItemViewState(
    val warehouseTFValue : String = "",
    val warehouseTFIsEnabled: Boolean = true,
    val warehouseTFIsEnabledBorderColor: Color = GREEN,
    val warehouseTFIsEnabledTextColor: Color = Color.White,

    val storageTFValue : String = "",

    val brandDDValue : String = "",
    val brandDDList : List<String> = ArrayList(),
    val brandEntityList : List<BrandEntity> = ArrayList(),
    val brandExpandedDD : Boolean = false,

    val categoryDDValue : String = "",
    val categoryDDList : List<String> = ArrayList(),
    val categoryEntityList : List<CategoryEntity> = ArrayList(),
    val categoryExpandedDD : Boolean = false,

    val modelDDValue : String = "",
    val modelDDList : List<String> = ArrayList(),
    val modelEntityList : List<ModelEntity> = ArrayList(),
    val modelExpandedDD : Boolean = false,

    val barcodeTFValue : String = "",
    val basePriceTFValue : String = "",
    val wholeSalePriceTFValue : String = "",

    val isShowErrorDialogOne : Boolean = false,
    val textShowErrorDialogOneId : Int = -1,

    val isShowErrorDialogTwo : Boolean = false,
    val textShowErrorDialogTwoId : Int = -1,

    val modifyItemScreenArgument : String = ""
)