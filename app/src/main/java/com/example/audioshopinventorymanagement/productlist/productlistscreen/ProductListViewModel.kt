package com.example.audioshopinventorymanagement.productlist.productlistscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audioshopinventorymanagement.navigation.AppNavigator
import com.example.audioshopinventorymanagement.navigation.Destination
import com.example.audioshopinventorymanagement.room.ProductDatabaseRepository
import com.example.audioshopinventorymanagement.room.ProductEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val productDatabaseRepository: ProductDatabaseRepository
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

    private val _viewState = MutableStateFlow(ProductViewState())
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val productList = productDatabaseRepository.getAllProducts()

            _viewState.update {
                it.copy(
                    productList = productList
                )
            }
        }
    }

    fun deleteItemFromProductList(product: ProductEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            productDatabaseRepository.delete(product)

            val productList = productDatabaseRepository.getAllProducts()

            _viewState.update {
                it.copy(
                    productList = productList
                )
            }
        }
    }

    fun deleteProductList() {
        viewModelScope.launch(Dispatchers.IO) {
            productDatabaseRepository.deleteAllProduct()

            _viewState.update {
                it.copy(
                    productList = mutableListOf()
                )
            }
        }
    }

    fun onNavigateToNewItemScreen() {
        appNavigator.tryNavigateTo(Destination.NewItemScreen.fullRoute)
    }

    fun onNavigateToModifyItemScreen(barcode : String) {
        appNavigator.tryNavigateTo(Destination.ModifyItemScreen.passParameters(barcode))
    }

    fun onNavigateToStartScreen() {
        appNavigator.tryNavigateTo(Destination.StartScreen.fullRoute)
    }

    fun onDialogShow(dialogText : String){
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    textShowErrorDialog = dialogText,
                    isShowErrorDialog = true
                )
            }
        }
    }

    fun onDialogDismiss() {
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    textShowErrorDialog = "",
                    isShowErrorDialog = false
                )
            }
        }
    }
}