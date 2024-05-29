package com.example.audioshopinventorymanagement.room

interface ProductDatabaseRepository {
    suspend fun insert(product : ProductEntity)
    suspend fun delete(product : ProductEntity)
    suspend fun deleteAllProduct()
    suspend fun updateProductId(id: Int, productId: String)
    suspend fun updateName(id: Int, productName: String)
    suspend fun updateType(id: Int, productType: String)
    suspend fun updateBasePrice(id: Int, basePrice: String)
    suspend fun updateWholeSalePrice(id: Int, wholeSalePrice: String)
    suspend fun updateWarehouseId(id: Int, warehouseId: String)
    suspend fun updateStorageId(id: Int, storageId: String)
    suspend fun updateBarcode(id: Int, barcode: String)
    suspend fun getAllProducts() : MutableList<ProductEntity>
    suspend fun getProductByBarcode(barcode: String) : ProductEntity
}