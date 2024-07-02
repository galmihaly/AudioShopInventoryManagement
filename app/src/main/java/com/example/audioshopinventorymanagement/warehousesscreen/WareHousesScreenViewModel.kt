package com.example.audioshopinventorymanagement.warehousesscreen

import android.content.res.Resources
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
class WareHousesScreenViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val productApiRepository: ProductApiRepository
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

    private val _viewState = MutableStateFlow(WarehouseViewState())
    val viewState = _viewState.asStateFlow()

    init {
        getAllWarehouseFromApi()
    }

    private fun getAllWarehouseFromApi(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = productApiRepository.getAllWarehouse()

            when (response){
                is ProductApiResponse.WarehouseSuccess -> {
                    if(response.data.statusCode == 200){
                        _viewState.update {
                            it.copy(
                                warehouseList = response.data.warehouseDetails!!.toMutableList()
                            )
                        }
                    }
                }
                is ProductApiResponse.WarehouseError -> {
                    if(response.data.statusCode == 401){
                        onDialogShow(R.string.WAREHOUSE_API_READ_FAILED)
                    }
                }
                is ProductApiResponse.Exception -> {
                    onDialogShow(response.exceptionMessage)
                }
                else -> {}
            }
        }
    }

    fun onNavigateToStartScreen() {
        appNavigator.tryNavigateTo(Destination.StartScreen.fullRoute)
    }

    fun onNavigateToStocksScreen(warehouseId : String) {
        appNavigator.tryNavigateTo(Destination.StoragesScreen.passParameters(warehouseId))
    }

    private fun onDialogShow(dialogTextId : Int){
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    textShowErrorDialogId = dialogTextId,
                    isShowErrorDialog = true
                )
            }
        }
    }

    fun onDialogDismiss() {
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    textShowErrorDialogId = -1,
                    isShowErrorDialog = false
                )
            }
        }
    }
}