package com.example.audioshopinventorymanagement.productlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audioshopinventorymanagement.navigation.AppNavigator
import com.example.audioshopinventorymanagement.navigation.Destination
import com.example.audioshopinventorymanagement.productlist.newitemscreen.NewItemViewState
import com.example.audioshopinventorymanagement.productlist.productlistscreen.ProductViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

    init {
        viewModelScope.launch {
            _newItemViewState.update {
                it.copy(
                    brandDropDownList = listOf("Sennheiser HD 560s", "Bill Payment", "Recharges", "Outing", "Other")
                )
            }
        }

        viewModelScope.launch {
            _newItemViewState.update {
                it.copy(
                    categoryDropDownList = listOf("Sennheiser HD 560s", "Bill Payment", "Recharges", "Outing", "Other")
                )
            }
        }

        viewModelScope.launch {
            _newItemViewState.update {
                it.copy(
                    modelDropDownList = listOf("Sennheiser HD 560s", "Bill Payment", "Recharges", "Outing", "Other")
                )
            }
        }
    }

    fun updateWarehouseTFValue(value : String){
        viewModelScope.launch {
            _newItemViewState.update {
                it.copy(
                    warehouseTFValue = value
                )
            }
        }
    }

    fun updateStockTFValue(value : String){
        viewModelScope.launch {
            _newItemViewState.update {
                it.copy(
                    storageTFValue = value
                )
            }
        }
    }

    fun updateBrandDropDownValue(value : String){
        viewModelScope.launch {
            _newItemViewState.update {
                it.copy(
                    brandDropDownValue = value
                )
            }
        }
    }

    fun updateBrandDropDownList(value : List<String>, isExpanded : Boolean){
        viewModelScope.launch {
            _newItemViewState.update {
                it.copy(
                    brandDropDownList = value,
                    brandExpandedDropDown = isExpanded
                )
            }
        }
    }

    fun updateCategoryDropDownValue(value : String){
        viewModelScope.launch {
            _newItemViewState.update {
                it.copy(
                    categoryDropDownValue = value
                )
            }
        }
    }

    fun updateCategoryDropDownList(value : List<String>, isExpanded : Boolean){
        viewModelScope.launch {
            _newItemViewState.update {
                it.copy(
                    categoryDropDownList = value,
                    categoryExpandedDropDown = isExpanded
                )
            }
        }
    }

    fun updateModelDropDownValue(value : String){
        viewModelScope.launch {
            _newItemViewState.update {
                it.copy(
                    modelDropDownValue = value
                )
            }
        }
    }

    fun updateModelDropDownList(value : List<String>, isExpanded : Boolean){
        viewModelScope.launch {
            _newItemViewState.update {
                it.copy(
                    modelDropDownList = value,
                    modelExpandedDropDown = isExpanded
                )
            }
        }
    }

    fun updateBarcodeTFValue(value : String){
        Log.e("updateBarcodeTFValue", value)

        viewModelScope.launch {
            _newItemViewState.update {
                it.copy(
                    barcodeTFValue = value
                )
            }
        }
    }

    fun updateBasePriceTFValue(value : String){
        viewModelScope.launch {
            _newItemViewState.update {
                it.copy(
                    basePriceTFValue = value
                )
            }
        }
    }

    fun updateBrandExpandedDropDown(value : Boolean){
        viewModelScope.launch {
            _newItemViewState.update {
                it.copy(
                    brandExpandedDropDown = value
                )
            }
        }
    }

    fun updateCategoryExpandedDropDown(value : Boolean){
        viewModelScope.launch {
            _newItemViewState.update {
                it.copy(
                    categoryExpandedDropDown = value
                )
            }
        }
    }

    fun updateModelExpandedDropDown(value : Boolean){
        viewModelScope.launch {
            _newItemViewState.update {
                it.copy(
                    modelExpandedDropDown = value
                )
            }
        }
    }

    fun updateWholeSalePriceTFValue(value : String){
        viewModelScope.launch {
            _newItemViewState.update {
                it.copy(
                    wholeSalePriceTFValue = value
                )
            }
        }
    }

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