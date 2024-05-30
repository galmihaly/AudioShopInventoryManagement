package com.example.audioshopinventorymanagement.room.repositories

import com.example.audioshopinventorymanagement.room.daos.BrandDAO
import com.example.audioshopinventorymanagement.room.daos.CategoryDAO
import com.example.audioshopinventorymanagement.room.daos.ModelDAO
import com.example.audioshopinventorymanagement.room.daos.ProductDAO
import com.example.audioshopinventorymanagement.room.entities.BrandEntity
import com.example.audioshopinventorymanagement.room.entities.CategoryEntity
import com.example.audioshopinventorymanagement.room.entities.ModelEntity
import com.example.audioshopinventorymanagement.room.entities.ProductEntity
import javax.inject.Inject

class ProductDatabaseRepositoryImpl @Inject constructor(
    private val productDao: ProductDAO,
    private val brandDao: BrandDAO,
    private val categoryDao: CategoryDAO,
    private val modelDao: ModelDAO,
) : ProductDatabaseRepository {

    override suspend fun insertProduct(product : ProductEntity){
        productDao.insert(product)
    }

    override suspend fun insertBrand(brand: BrandEntity) {
        brandDao.insert(brand)
    }

    override suspend fun insertCategory(category: CategoryEntity) {
        categoryDao.insert(category)
    }

    override suspend fun insertModel(model: ModelEntity) {
        modelDao.insert(model)
    }

    override suspend fun deleteProduct(product : ProductEntity){
        productDao.delete(product)
    }

    override suspend fun deleteBrand(brand: BrandEntity) {
        brandDao.delete(brand)
    }

    override suspend fun deleteCategory(category: CategoryEntity) {
        categoryDao.delete(category)
    }

    override suspend fun deleteModel(model: ModelEntity) {
        modelDao.delete(model)
    }

    override suspend fun deleteAllProduct() {
        productDao.deleteAll()
    }

    override suspend fun deleteAllBrand() {
        brandDao.deleteAll()
    }

    override suspend fun deleteAllCategory() {
        categoryDao.deleteAll()
    }

    override suspend fun deleteAllModel() {
        modelDao.deleteAll()
    }

    override suspend fun getAllProducts(): MutableList<ProductEntity> {
        return productDao.getAll()
    }

    override suspend fun getAllBrands(): MutableList<BrandEntity> {
        return brandDao.getAll()
    }

    override suspend fun getAllCategories(): MutableList<CategoryEntity> {
        return categoryDao.getAll()
    }

    override suspend fun getAllModels(): MutableList<ModelEntity> {
        return modelDao.getAll()
    }

    override suspend fun getProductByBarcode(barcode: String): ProductEntity {
        return productDao.getByBarcode(barcode)
    }

    override suspend fun getBrandById(brandId: String): BrandEntity {
        return brandDao.getByBrandId(brandId)
    }

    override suspend fun getBrandByName(brandName: String): BrandEntity {
        return brandDao.getByName(brandName)
    }

    override suspend fun getCategoryById(categoryId: String): CategoryEntity {
        return categoryDao.getByCategoryId(categoryId)
    }

    override suspend fun getCategoryByName(categoryName: String): CategoryEntity {
        return categoryDao.getByName(categoryName)
    }

    override suspend fun getModelById(modelId: String): ModelEntity {
        return modelDao.getByModelId(modelId)
    }

    override suspend fun getModelByName(modelName: String): ModelEntity {
        return modelDao.getByName(modelName)
    }

    override suspend fun updateProductId(barcode: String, productId: String) {
        productDao.updateProductId(barcode, productId)
    }

    override suspend fun updateProductName(barcode: String, productName: String) {
        productDao.updateName(barcode, productName)
    }

    override suspend fun updateBrandId(barcode: String, brandId: String) {
        productDao.updateBrandId(barcode, brandId)
    }

    override suspend fun updateBrandName(barcode: String, brandName: String) {
        productDao.updateBrandName(barcode, brandName)
    }

    override suspend fun updateCategoryId(barcode: String, categoryId: String) {
        productDao.updateCategoryId(barcode, categoryId)
    }

    override suspend fun updateCategoryName(barcode: String, categoryName: String) {
        productDao.updateCategoryName(barcode, categoryName)
    }

    override suspend fun updateModelId(barcode: String, modelId: String) {
        productDao.updateModelId(barcode, modelId)
    }

    override suspend fun updateModelName(barcode: String, modelName: String) {
        productDao.updateModelName(barcode, modelName)
    }

    override suspend fun updateBasePrice(barcode: String, basePrice: String) {
        productDao.updateBasePrice(barcode, basePrice)
    }

    override suspend fun updateWholeSalePrice(barcode: String, wholeSalePrice: String) {
        productDao.updateWholeSalePrice(barcode, wholeSalePrice)
    }

    override suspend fun updateWarehouseId(barcode: String, warehouseId: String) {
        productDao.updateWarehouseId(barcode, warehouseId)
    }

    override suspend fun updateStorageId(barcode: String, storageId: String) {
        productDao.updateStorageId(barcode, storageId)
    }

    override suspend fun updateBarcode(oldBarcode: String, newBarcode: String) {
        productDao.updateBarcode(oldBarcode, newBarcode)
    }

    override suspend fun updateRecorderName(barcode: String, recorderName: String) {
        productDao.updateRecorderName(barcode, recorderName)
    }

    override suspend fun updateDeviceId(barcode: String, deviceId: String) {
        productDao.updateDeviceId(barcode, deviceId)
    }
}