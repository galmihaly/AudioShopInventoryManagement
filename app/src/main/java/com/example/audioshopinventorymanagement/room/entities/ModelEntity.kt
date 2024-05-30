package com.example.audioshopinventorymanagement.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Required

@Entity(tableName = "Models")
data class ModelEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @Required
    @SerializedName("model_id")
    val modelId: String? = "",

    @Required
    @SerializedName("name")
    val modelName: String? = "",
)
