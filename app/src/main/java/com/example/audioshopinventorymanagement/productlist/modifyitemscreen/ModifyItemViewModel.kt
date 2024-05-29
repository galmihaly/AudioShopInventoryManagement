package com.example.audioshopinventorymanagement.productlist.modifyitemscreen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audioshopinventorymanagement.authentication.repositories.ProductApiRepository
import com.example.audioshopinventorymanagement.authentication.responses.BrandDetails
import com.example.audioshopinventorymanagement.authentication.responses.CategoryDetails
import com.example.audioshopinventorymanagement.authentication.responses.ModelDetails
import com.example.audioshopinventorymanagement.authentication.responses.sealed.ProductApiResponse
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
import java.lang.StringBuilder
import javax.inject.Inject

@HiltViewModel
class ModifyItemViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val productApiRepository: ProductApiRepository,
    private val databaseRepository: ProductDatabaseRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

    private val _viewState = MutableStateFlow(ModifyItemViewState())
    val viewState = _viewState.asStateFlow()

    private val arg = checkNotNull(savedStateHandle[Destination.ModifyItemSreenArguments.barcode.toString()] ?: "")

    private val productIdSeparator = '-'

    init {
        getProductFromRoom()

        getAllBrand()
        getAllCategory()
        getAllModel()
    }

    private fun getProductFromRoom(){
        viewModelScope.launch {
            if(arg != ""){

                _viewState.update {
                    it.copy(
                        modifyItemScreenArgument = arg
                    )
                }

                val product = databaseRepository.getProductByBarcode(arg)

                var brandDropDownValue = ""
                _viewState.value.brandDetailsList.forEach{
                        p->
                    run {
                        if (product.productId?.split(productIdSeparator)?.get(0) == p.brandId) brandDropDownValue = p.brandName!!
                    }
                }

                var categoryDropDownValue = ""
                _viewState.value.categoryDetailsList.forEach{
                        p->
                    run {
                        if (product.productId?.split(productIdSeparator)?.get(1) == p.categoryId) categoryDropDownValue = p.categoryName!!
                    }
                }

                var modelDropDownValue = ""
                _viewState.value.modelDetailsList.forEach{
                        p->
                    run {
                        if (product.productId?.split(productIdSeparator)?.get(2) == p.modelId) modelDropDownValue = p.modelName!!
                    }
                }

                _viewState.update {
                    it.copy(
                        warehouseTFValue = product.warehouseId!!,
                        storageTFValue = product.storageId!!,
                        brandDDValue = brandDropDownValue,
                        categoryDDValue = categoryDropDownValue,
                        modelDDValue = modelDropDownValue,
                        barcodeTFValue = product.barcode!!,
                        basePriceTFValue = product.basePrice!!,
                        wholeSalePriceTFValue = product.wholeSalePrice!!
                    )
                }
            }
        }
    }

    private fun getAllBrand(){
        viewModelScope.launch(Dispatchers.IO) {
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
                        onDialogShow("Read of the brands has been failed!")
                    }
                }
                is ProductApiResponse.Exception -> {
                    onDialogShow(response.exceptionMessage)
                }
                else -> {}
            }

            _viewState.update {
                it.copy(
                    brandDetailsList= brandList
                )
            }

            val asStringList = ArrayList<String>()
            brandList.forEach {
                    b -> asStringList.add(b.brandName!!)
            }

            _viewState.update {
                it.copy(
                    brandDDList = asStringList
                )
            }
        }
    }

    private fun getAllCategory(){
        viewModelScope.launch(Dispatchers.IO) {
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
                        onDialogShow("Read of the categories has been failed!")
                    }
                }
                is ProductApiResponse.Exception -> {
                    onDialogShow(response.exceptionMessage)
                }
                else -> {}
            }

            _viewState.update {
                it.copy(
                    categoryDetailsList = categoryList
                )
            }

            val asStringList = ArrayList<String>()
            categoryList.forEach{
                    c -> asStringList.add(c.categoryName!!)
            }

            _viewState.update {
                it.copy(
                    categoryDDList = asStringList
                )
            }
        }
    }

    private fun getAllModel(){
        viewModelScope.launch(Dispatchers.IO) {
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
                        onDialogShow("Read of the models has been failed!")
                    }
                }
                is ProductApiResponse.Exception -> {
                    onDialogShow(response.exceptionMessage)
                }
                else -> {}
            }

            _viewState.update {
                it.copy(
                    modelDetailsList = modelList
                )
            }

            val asStringList = ArrayList<String>()
            modelList.forEach{
                    n -> asStringList.add(n.modelName!!)
            }

            _viewState.update {
                it.copy(
                    modelDDList = asStringList
                )
            }
        }
    }

    fun deleteAllTextField() {
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    warehouseTFValue = "",
                    storageTFValue = "",
                    brandDDValue = "",
                    categoryDDValue = "",
                    modelDDValue = "",
                    barcodeTFValue = "",
                    basePriceTFValue = "",
                    wholeSalePriceTFValue = ""
                )
            }
        }
    }

    fun saveChangesOnItem(){
        viewModelScope.launch(Dispatchers.IO) {
            if(arg != ""){
                val oldProduct = databaseRepository.getProductByBarcode(arg)

                val barcodeTFValue = _viewState.value.barcodeTFValue

                val brandDDValue = _viewState.value.brandDDValue
                val modelDDValue = _viewState.value.modelDDValue
                val categoryDDValue = _viewState.value.categoryDDValue

                val basePriceTFValue = _viewState.value.basePriceTFValue
                val wholeSalePriceTFValue = _viewState.value.wholeSalePriceTFValue
                val warehouseTFValue = _viewState.value.warehouseTFValue
                val storageTFValue = _viewState.value.storageTFValue

                if (barcodeTFValue == "") {
                    onDialogShow(dialogText = "The barcode field cannot be empty!")
                }
                else if (brandDDValue == "") {
                    onDialogShow(dialogText = "The Brand field cannot be empty!")
                    return@launch
                }
                else if (modelDDValue == "") {
                    onDialogShow(dialogText = "The Model field cannot be empty!")
                    return@launch
                }
                else if (categoryDDValue == "") {
                    onDialogShow(dialogText = "The Category field field cannot be empty!")
                    return@launch
                }
                else if (basePriceTFValue == "") {
                    onDialogShow(dialogText = "The BasePrice field cannot be empty!")
                    return@launch
                }
                else if (wholeSalePriceTFValue == "") {
                    onDialogShow(dialogText = "The WholeSalePrice field cannot be empty!")
                    return@launch
                }
                else if (warehouseTFValue == "") {
                    onDialogShow(dialogText = "The Warehouse Identifier field cannot be empty!")
                    return@launch
                }
                else if (storageTFValue == "") {
                    onDialogShow(dialogText = "The Storage Identifier field cannot be empty!")
                    return@launch
                }

                val productName = createProductName(
                    brand = brandDDValue,
                    model = modelDDValue
                )

                val productId = createProductId(
                    brandId = getBrandId(brand = brandDDValue),
                    modelId = getModelId(model = modelDDValue),
                    categoryId = getCategoryId(category = categoryDDValue)
                )

                if(oldProduct.barcode != barcodeTFValue){
                    databaseRepository.updateBarcode(oldProduct.id!!, barcodeTFValue)
                }
                if(oldProduct.productId != productId){
                    databaseRepository.updateProductId(oldProduct.id!!, productId)
                }
                if(oldProduct.productName != productName){
                    databaseRepository.updateName(oldProduct.id!!, productName)
                }
                if(oldProduct.productType != categoryDDValue){
                    databaseRepository.updateType(oldProduct.id!!, categoryDDValue)
                }
                if(oldProduct.basePrice != basePriceTFValue){
                    databaseRepository.updateBasePrice(oldProduct.id!!, basePriceTFValue)
                }
                if(oldProduct.wholeSalePrice != wholeSalePriceTFValue){
                    databaseRepository.updateWholeSalePrice(oldProduct.id!!, wholeSalePriceTFValue)
                }
                if(oldProduct.storageId != storageTFValue){
                    databaseRepository.updateStorageId(oldProduct.id!!, storageTFValue)
                }
                onNavigateToProductListScreen()
            }
        }
    }

    fun backToProductListScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            if(arg != ""){
                val oldProduct = databaseRepository.getProductByBarcode(arg)

                val barcodeTFValue = _viewState.value.barcodeTFValue

                val brandDDValue = _viewState.value.brandDDValue
                val modelDDValue = _viewState.value.modelDDValue
                val categoryDDValue = _viewState.value.categoryDDValue

                val basePriceTFValue = _viewState.value.basePriceTFValue
                val wholeSalePriceTFValue = _viewState.value.wholeSalePriceTFValue
                val warehouseTFValue = _viewState.value.warehouseTFValue
                val storageTFValue = _viewState.value.storageTFValue

                if (barcodeTFValue == "") {
                    onDialogShow(dialogText = "The barcode field cannot be empty!")
                }
                else if (brandDDValue == "") {
                    onDialogShow(dialogText = "The Brand field cannot be empty!")
                    return@launch
                }
                else if (modelDDValue == "") {
                    onDialogShow(dialogText = "The Model field cannot be empty!")
                    return@launch
                }
                else if (categoryDDValue == "") {
                    onDialogShow(dialogText = "The Category field field cannot be empty!")
                    return@launch
                }
                else if (basePriceTFValue == "") {
                    onDialogShow(dialogText = "The BasePrice field cannot be empty!")
                    return@launch
                }
                else if (wholeSalePriceTFValue == "") {
                    onDialogShow(dialogText = "The WholeSalePrice field cannot be empty!")
                    return@launch
                }
                else if (warehouseTFValue == "") {
                    onDialogShow(dialogText = "The Warehouse Identifier field cannot be empty!")
                    return@launch
                }
                else if (storageTFValue == "") {
                    onDialogShow(dialogText = "The Storage Identifier field cannot be empty!")
                    return@launch
                }

                val productName = createProductName(
                    brand = brandDDValue,
                    model = modelDDValue
                )

                val productId = createProductId(
                    brandId = getBrandId(brand = brandDDValue),
                    modelId = getModelId(model = modelDDValue),
                    categoryId = getCategoryId(category = categoryDDValue)
                )

                var s = false

                if(oldProduct.barcode != barcodeTFValue){
                    s = true
                }
                if(oldProduct.productId != productId){
                    s = true
                }
                if(oldProduct.productName != productName){
                    s = true
                }
                if(oldProduct.productType != categoryDDValue){
                    s = true
                }
                if(oldProduct.basePrice != basePriceTFValue){
                    s = true
                }
                if(oldProduct.wholeSalePrice != wholeSalePriceTFValue){
                    s = true
                }
                if(oldProduct.storageId != storageTFValue){
                    s = true
                }

                if(s){
                    onDialogShow("asd")
                }
                else {
                    onNavigateToProductListScreen()
                }
            }
        }
    }

    private fun getModelId(model: String): String {
        var modelId = ""
        val modelDetailsList = _viewState.value.modelDetailsList
        modelDetailsList.forEach{
                m -> if(m.modelName == model) modelId = m.modelId!!
        }

        return modelId
    }

    private fun getCategoryId(category: String): String {
        var categoryId = ""
        val categoryDetailsList = _viewState.value.categoryDetailsList
        categoryDetailsList.forEach{
                c -> if(c.categoryName == category) categoryId = c.categoryId!!
        }

        return categoryId
    }

    private fun getBrandId(brand: String): String {
        var brandId = ""
        val brandDetailsList = _viewState.value.brandDetailsList
        brandDetailsList.forEach{
                b -> if(b.brandName == brand) brandId = b.brandId!!
        }

        return brandId
    }

    private fun createProductId(brandId: String, modelId: String, categoryId: String): String {
        return StringBuilder()
            .append(brandId)
            .append(productIdSeparator)
            .append(categoryId)
            .append(productIdSeparator)
            .append(modelId)
            .toString()
    }

    private fun createProductName(brand : String, model : String) : String{
        return StringBuilder()
            .append(brand)
            .append(" ")
            .append(model)
            .toString()
    }

    fun updateWarehouseTFValue(value : String){
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    warehouseTFValue = value
                )
            }
        }
    }

    fun updateStockTFValue(value : String){
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    storageTFValue = value
                )
            }
        }
    }

    fun updateBarcodeTFValue(value : String){
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    barcodeTFValue = value
                )
            }
        }
    }

    fun updateBasePriceTFValue(value : String){
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    basePriceTFValue = value
                )
            }
        }
    }

    fun updateWholeSalePriceTFValue(value : String){
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    wholeSalePriceTFValue = value
                )
            }
        }
    }

    fun updateBrandDropDownValue(value : String){
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    brandDDValue = value
                )
            }
        }
    }

    fun updateCategoryDropDownValue(value : String){
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    categoryDDValue = value
                )
            }
        }
    }

    fun updateModelDropDownValue(value : String){
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    modelDDValue = value
                )
            }
        }
    }

    fun updateBrandExpandedDropDown(value : Boolean){
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    brandExpandedDD = value
                )
            }
        }
    }

    fun updateCategoryExpandedDropDown(value : Boolean){
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    categoryExpandedDD = value
                )
            }
        }
    }

    fun updateModelExpandedDropDown(value : Boolean){
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    modelExpandedDD = value
                )
            }
        }
    }

    fun onNavigateToProductListScreen() {
        appNavigator.tryNavigateTo(Destination.ProductListScreen.fullRoute)
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