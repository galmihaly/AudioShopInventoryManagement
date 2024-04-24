package com.example.audioshopinventorymanagement.onecategoryscreen

import androidx.lifecycle.ViewModel
import com.example.audioshopinventorymanagement.navigation.AppNavigator
import com.example.audioshopinventorymanagement.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OneCategoryScreenViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

    fun onNavigateToCategoriesScreen() {
        appNavigator.tryNavigateTo(Destination.OneCategoryScreen.fullRoute)
    }
}