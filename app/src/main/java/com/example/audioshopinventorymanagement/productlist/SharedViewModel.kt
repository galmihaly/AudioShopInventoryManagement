package com.example.audioshopinventorymanagement.productlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audioshopinventorymanagement.authentication.repositories.ProductApiRepository
import com.example.audioshopinventorymanagement.authentication.requests.LoginAuthRequest
import com.example.audioshopinventorymanagement.authentication.responses.BrandDetails
import com.example.audioshopinventorymanagement.authentication.responses.CategoryDetails
import com.example.audioshopinventorymanagement.authentication.responses.ModelDetails
import com.example.audioshopinventorymanagement.authentication.responses.sealed.LoginApiResponse
import com.example.audioshopinventorymanagement.authentication.responses.sealed.ProductApiResponse
import com.example.audioshopinventorymanagement.navigation.AppNavigator
import com.example.audioshopinventorymanagement.navigation.Destination
import com.example.audioshopinventorymanagement.productlist.newitemscreen.NewItemViewState
import com.example.audioshopinventorymanagement.productlist.productlistscreen.ProductViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val productApiRepository: ProductApiRepository
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

    private val _productViewState = MutableStateFlow(ProductViewState())
    val productViewState = _productViewState.asStateFlow()

    private val _newItemViewState = MutableStateFlow(NewItemViewState())
    val newItemViewState = _newItemViewState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val brandList = getAllBrand()
            val categoryList = getAllCategory()
            val modelList = getAllModel()

            viewModelScope.launch {
                _newItemViewState.update {
                    it.copy(
                        brandDropDownList = brandList
                    )
                }
            }

            viewModelScope.launch {
                _newItemViewState.update {
                    it.copy(
                        categoryDropDownList = categoryList
                    )
                }
            }

            viewModelScope.launch {
                _newItemViewState.update {
                    it.copy(
                        modelDropDownList = modelList
                    )
                }
            }
        }
    }

    private suspend fun getAllBrand() : List<String>{
        val response = productApiRepository.getAllBrand()

        var brandList : List<BrandDetails> = ArrayList()

        when (response){
            is ProductApiResponse.BrandSuccess -> {
                if(response.data.statusCode == 200){
                    // save tokens to DataStore
                    brandList = response.data.brandDetails!!
                }
            }
            is ProductApiResponse.BrandError -> {
                if(response.data.statusCode == 401){
                    onNewItemDialogShow("Read of the brands has been failed!")
                }
            }
            is ProductApiResponse.Exception -> {
                Log.e("exceptionMessage", response.exceptionMessage)
                onNewItemDialogShow(response.exceptionMessage)
            }
            else -> {}
        }

        val asStringList = ArrayList<String>()
        brandList.forEach {
                b -> asStringList.add(b.brandName!!)
        }

        return asStringList
    }

    private suspend fun getAllCategory() : List<String>{
        val response = productApiRepository.getAllCategory()

        var categoryList : List<CategoryDetails> = ArrayList()

        when (response){
            is ProductApiResponse.CategorySuccess -> {
                if(response.data.statusCode == 200){
                    // save tokens to DataStore
                    categoryList = response.data.categoryDetails!!
                }
            }
            is ProductApiResponse.CategoryError -> {
                if(response.data.statusCode == 401){
                    onNewItemDialogShow("Read of the categories has been failed!")
                }
            }
            is ProductApiResponse.Exception -> {
                onNewItemDialogShow(response.exceptionMessage)
            }
            else -> {}
        }

        val asStringList = ArrayList<String>()
        categoryList.forEach{
                c -> asStringList.add(c.categoryName!!)
        }

        return asStringList
    }

    private suspend fun getAllModel() : List<String>{
        val response = productApiRepository.getAllModel()

        var modelList : List<ModelDetails> = ArrayList()

        when (response){
            is ProductApiResponse.ModelSuccess -> {
                if(response.data.statusCode == 200){
                    // save tokens to DataStore
                    modelList = response.data.modelDetails!!
                }
            }
            is ProductApiResponse.ModelError -> {
                if(response.data.statusCode == 401){
                    onNewItemDialogShow("Read of the models has been failed!")
                }
            }
            is ProductApiResponse.Exception -> {
                onNewItemDialogShow(response.exceptionMessage)
            }
            else -> {}
        }

        val asStringList = ArrayList<String>()
        modelList.forEach{
            n -> asStringList.add(n.modelName!!)
        }

        return asStringList
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

    fun onNewItemDialogShow(dialogText : String){
        viewModelScope.launch {
            _newItemViewState.update {
                it.copy(
                    textShowErrorDialog = dialogText,
                    isShowErrorDialog = true
                )
            }
        }
    }

    fun onNewItemDialogDismiss() {
        viewModelScope.launch {
            _newItemViewState.update {
                it.copy(
                    textShowErrorDialog = "",
                    isShowErrorDialog = false
                )
            }
        }
    }
}