package com.example.audioshopinventorymanagement.productsoverviewscreen

import android.content.res.Resources
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.api.repositories.ProductApiRepository
import com.example.audioshopinventorymanagement.api.responses.sealed.ProductApiResponse
import com.example.audioshopinventorymanagement.navigation.AppNavigator
import com.example.audioshopinventorymanagement.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsOverviewScreenViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val productApiRepository: ProductApiRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val navigationChannel = appNavigator.navigationChannel

    private val _viewState = MutableStateFlow(ProductsOverViewState())
    val viewState = _viewState.asStateFlow()

    private val arg = checkNotNull(savedStateHandle[Destination.StoragesScreenArguments.warehouseId] ?: "")

    init {
        getAllProductsFromApi()
    }

    private fun getAllProductsFromApi(){
        viewModelScope.launch(Dispatchers.IO) {
            if(arg != ""){
                val response = productApiRepository.getProductsByStorageId(arg)

                when (response){
                    is ProductApiResponse.ProductListSuccess -> {
                        if(response.data.statusCode == 200){
                            val responseProductList = response.data.productDetails!!.toMutableList()
                            _viewState.update {
                                it.copy(
                                    productList = responseProductList,
                                    searchedProductList = responseProductList,
                                    allMatches = responseProductList.size
                                )
                            }
                        }
                    }
                    is ProductApiResponse.ProductListError -> {
                        if(response.data.statusCode == 400){
                            onDialogShow(Resources.getSystem().getString(R.string.PRODUCT_API_READ_FAILED))
                        }
                        else if(response.data.statusCode == 401){
                            onDialogShow(Resources.getSystem().getString(R.string.PRODUCT_API_READ_FAILED))
                        }
                    }
                    is ProductApiResponse.Exception -> {
                        onDialogShow(response.exceptionMessage)
                    }
                    else -> {}
                }
            }
        }
    }

    fun filterListBySearchValue(newSearchText: String) {
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    searchFieldValue = newSearchText
                )
            }

            val searchedProductList = _viewState.value.productList.filter {
                it.barcode!!.startsWith(newSearchText)
            }.toMutableList()

            _viewState.update {
                it.copy(
                    searchedProductList = searchedProductList,
                    allMatches = searchedProductList.size
                )
            }
        }
    }

    fun onNavigateToStoragesScreen() {
        appNavigator.tryNavigateBack(Destination.StoragesScreen.fullRoute)
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