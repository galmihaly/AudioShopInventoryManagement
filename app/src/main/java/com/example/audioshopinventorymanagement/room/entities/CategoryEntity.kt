package com.example.audioshopinventorymanagement.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Required

@Entity(tableName = "Categories")
data class CategoryEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @Required
    @SerializedName("category_id")
    val categoryId: String? = "",

    @Required
    @SerializedName("name")
    val categoryName: String? = "",
)