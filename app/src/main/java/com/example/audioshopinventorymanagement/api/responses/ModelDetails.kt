package com.example.audioshopinventorymanagement.api.responses

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Required

data class ModelDetails(

    @Required
    @SerializedName("model_id")
    val modelId: String? = "",

    @Required
    @SerializedName("name")
    val modelName: String? = "",
)