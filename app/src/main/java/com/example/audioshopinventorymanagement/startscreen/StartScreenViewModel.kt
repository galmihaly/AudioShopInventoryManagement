package com.example.audioshopinventorymanagement.startscreen

import androidx.lifecycle.ViewModel
import com.example.audioshopinventorymanagement.navigation.AppNavigator
import com.example.audioshopinventorymanagement.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartScreenViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

    fun onNavigateToWareHousesScreen() {
        appNavigator.tryNavigateTo(Destination.WareHousesScreen.fullRoute)
    }

    fun onNavigateToProductListScreen() {
        appNavigator.tryNavigateTo(Destination.ProductListScreen.fullRoute)
    }

    fun onNavigateToLoginScreen() {
        appNavigator.tryNavigateTo(Destination.LoginScreen.fullRoute)
    }
}