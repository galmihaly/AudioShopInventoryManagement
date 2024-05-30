package com.example.audioshopinventorymanagement.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Products")
data class ProductEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "product_id")
    val productId: String? = "",

    @ColumnInfo(name = "product_name")
    val productName: String? = "",

    @ColumnInfo(name = "brand_id")
    val brandId: String? = "",

    @ColumnInfo(name = "brand_name")
    val brandName: String? = "",

    @ColumnInfo(name = "category_id")
    val categoryId: String? = "",

    @ColumnInfo(name = "category_name")
    val categoryName: String? = "",

    @ColumnInfo(name = "model_id")
    val modelId: String? = "",

    @ColumnInfo(name = "model_name")
    val modelName: String? = "",

    @ColumnInfo(name = "base_price")
    val basePrice: String? = "",

    @ColumnInfo(name = "wholesale_price")
    val wholeSalePrice: String? = "",

    @ColumnInfo(name = "warehouse_id")
    val warehouseId: String? = "",

    @ColumnInfo(name = "storage_id")
    val storageId: String? = "",

    @ColumnInfo(name = "recorder_name")
    val recorderName: String? = "",

    @ColumnInfo(name = "device_id")
    val deviceId: String? = "",

    @ColumnInfo(name = "barcode")
    val barcode: String? = "",
)