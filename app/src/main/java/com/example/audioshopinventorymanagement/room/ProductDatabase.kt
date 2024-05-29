package com.example.audioshopinventorymanagement.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ProductEntity::class), version = 1, exportSchema = true)
abstract class ProductDatabase : RoomDatabase(){
    abstract fun getProductDAO(): ProductDAO
}