package com.example.audioshopinventorymanagement.productlist.productlistscreen

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.jwt.JWT
import com.example.audioshopinventorymanagement.authentication.repositories.ProductApiRepository
import com.example.audioshopinventorymanagement.authentication.requests.ProductRequest
import com.example.audioshopinventorymanagement.authentication.requests.SaveProductListRequest
import com.example.audioshopinventorymanagement.authentication.requests.SaveProductRequest
import com.example.audioshopinventorymanagement.authentication.responses.CategoryDetails
import com.example.audioshopinventorymanagement.authentication.responses.sealed.ProductApiResponse
import com.example.audioshopinventorymanagement.jwttokensdatastore.JwtTokenRepository
import com.example.audioshopinventorymanagement.navigation.AppNavigator
import com.example.audioshopinventorymanagement.navigation.Destination
import com.example.audioshopinventorymanagement.room.entities.BrandEntity
import com.example.audioshopinventorymanagement.room.entities.CategoryEntity
import com.example.audioshopinventorymanagement.room.entities.ModelEntity
import com.example.audioshopinventorymanagement.room.repositories.ProductDatabaseRepository
import com.example.audioshopinventorymanagement.room.entities.ProductEntity
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val productDatabaseRepository: ProductDatabaseRepository,
    private val productApiRepository: ProductApiRepository,
    private val jwtTokenRepository: JwtTokenRepository
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

    private val _viewState = MutableStateFlow(ProductViewState())
    val viewState = _viewState.asStateFlow()

    private val _userDetailsState = MutableStateFlow(UserDetailsState())

    init {
        getProductListFromRoom()
        getJwtTokenFromRepository()

        getAllBrandFromApi()
        getAllCategoryFromApi()
        getAllModelFromApi()
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
        }
    }

    private fun getAllBrandFromApi(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = productApiRepository.getAllBrand()

            when (response){
                is ProductApiResponse.BrandSuccess -> {
                    if(response.data.statusCode == 200){
                        val brandList = response.data.brandDetails!!

                        for (b in brandList){
                            val existedBrandEntity = productDatabaseRepository.getBrandById(b.brandId!!)
                            if(existedBrandEntity == null){
                                productDatabaseRepository.insertBrand(BrandEntity(
                                    brandId = b.brandId,
                                    brandName = b.brandName
                                ))
                            }
                        }
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
        }
    }

    private fun getAllCategoryFromApi(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = productApiRepository.getAllCategory()

            when (response){
                is ProductApiResponse.CategorySuccess -> {
                    if(response.data.statusCode == 200){
                        val categoryList = response.data.categoryDetails!!

                        for (c in categoryList){
                            val existedCategoryEntity = productDatabaseRepository.getCategoryById(c.categoryId!!)
                            if(existedCategoryEntity == null){
                                productDatabaseRepository.insertCategory(CategoryEntity(
                                    categoryId = c.categoryId,
                                    categoryName = c.categoryName
                                ))
                            }
                        }
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
        }
    }

    private fun getAllModelFromApi(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = productApiRepository.getAllModel()

            when (response){
                is ProductApiResponse.ModelSuccess -> {
                    if(response.data.statusCode == 200){
                        val modelList = response.data.modelDetails!!
                        for (m in modelList){
                            val existedModelEntity = productDatabaseRepository.getModelById(m.modelId!!)
                            if(existedModelEntity == null){
                                productDatabaseRepository.insertModel(ModelEntity(
                                    modelId = m.modelId,
                                    modelName = m.modelName
                                ))
                            }
                        }
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
        }
    }

    fun filterListBySearchValue(newSearchText: String) {
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    searchFieldValue = newSearchText
                )
            }

            val productList = productDatabaseRepository.getAllProducts()

            val searchedProductList = productList.filter {
                it.barcode!!.startsWith(newSearchText)
            }.toMutableList()

            _viewState.update {
                it.copy(
                    productList = searchedProductList,
                    allMatches = searchedProductList.size
                )
            }
        }
    }

    fun sendListToApi() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentProductList = _viewState.value.productList
            val requestProductList : MutableList<SaveProductRequest> = mutableListOf()

            for (p in currentProductList){
                requestProductList.add(SaveProductRequest(
                    brandId = p.brandId,
                    categoryId = p.categoryId,
                    modelId = p.modelId,
                    warehouseId = p.warehouseId,
                    storageId = p.storageId,
                    basePrice = p.basePrice,
                    wholeSalePrice = p.wholeSalePrice,
                    barcode = p.barcode
                ))
            }

            val request = SaveProductListRequest(
                userName = _userDetailsState.value.name,
                deviceId = _userDetailsState.value.deviceId,
                productList = requestProductList
            )

            val response = productApiRepository.sendProductList(request)

            when (response){
                is ProductApiResponse.ProductSuccess -> {
                    if(response.data.statusCode == 200){
                        onDialogShow("Save Products is success!")
                        productDatabaseRepository.deleteAllProduct()
                        _viewState.update {
                            it.copy(
                                productList = mutableListOf()
                            )
                        }
                    }
                }
                is ProductApiResponse.ProductError -> {
                    if(response.data.statusCode == 400){
                        onDialogShow("Saving of the products is unsuccessful!")
                    }
                    else if(response.data.statusCode == 409){
                        onDialogShow("Saving of the products is unsuccessful!")
                    }
                }
                is ProductApiResponse.Exception -> {
                    onDialogShow(response.exceptionMessage)
                }
                else -> {}
            }
        }
    }

    private fun getProductListFromRoom(){
        viewModelScope.launch(Dispatchers.IO) {
            val productList = productDatabaseRepository.getAllProducts()

            _viewState.update {
                it.copy(
                    productList = productList,
                    allMatches = productList.size
                )
            }
        }
    }

    fun deleteItemFromProductList(product: ProductEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            productDatabaseRepository.deleteProduct(product)

            val productList = productDatabaseRepository.getAllProducts()

            _viewState.update {
                it.copy(
                    productList = productList,
                    allMatches = productList.size
                )
            }
        }
    }

    fun deleteProductList() {
        viewModelScope.launch(Dispatchers.IO) {
            productDatabaseRepository.deleteAllProduct()

            _viewState.update {
                it.copy(
                    productList = mutableListOf(),
                    allMatches = 0
                )
            }
        }
    }

    fun onNavigateToNewItemScreen() {
        appNavigator.tryNavigateTo(Destination.NewItemScreen.fullRoute)
    }

    fun onNavigateToModifyItemScreen(barcode : String) {
        appNavigator.tryNavigateTo(Destination.ModifyItemScreen.passParameters(barcode))
    }

    fun onNavigateToStartScreen() {
        appNavigator.tryNavigateTo(Destination.StartScreen.fullRoute)
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