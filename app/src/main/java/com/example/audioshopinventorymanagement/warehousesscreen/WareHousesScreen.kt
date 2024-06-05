package com.example.audioshopinventorymanagement.warehousesscreen

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
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import com.example.audioshopinventorymanagement.ui.theme.LIGHT_GRAY

@Composable
fun WareHousesScreen(
    viewModel: WareHousesScreenViewModel = hiltViewModel()
) {
    val warehouseListState = viewModel.viewState.collectAsState().value.warehouseList

    val isShowErrorDialog = viewModel.viewState.collectAsState().value.isShowErrorDialog
    val dialogText = viewModel.viewState.collectAsState().value.textShowErrorDialog


    Scaffold (
        topBar = {
            AllViewComponents.HeadLineWithText(
                headLineText = "WareHouses",
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
                        onClick = { viewModel.onNavigateToStartScreen() }
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
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp, vertical = 20.dp)
            ) {
                itemsIndexed(warehouseListState) { index, warehouse ->
                    val pIndex = index + 1

                    WareHousesScreenComponents.WareHouseCard(
                        cardNumber = pIndex,
                        cardWarehouse = warehouse,
                        onClick = { viewModel.onNavigateToCategoriesScreen(warehouse.id.toString()) },
                    )
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