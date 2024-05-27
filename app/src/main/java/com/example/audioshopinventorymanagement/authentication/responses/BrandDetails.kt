package com.example.audioshopinventorymanagement.authentication.responses

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Required

data class BrandDetails(

    @Required
    @SerializedName("name")
    val brandName: String?,
)