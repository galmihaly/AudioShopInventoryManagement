package com.example.audioshopinventorymanagement.di

import android.content.Context
import androidx.room.Room
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.room.daos.ProductDAO
import com.example.audioshopinventorymanagement.room.ProductDatabase
import com.example.audioshopinventorymanagement.room.daos.BrandDAO
import com.example.audioshopinventorymanagement.room.daos.CategoryDAO
import com.example.audioshopinventorymanagement.room.daos.ModelDAO
import com.example.audioshopinventorymanagement.room.repositories.ProductDatabaseRepository
import com.example.audioshopinventorymanagement.room.repositories.ProductDatabaseRepositoryImpl
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
        name = context.getString(R.string.DATABASE_NAME)
    )
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideProductDatabaseRepository(
        productDAO: ProductDAO,
        brandDAO: BrandDAO,
        categoryDAO: CategoryDAO,
        modelDAO: ModelDAO,
    ) : ProductDatabaseRepository {
        return ProductDatabaseRepositoryImpl(productDAO, brandDAO, categoryDAO, modelDAO)
    }

    @Singleton
    @Provides
    fun provideProductDAO(db : ProductDatabase) = db.getProductDAO()

    @Singleton
    @Provides
    fun provideBrandDAO(db : ProductDatabase) = db.getBrandDAO()

    @Singleton
    @Provides
    fun provideCategoryDAO(db : ProductDatabase) = db.getCategoryDAO()

    @Singleton
    @Provides
    fun provideModelDAO(db : ProductDatabase) = db.getModelDAO()
}