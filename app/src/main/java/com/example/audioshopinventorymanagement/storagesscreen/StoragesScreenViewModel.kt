package com.example.audioshopinventorymanagement.storagesscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audioshopinventorymanagement.authentication.repositories.ProductApiRepository
import com.example.audioshopinventorymanagement.authentication.responses.sealed.ProductApiResponse
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
class StoragesScreenViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val productApiRepository: ProductApiRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

    private val _viewState = MutableStateFlow(StoragesViewState())
    val viewState = _viewState.asStateFlow()

    private val arg = checkNotNull(savedStateHandle[Destination.StocksScreenArguments.warehouseId.toString()] ?: "")

    init {
        getAllStorageFromApi()
    }

    private fun getAllStorageFromApi(){
        viewModelScope.launch(Dispatchers.IO) {

            if(arg != null){
                val response = productApiRepository.getStoragesByWarehouseId(arg)

                when (response){
                    is ProductApiResponse.StoragesSuccess -> {
                        if(response.data.statusCode == 200){
                            _viewState.update {
                                it.copy(
                                    storagesList = response.data.storagesDetails!!.toMutableList()
                                )
                            }
                        }
                    }
                    is ProductApiResponse.StoragesError -> {
                        if(response.data.statusCode == 401){
                            onDialogShow("Read of the storages has been failed!")
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

    fun onNavigateToStartScreen() {
        appNavigator.tryNavigateTo(Destination.StartScreen.fullRoute)
    }

    fun onNavigateToWareHousesScreen() {
        appNavigator.tryNavigateTo(Destination.WareHousesScreen.fullRoute)
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