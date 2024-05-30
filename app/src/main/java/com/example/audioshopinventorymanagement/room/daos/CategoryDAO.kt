package com.example.audioshopinventorymanagement.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.audioshopinventorymanagement.room.entities.CategoryEntity

@Dao
interface CategoryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categoryEntity: CategoryEntity)

    @Delete
    suspend fun delete(categoryEntity: CategoryEntity)

    @Query("DELETE FROM Categories")
    suspend fun deleteAll()

    @Query("SELECT * FROM Categories")
    suspend fun getAll(): MutableList<CategoryEntity>

    @Query("SELECT * FROM Categories WHERE categoryId = :categoryId")
    suspend fun getByCategoryId(categoryId: String): CategoryEntity

    @Query("SELECT * FROM Categories WHERE categoryName = :categoryName")
    suspend fun getByName(categoryName: String): CategoryEntity
}