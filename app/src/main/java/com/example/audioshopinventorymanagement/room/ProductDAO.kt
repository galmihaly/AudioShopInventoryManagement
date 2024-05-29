package com.example.audioshopinventorymanagement.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productEntity: ProductEntity)

    @Delete
    suspend fun delete(productEntity: ProductEntity)

    @Query("DELETE FROM Products")
    suspend fun deleteAllProduct()

    @Query("SELECT * FROM Products")
    suspend fun getAllProducts(): MutableList<ProductEntity>

    @Query("SELECT * FROM Products WHERE barcode = :barcode")
    suspend fun getProductByBarcode(barcode: String): ProductEntity

    @Query("UPDATE Products SET product_id = :productId WHERE id = :id")
    suspend fun updateProductId(id: Int, productId: String)

    @Query("UPDATE Products SET product_name = :productName WHERE id = :id")
    suspend fun updateName(id: Int, productName: String)

    @Query("UPDATE Products SET product_type = :productType WHERE id = :id")
    suspend fun updateType(id: Int, productType: String)

    @Query("UPDATE Products SET base_price = :basePrice WHERE id = :id")
    suspend fun updateBasePrice(id: Int, basePrice: String)

    @Query("UPDATE Products SET wholesale_price = :wholeSalePrice WHERE id = :id")
    suspend fun updateWholeSalePrice(id: Int, wholeSalePrice: String)

    @Query("UPDATE Products SET warehouse_id = :warehouseId WHERE id = :id")
    suspend fun updateWarehouseId(id: Int, warehouseId: String)

    @Query("UPDATE Products SET storage_id = :storageId WHERE id = :id")
    suspend fun updateStorageId(id: Int, storageId: String)

    @Query("UPDATE Products SET barcode = :barcode WHERE id = :id")
    suspend fun updateBarcode(id: Int, barcode: String)
}