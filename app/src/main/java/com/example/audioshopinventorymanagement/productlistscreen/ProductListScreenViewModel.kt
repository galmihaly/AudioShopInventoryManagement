package com.example.audioshopinventorymanagement.productlistscreen

import androidx.lifecycle.ViewModel
import com.example.audioshopinventorymanagement.navigation.AppNavigator
import com.example.audioshopinventorymanagement.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductListScreenViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

    fun onNavigateToProductListScreen() {
        appNavigator.tryNavigateTo(Destination.ProductListScreen.fullRoute)
    }

    fun onNavigateToModifyItemScreen() {
        appNavigator.tryNavigateTo(Destination.NewItemScreen.fullRoute)
    }

    fun onNavigateToStartScreen() {
        appNavigator.tryNavigateTo(Destination.StartScreen.fullRoute)
    }
}