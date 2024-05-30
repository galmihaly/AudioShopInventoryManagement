package com.example.audioshopinventorymanagement.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.audioshopinventorymanagement.room.entities.ModelEntity

@Dao
interface ModelDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(modelEntity: ModelEntity)

    @Delete
    suspend fun delete(modelEntity: ModelEntity)

    @Query("DELETE FROM Models")
    suspend fun deleteAll()

    @Query("SELECT * FROM Models")
    suspend fun getAll(): MutableList<ModelEntity>

    @Query("SELECT * FROM Models WHERE modelId = :modelId")
    suspend fun getByModelId(modelId: String): ModelEntity

    @Query("SELECT * FROM Models WHERE modelName = :modelName")
    suspend fun getByName(modelName: String): ModelEntity
}