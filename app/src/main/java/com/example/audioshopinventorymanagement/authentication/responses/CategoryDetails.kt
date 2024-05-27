package com.example.audioshopinventorymanagement.authentication.responses

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Required

data class CategoryDetails(

    @Required
    @SerializedName("name")
    val categoryName: String?,
)