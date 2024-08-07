package com.example.audioshopinventorymanagement.modifyitemscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.navigation.AppNavigator
import com.example.audioshopinventorymanagement.navigation.Destination
import com.example.audioshopinventorymanagement.room.entities.BrandEntity
import com.example.audioshopinventorymanagement.room.entities.CategoryEntity
import com.example.audioshopinventorymanagement.room.entities.ModelEntity
import com.example.audioshopinventorymanagement.room.repositories.ProductDatabaseRepository
import com.example.audioshopinventorymanagement.utils.Formatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModifyItemViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val databaseRepo: ProductDatabaseRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

    private val _viewState = MutableStateFlow(ModifyItemViewState())
    val viewState = _viewState.asStateFlow()

    private val arg = checkNotNull(savedStateHandle[Destination.ModifyItemScreenArguments.barcode] ?: "")

    init {
        getAllBrand()
        getAllCategory()
        getAllModel()

        getProductFromRoom()
    }

    private fun getProductFromRoom(){
        viewModelScope.launch(Dispatchers.IO) {
            if(arg != ""){

                _viewState.update {
                    it.copy(
                        modifyItemScreenArgument = arg
                    )
                }

                val product = databaseRepo.getProductByBarcode(arg)
                _viewState.update {
                    it.copy(
                        warehouseTFValue = product.warehouseId!!,
                        storageTFValue = product.storageId!!,
                        brandDDValue = product.brandName!!,
                        categoryDDValue = product.categoryName!!,
                        modelDDValue = product.modelName!!,
                        barcodeTFValue = product.barcode!!,
                        basePriceTFValue = product.basePrice.toString(),
                        wholeSalePriceTFValue = product.wholeSalePrice.toString()
                    )
                }
            }
        }
    }

    fun saveChangesOnItem(){
        viewModelScope.launch(Dispatchers.IO) {
            val barcodeTFValue = _viewState.value.barcodeTFValue
            val brandDDValue = _viewState.value.brandDDValue
            val modelDDValue = _viewState.value.modelDDValue
            val categoryDDValue = _viewState.value.categoryDDValue

            var brandDetails = getBrandId(brand = brandDDValue)
            var categoryDetails = getCategoryId(category = categoryDDValue)
            var modelDetails = getModelId(model = modelDDValue)

            val basePriceTFValue = _viewState.value.basePriceTFValue
            val wholeSalePriceTFValue = _viewState.value.wholeSalePriceTFValue
            val warehouseTFValue = _viewState.value.warehouseTFValue
            val storageTFValue = _viewState.value.storageTFValue

            val products = databaseRepo.getProductByBarcode(arg)

            if (warehouseTFValue.isEmpty()) {
                onTwoDialogShow(dialogTextId = R.string.MODIFY_WAREHOUSE_ID_EMPTY)
                return@launch
            }
            else{
                if(warehouseTFValue != products.warehouseId){
                    databaseRepo.updateWarehouseId(products.barcode!!, warehouseTFValue)
                }
            }

            if (storageTFValue.isEmpty()) {
                onTwoDialogShow(dialogTextId = R.string.MODIFY_STORAGE_ID_EMPTY)
                return@launch
            }
            else{
                if(storageTFValue != products.storageId){
                    databaseRepo.updateStorageId(products.barcode!!, storageTFValue)
                }
            }

            if (brandDDValue.isEmpty()) {
                onTwoDialogShow(dialogTextId = R.string.MODIFY_BRAND_EMPTY)
                return@launch
            }
            else{
                if(brandDDValue != products.brandName){
                    databaseRepo.updateBrandName(products.barcode!!, brandDDValue)
                    brandDetails = getBrandId(brand = brandDDValue)
                    databaseRepo.updateBrandId(products.barcode, brandDetails.brandId!!)
                }
            }

            if (categoryDDValue.isEmpty()) {
                onTwoDialogShow(dialogTextId = R.string.MODIFY_CATEGORY_EMPTY)
                return@launch
            }
            else{
                if(categoryDDValue != products.categoryName){
                    databaseRepo.updateCategoryName(products.barcode!!, categoryDDValue)
                    categoryDetails = getCategoryId(category = categoryDDValue)
                    databaseRepo.updateCategoryId(products.barcode, categoryDetails.categoryId!!)
                }
            }

            if (modelDDValue.isEmpty()) {
                onTwoDialogShow(dialogTextId = R.string.MODIFY_MODEL_EMPTY)
                return@launch
            }
            else{
                if(modelDDValue != products.modelName){
                    databaseRepo.updateModelName(products.barcode!!, modelDDValue)
                    modelDetails = getModelId(model = modelDDValue)
                    databaseRepo.updateModelId(products.barcode, modelDetails.modelId!!)
                }
            }

            if (barcodeTFValue.isEmpty()) {
                onTwoDialogShow(dialogTextId = R.string.MODIFY_BARCODE_EMPTY)
                return@launch
            }
            else{
                if(barcodeTFValue != products.barcode){
                    databaseRepo.updateBarcode(products.barcode!!, barcodeTFValue)
                }
            }

            if (basePriceTFValue.isEmpty()) {
                onTwoDialogShow(dialogTextId = R.string.MODIFY_BASE_PRICE_EMPTY)
                return@launch
            }
            else{
                if(basePriceTFValue != products.basePrice.toString()){
                    databaseRepo.updateBasePrice(products.barcode, basePriceTFValue)
                }
            }

            if (wholeSalePriceTFValue.isEmpty()) {
                onTwoDialogShow(dialogTextId = R.string.MODIFY_WHOLESALE_PRICE_EMPTY)
                return@launch
            }
            else{
                if(wholeSalePriceTFValue != products.wholeSalePrice.toString()){
                    databaseRepo.updateWholeSalePrice(products.barcode, wholeSalePriceTFValue)
                }
            }

            if(brandDDValue.isNotEmpty() || categoryDDValue.isNotEmpty() || modelDDValue.isNotEmpty()){
                if(brandDDValue != products.brandName || categoryDDValue != products.categoryName || modelDDValue != products.modelName){
                    val productId = Formatter.createProductId(brandDetails.brandId!!, categoryDetails.categoryId!!, modelDetails.modelId!!)
                    databaseRepo.updateProductId(barcodeTFValue, productId)
                }
            }

            if(brandDDValue.isNotEmpty() || modelDDValue.isNotEmpty()){
                if(brandDDValue != products.brandName || modelDDValue != products.modelName){
                    val productName = Formatter.createProductName(brandDDValue, modelDDValue)
                    databaseRepo.updateProductName(barcodeTFValue, productName)
                }
            }
            onNavigateToProductListScreen()
        }
    }

    fun backToProductListScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            val barcodeTFValue = _viewState.value.barcodeTFValue
            val brandDDValue = _viewState.value.brandDDValue
            val modelDDValue = _viewState.value.modelDDValue
            val categoryDDValue = _viewState.value.categoryDDValue
            val basePriceTFValue = _viewState.value.basePriceTFValue
            val wholeSalePriceTFValue = _viewState.value.wholeSalePriceTFValue
            val warehouseTFValue = _viewState.value.warehouseTFValue
            val storageTFValue = _viewState.value.storageTFValue

            val products = databaseRepo.getProductByBarcode(arg)

            if (warehouseTFValue.isEmpty()) {
                onTwoDialogShow(dialogTextId = R.string.MODIFY_WAREHOUSE_ID_EMPTY)
                return@launch
            }
            else{
                if(warehouseTFValue != products.warehouseId){
                    onOneDialogShow(dialogTextId = R.string.MODIFY_QUIT_WITHOUT_SAVE)
                    return@launch
                }
            }

            if (storageTFValue.isEmpty()) {
                onTwoDialogShow(dialogTextId = R.string.MODIFY_STORAGE_ID_EMPTY)
                return@launch
            }
            else{
                if(storageTFValue != products.storageId){
                    onOneDialogShow(dialogTextId = R.string.MODIFY_QUIT_WITHOUT_SAVE)
                    return@launch
                }
            }

            if (brandDDValue.isEmpty()) {
                onTwoDialogShow(dialogTextId = R.string.MODIFY_BRAND_EMPTY)
                return@launch
            }
            else{
                if(brandDDValue != products.brandName){
                    onOneDialogShow(dialogTextId = R.string.MODIFY_QUIT_WITHOUT_SAVE)
                    return@launch
                }
            }

            if (categoryDDValue.isEmpty()) {
                onTwoDialogShow(dialogTextId = R.string.MODIFY_CATEGORY_EMPTY)
                return@launch
            }
            else{
                if(categoryDDValue != products.categoryName){
                    onOneDialogShow(dialogTextId = R.string.MODIFY_QUIT_WITHOUT_SAVE)
                    return@launch
                }
            }

            if (modelDDValue.isEmpty()) {
                onTwoDialogShow(dialogTextId = R.string.MODIFY_MODEL_EMPTY)
                return@launch
            }
            else{
                if(modelDDValue != products.modelName){
                    onOneDialogShow(dialogTextId = R.string.MODIFY_QUIT_WITHOUT_SAVE)
                    return@launch
                }
            }

            if (barcodeTFValue.isEmpty()) {
                onTwoDialogShow(dialogTextId = R.string.MODIFY_BARCODE_EMPTY)
                return@launch
            }
            else{
                if(barcodeTFValue != products.barcode){
                    onOneDialogShow(dialogTextId =  R.string.MODIFY_QUIT_WITHOUT_SAVE)
                    return@launch
                }
            }

            if (basePriceTFValue.isEmpty()) {
                onTwoDialogShow(dialogTextId = R.string.MODIFY_BASE_PRICE_EMPTY)
                return@launch
            }
            else{
                if(basePriceTFValue != products.basePrice.toString()){
                    onOneDialogShow(dialogTextId = R.string.MODIFY_QUIT_WITHOUT_SAVE)
                    return@launch
                }
            }

            if (wholeSalePriceTFValue.isEmpty()) {
                onTwoDialogShow(dialogTextId = R.string.MODIFY_WHOLESALE_PRICE_EMPTY)
                return@launch
            }
            else{
                if(wholeSalePriceTFValue != products.wholeSalePrice.toString()){
                    onOneDialogShow(dialogTextId = R.string.MODIFY_QUIT_WITHOUT_SAVE)
                    return@launch
                }
            }
            onNavigateToProductListScreen()
        }
    }

    private fun getAllBrand(){
        viewModelScope.launch(Dispatchers.IO) {
            val brandList = databaseRepo.getAllBrands()

            _viewState.update {
                it.copy(
                    brandEntityList= brandList
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
            val categoryList = databaseRepo.getAllCategories()

            _viewState.update {
                it.copy(
                    categoryEntityList = categoryList
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
            val modelList = databaseRepo.getAllModels()

            _viewState.update {
                it.copy(
                    modelEntityList = modelList
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

    private fun getBrandId(brand: String): BrandEntity {
        var brandEntity = BrandEntity()
        val brandEntityList = _viewState.value.brandEntityList
        brandEntityList.forEach{
                b -> if(b.brandName == brand) brandEntity = b
        }

        return brandEntity
    }

    private fun getCategoryId(category: String): CategoryEntity{
        var categoryEntity = CategoryEntity()
        val categoryEntityList = _viewState.value.categoryEntityList
        categoryEntityList.forEach{
                c -> if(c.categoryName == category) categoryEntity = c
        }

        return categoryEntity
    }

    private fun getModelId(model: String): ModelEntity {
        var modelEntity = ModelEntity()
        val modelEntityList = _viewState.value.modelEntityList
        modelEntityList.forEach{
                m -> if(m.modelName == model) modelEntity = m
        }

        return modelEntity
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

    private fun onNavigateToProductListScreen() {
        appNavigator.tryNavigateTo(Destination.ProductListScreen.fullRoute)
    }

    fun onNavigateBackToProductListScreen() {
        onOneDialogDismiss()
        onNavigateToProductListScreen()
    }

    private fun onOneDialogShow(dialogTextId : Int){
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    textShowErrorDialogOneId = dialogTextId,
                    isShowErrorDialogOne = true
                )
            }
        }
    }

    fun onOneDialogDismiss() {
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    textShowErrorDialogOneId = -1,
                    isShowErrorDialogOne = false
                )
            }
        }
    }

    private fun onTwoDialogShow(dialogTextId : Int){
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    textShowErrorDialogTwoId = dialogTextId,
                    isShowErrorDialogTwo = true
                )
            }
        }
    }

    fun onTwoDialogDismiss() {
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    textShowErrorDialogTwoId = -1,
                    isShowErrorDialogTwo = false
                )
            }
        }
    }
}