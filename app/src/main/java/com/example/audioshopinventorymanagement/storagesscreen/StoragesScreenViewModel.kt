package com.example.audioshopinventorymanagement.storagesscreen

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
class StoragesScreenViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val productApiRepository: ProductApiRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

    private val _viewState = MutableStateFlow(StoragesViewState())
    val viewState = _viewState.asStateFlow()

    private val arg = checkNotNull(savedStateHandle[Destination.StoragesScreenArguments.warehouseId] ?: "")

    init {
        getAllStorageFromApi()
    }

    private fun getAllStorageFromApi(){
        viewModelScope.launch(Dispatchers.IO) {

            if(arg != "-1"){
                val response = productApiRepository.getStoragesByWarehouseId(arg)

                when (response){
                    is ProductApiResponse.StoragesSuccess -> {
                        if(response.data.statusCode == 200){
                            val responseStorageList = response.data.storagesDetails!!.toMutableList()
                            _viewState.update {
                                it.copy(
                                    storagesList = responseStorageList,
                                    searchedStoragesList = responseStorageList,
                                    allMatches = responseStorageList.size
                                )
                            }
                        }
                    }
                    is ProductApiResponse.StoragesError -> {
                        if(response.data.statusCode == 401){
                            onDialogShow(R.string.STORAGE_API_READ_FAILED)
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

            val searchedStoragesList = _viewState.value.storagesList.filter {
                it.storageId!!.startsWith(newSearchText)
            }.toMutableList()

            _viewState.update {
                it.copy(
                    searchedStoragesList = searchedStoragesList,
                    allMatches = searchedStoragesList.size
                )
            }
        }
    }

    fun onNavigateProductsOverviewScreen(storageId: String?) {
        appNavigator.tryNavigateTo(Destination.ProductsOverviewScreen.passParameters(storageId!!))
    }

    fun onNavigateToWareHousesScreen() {
        appNavigator.tryNavigateTo(Destination.WareHousesScreen.fullRoute)
    }

    fun onDialogShow(dialogTextId : Int){
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