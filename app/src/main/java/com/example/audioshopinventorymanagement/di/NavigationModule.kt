package com.example.audioshopinventorymanagement.di

import android.app.Application
import android.content.Context
import com.example.audioshopinventorymanagement.navigation.AppNavigator
import com.example.audioshopinventorymanagement.navigation.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Singleton
    @Binds
    fun bindAppNavigator(appNavigatorImpl: AppNavigatorImpl): AppNavigator
}