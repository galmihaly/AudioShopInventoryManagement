package com.example.audioshopinventorymanagement.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.audioshopinventorymanagement.room.daos.BrandDAO
import com.example.audioshopinventorymanagement.room.daos.CategoryDAO
import com.example.audioshopinventorymanagement.room.daos.ModelDAO
import com.example.audioshopinventorymanagement.room.daos.ProductDAO
import com.example.audioshopinventorymanagement.room.entities.BrandEntity
import com.example.audioshopinventorymanagement.room.entities.CategoryEntity
import com.example.audioshopinventorymanagement.room.entities.ModelEntity
import com.example.audioshopinventorymanagement.room.entities.ProductEntity

@Database(entities = arrayOf(ProductEntity::class, BrandEntity::class, CategoryEntity::class, ModelEntity::class), version = 1, exportSchema = true)
abstract class ProductDatabase : RoomDatabase(){
    abstract fun getProductDAO(): ProductDAO
    abstract fun getBrandDAO(): BrandDAO
    abstract fun getCategoryDAO(): CategoryDAO
    abstract fun getModelDAO(): ModelDAO
}