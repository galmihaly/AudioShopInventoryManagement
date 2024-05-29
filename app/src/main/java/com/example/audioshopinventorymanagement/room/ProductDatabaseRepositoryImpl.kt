package com.example.audioshopinventorymanagement.room

import javax.inject.Inject

class ProductDatabaseRepositoryImpl @Inject constructor(
    private val productDao: ProductDAO
) : ProductDatabaseRepository {

    override suspend fun insert(product : ProductEntity){
        productDao.insert(product)
    }

    override suspend fun delete(product : ProductEntity){
        productDao.delete(product)
    }

    override suspend fun deleteAllProduct() {
        productDao.deleteAllProduct()
    }

    override suspend fun updateProductId(id: Int, productId: String) {
        productDao.updateProductId(id, productId)
    }

    override suspend fun updateName(id: Int, productName: String) {
        productDao.updateName(id, productName)
    }

    override suspend fun updateType(id: Int, productType: String) {
        productDao.updateType(id, productType)
    }

    override suspend fun updateBasePrice(id: Int, basePrice: String) {
        productDao.updateBasePrice(id, basePrice)
    }

    override suspend fun updateWholeSalePrice(id: Int, wholeSalePrice: String) {
        productDao.updateWholeSalePrice(id, wholeSalePrice)
    }

    override suspend fun updateWarehouseId(id: Int, warehouseId: String) {
        productDao.updateWarehouseId(id, warehouseId)
    }

    override suspend fun updateStorageId(id: Int, storageId: String) {
        productDao.updateStorageId(id, storageId)
    }

    override suspend fun updateBarcode(id: Int, barcode: String) {
        productDao.updateBarcode(id, barcode)
    }

    override suspend fun getAllProducts(): MutableList<ProductEntity> {
        return productDao.getAllProducts()
    }

    override suspend fun getProductByBarcode(barcode: String): ProductEntity {
        return productDao.getProductByBarcode(barcode)
    }
}