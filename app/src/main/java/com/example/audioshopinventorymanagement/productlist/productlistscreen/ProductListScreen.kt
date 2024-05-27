package com.example.audioshopinventorymanagement.productlist.productlistscreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.audioshopinventorymanagement.AllViewComponents
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.productlist.SharedViewModel
import com.example.audioshopinventorymanagement.ui.theme.Blue
import com.example.audioshopinventorymanagement.ui.theme.ERROR_RED
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import com.example.audioshopinventorymanagement.ui.theme.LIGHT_GRAY
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductListScreen(
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    val productListState = sharedViewModel.productViewState.collectAsState().value.productList
    val searchFieldValue = sharedViewModel.productViewState.collectAsState().value.searchFieldValue
    val isExpandCard = sharedViewModel.productViewState.collectAsState().value.isExpandCard
    val allMatches = sharedViewModel.productViewState.collectAsState().value.allMatches

    /*productListState = sharedViewModel.productViewState.collectAsState(initial = ProductViewState()).value.productList
    if(productListState.isNotEmpty()){
        Log.e("barcode1", productListState.get(0).barcode)
        Log.e("productType1", productListState.get(0).productType)
        Log.e("productName1", productListState.get(0).productName)
        Log.e("productId1", productListState.get(0).productId)
        Log.e("productName1", productListState.get(0).productName)
        Log.e("productType1", productListState.get(0).productType)
        Log.e("basePrice1", productListState.get(0).basePrice.toString())
        Log.e("wholeSalePrice1", productListState.get(0).wholeSalePrice.toString())
        Log.e("warehouseId1", productListState.get(0).warehouseId)
        Log.e("storageId1", productListState.get(0).storageId)
    }*/


    /*LaunchedEffect(true) {
        sharedViewModel.productViewState.collect { product ->
            if(productListState.isNotEmpty()){
                Log.e("barcode1", product.get(0).barcode)
                Log.e("productType1", product.get(0).productType)
                Log.e("productName1", product.get(0).productName)
                Log.e("productId1", product.get(0).productId)
                Log.e("productName1", product.get(0).productName)
                Log.e("productType1", product.get(0).productType)
                Log.e("basePrice1", product.get(0).basePrice.toString())
                Log.e("wholeSalePrice1", product.get(0).wholeSalePrice.toString())
                Log.e("warehouseId1", product.get(0).warehouseId)
                Log.e("storageId1", product.get(0).storageId)
            }

           /* Log.e("searchFieldValue", product.get(0).searchFieldValue)
            Log.e("isExpandCard", product.get(0).isExpandCard.toString())
            Log.e("allMatches", product.get(0).allMatches.toString())*/

            productListState = product
            /*searchFieldValue = product.searchFieldValue
            isExpandCard = product.isExpandCard
            allMatches = product.allMatches*/
        }
    }*/

    Scaffold (
        topBar = {
            AllViewComponents.HeadLineWithText(
                headLineText = "New Products List",
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                contentPadding = PaddingValues(0.dp),
                containerColor = LIGHT_GRAY
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.Absolute.Center
                ) {
                    AllViewComponents.NavigationButtons(
                        buttonLogoId = R.drawable.back_logo,
                        buttonLogoHeight = 40.dp,
                        buttonLogoWidth = 40.dp,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        backgroundColor = GREEN,
                        onClick = { sharedViewModel.onNavigateToStartScreen()}
                    )
                    AllViewComponents.NavigationButtons(
                        buttonLogoId = R.drawable.add_list_logo,
                        buttonLogoHeight = 40.dp,
                        buttonLogoWidth = 40.dp,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        backgroundColor = Blue,
                        onClick = { sharedViewModel.onNavigateToNewItemScreen() }
                    )
                    AllViewComponents.NavigationButtons(
                        buttonLogoId = R.drawable.delete_list_logo,
                        buttonLogoHeight = 40.dp,
                        buttonLogoWidth = 40.dp,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        backgroundColor = ERROR_RED,
                        onClick = {}
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(LIGHT_GRAY)
                .padding(paddingValues)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
            ) {
                AllViewComponents.SearchField(
                    value = "Search",
                    textFieldValue = searchFieldValue,
                    onValueChange = {},
                    deleteValueChange = {}
                )
                AllViewComponents.MatchesText(text = "All Matches: $allMatches")
                LazyColumn {
                    itemsIndexed(productListState) { index, product ->
                        AllViewComponents.ItemCard(
                            cardNumber = index + 1,
                            cardProduct = product,
                            modifyButtonLogo = R.drawable.modify_logo,
                            deleteButtonLogo = R.drawable.delete_x_logo,
                            modifyCardFunction = { sharedViewModel.onNavigateToModifyItemScreen() },
                            deleteCardFunction = {},
                            expandedCard = isExpandCard
                        )
                    }
                }
            }
        }
    }
}