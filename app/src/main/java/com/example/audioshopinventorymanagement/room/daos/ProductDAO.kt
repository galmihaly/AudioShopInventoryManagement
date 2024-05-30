package com.example.audioshopinventorymanagement.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.audioshopinventorymanagement.room.entities.ProductEntity

@Dao
interface ProductDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productEntity: ProductEntity)

    @Delete
    suspend fun delete(productEntity: ProductEntity)

    @Query("DELETE FROM Products")
    suspend fun deleteAll()

    @Query("SELECT * FROM Products")
    suspend fun getAll(): MutableList<ProductEntity>

    @Query("SELECT * FROM Products WHERE barcode = :barcode")
    suspend fun getByBarcode(barcode: String): ProductEntity

    @Query("UPDATE Products SET product_id = :productId WHERE barcode = :barcode")
    suspend fun updateProductId(barcode: String, productId: String)

    @Query("UPDATE Products SET product_name = :productName WHERE barcode = :barcode")
    suspend fun updateName(barcode: String, productName: String)

    @Query("UPDATE Products SET brand_id = :brandId WHERE barcode = :barcode")
    suspend fun updateBrandId(barcode: String, brandId: String)

    @Query("UPDATE Products SET brand_name = :brandName WHERE barcode = :barcode")
    suspend fun updateBrandName(barcode: String, brandName: String)

    @Query("UPDATE Products SET category_id = :categoryId WHERE barcode = :barcode")
    suspend fun updateCategoryId(barcode: String, categoryId: String)

    @Query("UPDATE Products SET category_name = :categoryName WHERE barcode = :barcode")
    suspend fun updateCategoryName(barcode: String, categoryName: String)

    @Query("UPDATE Products SET model_id = :modelId WHERE barcode = :barcode")
    suspend fun updateModelId(barcode: String, modelId: String)

    @Query("UPDATE Products SET model_name = :modelName WHERE barcode = :barcode")
    suspend fun updateModelName(barcode: String, modelName: String)

    @Query("UPDATE Products SET base_price = :basePrice WHERE barcode = :barcode")
    suspend fun updateBasePrice(barcode: String, basePrice: String)

    @Query("UPDATE Products SET wholesale_price = :wholeSalePrice WHERE barcode = :barcode")
    suspend fun updateWholeSalePrice(barcode: String, wholeSalePrice: String)

    @Query("UPDATE Products SET warehouse_id = :warehouseId WHERE barcode = :barcode")
    suspend fun updateWarehouseId(barcode: String, warehouseId: String)

    @Query("UPDATE Products SET storage_id = :storageId WHERE barcode = :barcode")
    suspend fun updateStorageId(barcode: String, storageId: String)

    @Query("UPDATE Products SET recorder_name = :recorderName WHERE barcode = :barcode")
    suspend fun updateRecorderName(barcode: String, recorderName: String)

    @Query("UPDATE Products SET device_id = :deviceId WHERE barcode = :barcode")
    suspend fun updateDeviceId(barcode: String, deviceId: String)

    @Query("UPDATE Products SET barcode = :newBarcode WHERE barcode = :oldBarcode")
    suspend fun updateBarcode(oldBarcode: String, newBarcode: String)
}