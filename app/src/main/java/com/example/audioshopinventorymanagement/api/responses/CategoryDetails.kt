package com.example.audioshopinventorymanagement.api.responses

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Required

data class CategoryDetails(

    @Required
    @SerializedName("category_id")
    val categoryId: String? = "",

    @Required
    @SerializedName("name")
    val categoryName: String? = "",
)