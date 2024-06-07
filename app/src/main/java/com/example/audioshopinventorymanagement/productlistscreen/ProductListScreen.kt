package com.example.audioshopinventorymanagement.productlistscreen

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
import com.example.audioshopinventorymanagement.modifyitemscreen.ModifyItemScreenComponents
import com.example.audioshopinventorymanagement.ui.theme.BLUE
import com.example.audioshopinventorymanagement.ui.theme.ERROR_RED
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import com.example.audioshopinventorymanagement.ui.theme.LIGHT_GRAY

@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel = hiltViewModel()
) {
    val productListState = viewModel.viewState.collectAsState().value.productList
    val searchFieldValue = viewModel.viewState.collectAsState().value.searchFieldValue
    val isExpandCard = viewModel.viewState.collectAsState().value.isExpandCard
    val allMatches = viewModel.viewState.collectAsState().value.allMatches

    val isShowErrorDialog = viewModel.viewState.collectAsState().value.isShowErrorDialog
    val dialogText = viewModel.viewState.collectAsState().value.textShowErrorDialog

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
                        onClick = { viewModel.onNavigateToStartScreen()}
                    )
                    AllViewComponents.NavigationButtons(
                        buttonLogoId = R.drawable.add_list_logo,
                        buttonLogoHeight = 40.dp,
                        buttonLogoWidth = 40.dp,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        backgroundColor = BLUE,
                        onClick = { viewModel.onNavigateToNewItemScreen() }
                    )
                    AllViewComponents.NavigationButtons(
                        buttonLogoId = R.drawable.delete_list_logo,
                        buttonLogoHeight = 40.dp,
                        buttonLogoWidth = 40.dp,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        backgroundColor = ERROR_RED,
                        onClick = { viewModel.deleteProductList() }
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
                    value = "Barcode",
                    textFieldValue = searchFieldValue,
                    textChangeFunction = { viewModel.filterListBySearchValue(it) },
                    deleteValueChange = { viewModel.filterListBySearchValue("") }
                )
                ProducListScreenComponents.MatchesTextAndSendButton(
                    text = "All Matches: $allMatches",
                    sendButtonFunction = { viewModel.sendListToApi() },
                    buttonLogoId = R.drawable.send_cube,
                    buttonLogoWidth = 40.dp,
                    buttonLogoHeight = 40.dp
                )
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    itemsIndexed(productListState) { index, product ->
                        val pIndex = index + 1

                        AllViewComponents.ItemCard(
                            cardNumber = pIndex,
                            cardProduct = product,
                            modifyButtonLogo = R.drawable.modify_logo,
                            deleteButtonLogo = R.drawable.delete_x_logo,
                            modifyCardFunction = { viewModel.onNavigateToModifyItemScreen(product.barcode!!) },
                            deleteCardFunction = { viewModel.deleteItemFromProductList(product) },
                            expandedCard = isExpandCard
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
}