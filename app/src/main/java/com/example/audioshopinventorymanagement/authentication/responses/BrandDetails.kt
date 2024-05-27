package com.example.audioshopinventorymanagement.authentication.responses

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Required

data class BrandDetails(

    @Required
    @SerializedName("brand_id")
    val brandId: String?,

    @Required
    @SerializedName("name")
    val brandName: String?,
)