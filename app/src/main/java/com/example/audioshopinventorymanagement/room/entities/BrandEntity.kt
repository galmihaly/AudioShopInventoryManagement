package com.example.audioshopinventorymanagement.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Required

@Entity(tableName = "Brands")
data class BrandEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @Required
    @SerializedName("brand_id")
    val brandId: String? = "",

    @Required
    @SerializedName("name")
    val brandName: String? = "",
)