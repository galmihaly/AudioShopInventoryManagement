package com.example.audioshopinventorymanagement.navigation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) :ViewModel() {

    val navigationChannel = appNavigator.navigationChannel
}