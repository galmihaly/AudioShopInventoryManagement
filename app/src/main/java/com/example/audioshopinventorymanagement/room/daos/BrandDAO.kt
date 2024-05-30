package com.example.audioshopinventorymanagement.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.audioshopinventorymanagement.room.entities.BrandEntity

@Dao
interface BrandDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(brandEntity: BrandEntity)

    @Delete
    suspend fun delete(brandEntity: BrandEntity)

    @Query("DELETE FROM Brands")
    suspend fun deleteAll()

    @Query("SELECT * FROM Brands")
    suspend fun getAll(): MutableList<BrandEntity>

    @Query("SELECT * FROM Brands WHERE brandId = :brandId")
    suspend fun getByBrandId(brandId: String): BrandEntity

    @Query("SELECT * FROM Brands WHERE brandName = :brandName")
    suspend fun getByName(brandName: String): BrandEntity
}