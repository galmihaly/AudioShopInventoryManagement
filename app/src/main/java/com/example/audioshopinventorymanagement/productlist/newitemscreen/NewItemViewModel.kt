package com.example.audioshopinventorymanagement.productlist.newitemscreen

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.jwt.JWT
import com.example.audioshopinventorymanagement.jwttokensdatastore.JwtTokenRepository
import com.example.audioshopinventorymanagement.navigation.AppNavigator
import com.example.audioshopinventorymanagement.navigation.Destination
import com.example.audioshopinventorymanagement.productlist.productlistscreen.UserDetailsState
import com.example.audioshopinventorymanagement.room.entities.BrandEntity
import com.example.audioshopinventorymanagement.room.entities.CategoryEntity
import com.example.audioshopinventorymanagement.room.entities.ModelEntity
import com.example.audioshopinventorymanagement.room.entities.ProductEntity
import com.example.audioshopinventorymanagement.room.repositories.ProductDatabaseRepository
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewItemViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val databaseRepo: ProductDatabaseRepository,
    private val jwtTokenRepository: JwtTokenRepository
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

    private val _viewState = MutableStateFlow(NewItemViewState())
    val viewState = _viewState.asStateFlow()

    private val _userDetailsState = MutableStateFlow(UserDetailsState())

    private val productIdSeparator = '-'

    init {
        getJwtTokenFromRepository()

        getAllBrand()
        getAllCategory()
        getAllModel()
    }

    fun addItemToProductList(){
        viewModelScope.launch(Dispatchers.IO) {
            val barcodeTFValue = _viewState.value.barcodeTFValue

            val brandDDValue = _viewState.value.brandDDValue
            val modelDDValue = _viewState.value.modelDDValue
            val categoryDDValue = _viewState.value.categoryDDValue

            val brandDetails = getBrandId(brand = brandDDValue)
            val categoryDetails = getCategoryId(category = categoryDDValue)
            val modelDetails = getModelId(model = modelDDValue)

            val basePriceTFValue = _viewState.value.basePriceTFValue
            val wholeSalePriceTFValue = _viewState.value.wholeSalePriceTFValue
            val storageTFValue = _viewState.value.storageTFValue

            val userName = _userDetailsState.value.name
            val userDeviceId = _userDetailsState.value.deviceId

            val warehouseTFValue = _viewState.value.warehouseTFValue

            if (warehouseTFValue == "") {
                onDialogShow(dialogText = "The Warehouse Identifier field cannot be empty!")
                return@launch
            }
            else if (storageTFValue == "") {
                onDialogShow(dialogText = "The Storage Identifier field cannot be empty!")
                return@launch
            }
            else if (brandDDValue == "") {
                onDialogShow(dialogText = "The Brand field cannot be empty!")
                return@launch
            }
            else if (categoryDDValue == "") {
                onDialogShow(dialogText = "The Category field field cannot be empty!")
                return@launch
            }
            else if (modelDDValue == "") {
                onDialogShow(dialogText = "The Model field cannot be empty!")
                return@launch
            }
            else if (barcodeTFValue == "") {
                onDialogShow(dialogText = "The barcode field cannot be empty!")
                return@launch
            }
            else if (basePriceTFValue == "") {
                onDialogShow(dialogText = "The Base Price field cannot be empty!")
                return@launch
            }
            else if (wholeSalePriceTFValue == "") {
                onDialogShow(dialogText = "The WholeSale Price field cannot be empty!")
                return@launch
            }


            val productId = createProductId(brandDetails.brandId!!, categoryDetails.categoryId!!, modelDetails.modelId!!)
            val productName = createProductName(brandDDValue, modelDDValue)

            val daoEntity = ProductEntity(
                productId = productId,
                productName = productName,
                brandId = brandDetails.brandId,
                brandName = brandDetails.brandName,
                categoryId = categoryDetails.categoryId,
                categoryName = categoryDetails.categoryName,
                modelId = modelDetails.modelId,
                modelName = modelDetails.modelName,
                basePrice = basePriceTFValue.toInt(),
                wholeSalePrice = wholeSalePriceTFValue.toInt(),
                warehouseId = warehouseTFValue,
                storageId = storageTFValue,
                recorderName = userName,
                deviceId = userDeviceId,
                barcode = barcodeTFValue
            )

            val products = databaseRepo.getProductByBarcode(barcodeTFValue)
            if(products == null){
                databaseRepo.insertProduct(daoEntity)
                onNavigateToProductListScreen()
            }
            else{
                onDialogShow("The Product has already been added to list!")
            }
        }
    }

    private fun getJwtTokenFromRepository(){
        viewModelScope.launch(Dispatchers.IO) {
            val tokens = jwtTokenRepository.getAccessJwt()
            val token = JWT(tokens.accessToken)

            val emailClaim = token.getClaim("email").asString()!!
            val roleClaim = token.getClaim("role").asString()!!
            val nameClaim = token.getClaim("username").asString()!!
            val deviceActiveClaim = token.getClaim("device_active").asString()!!
            val deviceIdClaim = token.getClaim("device_id").asString()!!
            val warehouseIdClaim = token.getClaim("warehouse_id").asString()!!

            _userDetailsState.update {
                it.copy(
                    email = emailClaim,
                    role = roleClaim,
                    name = nameClaim,
                    deviceActive = deviceActiveClaim,
                    deviceId = deviceIdClaim,
                    warehouseId = warehouseIdClaim
                )
            }

            if(roleClaim != "" && warehouseIdClaim != ""){
                if(roleClaim == "ADMIN"){
                    _viewState.update {
                        it.copy(
                            warehouseTFValue = warehouseIdClaim,
                            warehouseTFIsEnabled = true,
                            warehouseTFIsEnabledBorderColor = GREEN,
                            warehouseTFIsEnabledTextColor = Color.White,
                        )
                    }
                }
                else if(roleClaim == "BLIND_INVENTORY"){
                    _viewState.update {
                        it.copy(
                            warehouseTFValue = warehouseIdClaim,
                            warehouseTFIsEnabled = false,
                            warehouseTFIsEnabledBorderColor = Color.Gray,
                            warehouseTFIsEnabledTextColor = Color.Gray,
                        )
                    }
                }
            }
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

    private fun onDialogShow(dialogText : String){
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