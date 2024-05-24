package com.example.audioshopinventorymanagement.porductlist

import androidx.lifecycle.ViewModel
import com.example.audioshopinventorymanagement.navigation.AppNavigator
import com.example.audioshopinventorymanagement.navigation.Destination
import com.example.audioshopinventorymanagement.porductlist.newitemscreen.NewItemViewState
import com.example.audioshopinventorymanagement.porductlist.productlistscreen.ProductViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

    private val _productViewState = MutableStateFlow(ProductViewState())
    val productViewState = _productViewState.asStateFlow()

    private val _newItemViewState = MutableStateFlow(NewItemViewState())
    val newItemViewState = _newItemViewState.asStateFlow()

    fun onNavigateToProductListScreen() {
        appNavigator.tryNavigateTo(Destination.ProductListScreen.fullRoute)
    }

    fun onNavigateToNewItemScreen() {
        appNavigator.tryNavigateTo(Destination.NewItemScreen.fullRoute)
    }

    fun onNavigateToModifyItemScreen() {
        appNavigator.tryNavigateTo(Destination.ModifyItemScreen.fullRoute)
    }

    fun onNavigateToStartScreen() {
        appNavigator.tryNavigateTo(Destination.StartScreen.fullRoute)
    }
}