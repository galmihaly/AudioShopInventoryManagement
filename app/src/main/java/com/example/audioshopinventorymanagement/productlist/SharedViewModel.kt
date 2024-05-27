package com.example.audioshopinventorymanagement.productlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audioshopinventorymanagement.authentication.repositories.ProductApiRepository
import com.example.audioshopinventorymanagement.authentication.responses.BrandDetails
import com.example.audioshopinventorymanagement.authentication.responses.CategoryDetails
import com.example.audioshopinventorymanagement.authentication.responses.ModelDetails
import com.example.audioshopinventorymanagement.authentication.responses.sealed.ProductApiResponse
import com.example.audioshopinventorymanagement.navigation.AppNavigator
import com.example.audioshopinventorymanagement.navigation.Destination
import com.example.audioshopinventorymanagement.productlist.newitemscreen.NewItemViewState
import com.example.audioshopinventorymanagement.productlist.productlistscreen.ProductListItem
import com.example.audioshopinventorymanagement.productlist.productlistscreen.ProductViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val productApiRepository: ProductApiRepository
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

    /*private val _productViewState = MutableStateFlow(ProductViewState())
    val productViewState = _productViewState.asStateFlow()*/

    private val _productViewState = MutableStateFlow(ProductViewState())
    val productViewState = _productViewState.asStateFlow()

    private val _newItemViewState = MutableStateFlow(NewItemViewState())
    val newItemViewState = _newItemViewState.asStateFlow()

    private val _productList = ArrayList<ProductListItem>()

    private val productIdSeparator = '-'

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
                    brandList = response.data.brandDetails!!
                }
            }
            is ProductApiResponse.BrandError -> {
                if(response.data.statusCode == 401){
                    onNewItemDialogShow("Read of the brands has been failed!")
                }
            }
            is ProductApiResponse.Exception -> {
                onNewItemDialogShow(response.exceptionMessage)
            }
            else -> {}
        }

        viewModelScope.launch {
            _newItemViewState.update {
                it.copy(
                    brandDetailsList= brandList
                )
            }
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

        viewModelScope.launch {
            _newItemViewState.update {
                it.copy(
                    categoryDetailsList = categoryList
                )
            }
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

        viewModelScope.launch {
            _newItemViewState.update {
                it.copy(
                    modelDetailsList = modelList
                )
            }
        }

        val asStringList = ArrayList<String>()
        modelList.forEach{
            n -> asStringList.add(n.modelName!!)
        }

        return asStringList
    }

    fun addItemToProductList(){
        val barcodeTFValue = _newItemViewState.value.barcodeTFValue

        val brandDropDownValue = _newItemViewState.value.brandDropDownValue
        val modelDropDownValue = _newItemViewState.value.modelDropDownValue
        val categoryDropDownValue = _newItemViewState.value.categoryDropDownValue

        val brandId = getBrandId(brand = brandDropDownValue)
        val categoryId = getCategoryId(category = categoryDropDownValue)
        val modelId = getModelId(model = modelDropDownValue)

        val basePriceTFValue = _newItemViewState.value.basePriceTFValue
        val wholeSalePriceTFValue = _newItemViewState.value.wholeSalePriceTFValue
        val warehouseTFValue = _newItemViewState.value.warehouseTFValue
        val storageTFValue = _newItemViewState.value.storageTFValue

        if (barcodeTFValue == "") {
            onNewItemDialogShow(dialogText = "The barcode field cannot be empty!")
            return
        }
        else if (brandDropDownValue == "") {
            onNewItemDialogShow(dialogText = "The Brand field cannot be empty!")
            return
        }
        else if (modelDropDownValue == "") {
            onNewItemDialogShow(dialogText = "The Model field cannot be empty!")
            return
        }
        else if (categoryDropDownValue == "") {
            onNewItemDialogShow(dialogText = "The Category field field cannot be empty!")
            return
        }
        else if (basePriceTFValue == "") {
            onNewItemDialogShow(dialogText = "The BasePrice field cannot be empty!")
            return
        }
        else if (wholeSalePriceTFValue == "") {
            onNewItemDialogShow(dialogText = "The WholeSalePrice field cannot be empty!")
            return
        }
        else if (warehouseTFValue == "") {
            onNewItemDialogShow(dialogText = "The Warehouse Identifier field cannot be empty!")
            return
        }
        else if (storageTFValue == "") {
            onNewItemDialogShow(dialogText = "The Storage Identifier field cannot be empty!")
            return
        }

        val productName = createProductName(
            brand = brandDropDownValue,
            model = modelDropDownValue
        )

        val productId = createProductId(
            brandId = brandId,
            modelId = modelId,
            categoryId = categoryId
        )

        val newProduct = ProductListItem(
            barcode = barcodeTFValue,
            productId = productId,
            productName = productName,
            productType = categoryDropDownValue,
            basePrice = basePriceTFValue.toInt(),
            wholeSalePrice = wholeSalePriceTFValue.toInt(),
            warehouseId = warehouseTFValue,
            storageId = storageTFValue
        )

        _productList.add(newProduct)

        Log.e("barcode1",  _productList.get(0).barcode)
        Log.e("productType1",  _productList.get(0).productType)
        Log.e("productName1",  _productList.get(0).productName)
        Log.e("productId1",  _productList.get(0).productId)
        Log.e("productName1",  _productList.get(0).productName)
        Log.e("productType1",  _productList.get(0).productType)
        Log.e("basePrice1",  _productList.get(0).basePrice.toString())
        Log.e("wholeSalePrice1",  _productList.get(0).wholeSalePrice.toString())
        Log.e("warehouseId1",  _productList.get(0).warehouseId)
        Log.e("storageId1",  _productList.get(0).storageId)

        Log.e("size",  _productList.size.toString())

        viewModelScope.launch {
            _productViewState.update {
                it.copy(
                    productList = _productList
                )
            }
        }

        /*Log.e("barcode2",  _productViewState.get(0).barcode)
        Log.e("productType2",  _productViewState.value.productList.get(0).productType)
        Log.e("productName2",  _productViewState.value.productList.get(0).productName)
        Log.e("productId2",  _productViewState.value.productList.get(0).productId)
        Log.e("productName2",  _productViewState.value.productList.get(0).productName)
        Log.e("productType2",  _productViewState.value.productList.get(0).productType)
        Log.e("basePrice2",  _productViewState.value.productList.get(0).basePrice.toString())
        Log.e("wholeSalePrice2",  _productViewState.value.productList.get(0).wholeSalePrice.toString())
        Log.e("warehouseId2",  _productViewState.value.productList.get(0).warehouseId)
        Log.e("storageId2",  _productViewState.value.productList.get(0).storageId)*/
    }

    private fun getModelId(model: String): String {
        var modelId = ""
        val modelDetailsList = _newItemViewState.value.modelDetailsList
        modelDetailsList.forEach{
            m -> if(m.modelName == model) modelId = m.modelId!!
        }

        return modelId
    }

    private fun getCategoryId(category: String): String {
        var categoryId = ""
        val categoryDetailsList = _newItemViewState.value.categoryDetailsList
        categoryDetailsList.forEach{
                c -> if(c.categoryName == category) categoryId = c.categoryId!!
        }

        return categoryId
    }

    private fun getBrandId(brand: String): String {
        var brandId = ""
        val brandDetailsList = _newItemViewState.value.brandDetailsList
        brandDetailsList.forEach{
                b -> if(b.brandName == brand) brandId = b.brandId!!
        }

        return brandId
    }

    private fun createProductId(brandId: String, modelId: String, categoryId: String): String {
        return brandId + productIdSeparator + categoryId + productIdSeparator + modelId
    }

    private fun createProductName(brand : String, model : String) : String{
        return "$brand $model";
    }

    fun updateSearchFieldValue(value : String){
        /*viewModelScope.launch {
            _productViewState.update {
                it.copy(
                    searchFieldValue = value
                )
            }
        }*/
    }

    fun updateIsExpandCard(value : Boolean){
        /*viewModelScope.launch {
            _productViewState.update {
                it.copy(
                    isExpandCard = value
                )
            }
        }*/
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

    fun updateCategoryDropDownValue(value : String){
        viewModelScope.launch {
            _newItemViewState.update {
                it.copy(
                    categoryDropDownValue = value
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

    fun updateBarcodeTFValue(value : String){
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