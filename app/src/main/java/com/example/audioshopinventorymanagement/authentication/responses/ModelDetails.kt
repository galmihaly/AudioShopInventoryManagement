package com.example.audioshopinventorymanagement.authentication.responses

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Required

data class ModelDetails(

    @Required
    @SerializedName("name")
    val modelName: String?,
)