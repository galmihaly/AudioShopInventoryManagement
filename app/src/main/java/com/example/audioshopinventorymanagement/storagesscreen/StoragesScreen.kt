package com.example.audioshopinventorymanagement.storagesscreen

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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.audioshopinventorymanagement.AllViewComponents
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.loginscreen.LoginScreenComponents
import com.example.audioshopinventorymanagement.productlistscreen.ProducListScreenComponents
import com.example.audioshopinventorymanagement.ui.theme.BLUE
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import com.example.audioshopinventorymanagement.ui.theme.LIGHT_GRAY
import com.example.audioshopinventorymanagement.warehousesscreen.WareHousesScreenComponents

@Composable
fun StoragesScreen(
    viewModel: StoragesScreenViewModel = hiltViewModel()
) {
    val searchedStorageListState = viewModel.viewState.collectAsState().value.searchedStoragesList
    val currentCapacityColor = viewModel.viewState.collectAsState().value.currentCapacityColor
    val maxCapacityColor = viewModel.viewState.collectAsState().value.maxCapacityColor

    val searchFieldValue = viewModel.viewState.collectAsState().value.searchFieldValue
    val allMatches = viewModel.viewState.collectAsState().value.allMatches

    val isShowErrorDialog = viewModel.viewState.collectAsState().value.isShowErrorDialog
    val dialogText = viewModel.viewState.collectAsState().value.textShowErrorDialog

    Scaffold (
        topBar = {
            AllViewComponents.HeadLineWithText(
                headLineText = "Storages",
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
                        onClick = { viewModel.onNavigateToWareHousesScreen() }
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
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 10.dp
                    ),
            ){
                AllViewComponents.SearchField(
                    value = "Barcode",
                    textFieldValue = searchFieldValue,
                    textChangeFunction = { viewModel.filterListBySearchValue(it) },
                    deleteValueChange = { viewModel.filterListBySearchValue("") }
                )
                StoragesScreenComponents.MatchesText(
                    text = "All Matches: $allMatches"
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    itemsIndexed(searchedStorageListState) { index, storage ->
                        val pIndex = index + 1

                        StoragesScreenComponents.StorageCard(
                            cardNumber = pIndex,
                            cardStorage = storage,
                            currentQuantityColor = currentCapacityColor,
                            maxQuantityColor = maxCapacityColor,
                            onClick = { viewModel.onNavigateProductsOverviewScreen(storage.storageId) },
                        )
                    }
                }
            }
        }

        LoginScreenComponents.ErrorDialog(
            isShowErrorDialog = isShowErrorDialog,
            dialogText = dialogText,
            buttonText = "OK",
            dialogDismissFunction = { viewModel.onDialogDismiss() }
        )
    }
}