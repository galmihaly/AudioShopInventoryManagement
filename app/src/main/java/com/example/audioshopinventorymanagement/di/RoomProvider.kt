package com.example.audioshopinventorymanagement.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.room.Room
import com.example.audioshopinventorymanagement.jwttokensdatastore.JwtTokenRepository
import com.example.audioshopinventorymanagement.jwttokensdatastore.JwtTokenRepositoryImpl
import com.example.audioshopinventorymanagement.jwttokensdatastore.JwtTokens
import com.example.audioshopinventorymanagement.room.ProductDAO
import com.example.audioshopinventorymanagement.room.ProductDatabase
import com.example.audioshopinventorymanagement.room.ProductDatabaseRepository
import com.example.audioshopinventorymanagement.room.ProductDatabaseRepositoryImpl
import com.example.audioshopinventorymanagement.room.ProductEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomProvider {

    @Singleton
    @Provides
    fun provideProductDatabase(
        @ApplicationContext context : Context
    ) = Room.databaseBuilder(
        context = context,
        klass = ProductDatabase::class.java,
        name = "ProductDatabase"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideProductDatabaseRepository(
        productDAO: ProductDAO,
    ) : ProductDatabaseRepository {
        return ProductDatabaseRepositoryImpl(productDAO)
    }

    @Singleton
    @Provides
    fun provideProductDAO(db : ProductDatabase) = db.getProductDAO()
}