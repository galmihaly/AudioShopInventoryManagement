package com.example.audioshopinventorymanagement.room.repositories

import com.example.audioshopinventorymanagement.room.entities.BrandEntity
import com.example.audioshopinventorymanagement.room.entities.CategoryEntity
import com.example.audioshopinventorymanagement.room.entities.ModelEntity
import com.example.audioshopinventorymanagement.room.entities.ProductEntity

interface ProductDatabaseRepository {
    suspend fun insertProduct(product : ProductEntity)
    suspend fun insertBrand(brand : BrandEntity)
    suspend fun insertCategory(category : CategoryEntity)
    suspend fun insertModel(model : ModelEntity)

    suspend fun deleteProduct(product : ProductEntity)
    suspend fun deleteBrand(brand: BrandEntity)
    suspend fun deleteCategory(category: CategoryEntity)
    suspend fun deleteModel(model: ModelEntity)
    suspend fun deleteAllProduct()
    suspend fun deleteAllBrand()
    suspend fun deleteAllCategory()
    suspend fun deleteAllModel()
    suspend fun getAllProducts() : MutableList<ProductEntity>
    suspend fun getAllBrands() : MutableList<BrandEntity>
    suspend fun getAllCategories() : MutableList<CategoryEntity>
    suspend fun getAllModels() : MutableList<ModelEntity>
    suspend fun getProductByBarcode(barcode: String) : ProductEntity
    suspend fun getBrandById(brandId: String) : BrandEntity
    suspend fun getBrandByName(brandName: String) : BrandEntity
    suspend fun getCategoryById(categoryId: String) : CategoryEntity
    suspend fun getCategoryByName(categoryName: String) : CategoryEntity
    suspend fun getModelById(modelId: String) : ModelEntity
    suspend fun getModelByName(modelName: String) : ModelEntity
    suspend fun updateProductId(barcode: String, productId: String)
    suspend fun updateProductName(barcode: String, productName: String)
    suspend fun updateBrandId(barcode: String, brandId: String)
    suspend fun updateBrandName(barcode: String, brandName: String)
    suspend fun updateCategoryId(barcode: String, categoryId: String)
    suspend fun updateCategoryName(barcode: String, categoryName: String)
    suspend fun updateModelId(barcode: String, modelId: String)
    suspend fun updateModelName(barcode: String, modelName: String)
    suspend fun updateBasePrice(barcode: String, basePrice: String)
    suspend fun updateWholeSalePrice(barcode: String, wholeSalePrice: String)
    suspend fun updateWarehouseId(barcode: String, warehouseId: String)
    suspend fun updateStorageId(barcode: String, storageId: String)
    suspend fun updateBarcode(oldBarcode: String, newBarcode: String)
    suspend fun updateRecorderName(barcode: String, recorderName: String)
    suspend fun updateDeviceId(barcode: String, deviceId: String)
}