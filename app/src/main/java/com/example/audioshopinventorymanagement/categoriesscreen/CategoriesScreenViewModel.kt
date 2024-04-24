package com.example.audioshopinventorymanagement.categoriesscreen

import androidx.lifecycle.ViewModel
import com.example.audioshopinventorymanagement.navigation.AppNavigator
import com.example.audioshopinventorymanagement.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesScreenViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

    fun onNavigateToStartScreen() {
        appNavigator.tryNavigateTo(Destination.StartScreen.fullRoute)
    }

    fun onNavigateToOneCategoryScreen() {
        appNavigator.tryNavigateTo(Destination.OneCategoryScreen.fullRoute)
    }

    fun onNavigateToWareHousesScreen() {
        appNavigator.tryNavigateTo(Destination.WareHousesScreen.fullRoute)
    }
}