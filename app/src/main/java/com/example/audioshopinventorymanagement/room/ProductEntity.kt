package com.example.audioshopinventorymanagement.room

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

    @ColumnInfo(name = "product_type")
    val productType: String? = "",

    @ColumnInfo(name = "base_price")
    val basePrice: String? = "",

    @ColumnInfo(name = "wholesale_price")
    val wholeSalePrice: String? = "",

    @ColumnInfo(name = "warehouse_id")
    val warehouseId: String? = "",

    @ColumnInfo(name = "storage_id")
    val storageId: String? = "",

    @ColumnInfo(name = "barcode")
    val barcode: String? = "",
)