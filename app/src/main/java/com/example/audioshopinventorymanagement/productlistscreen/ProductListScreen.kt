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
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.audioshopinventorymanagement.AllViewComponents
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.ui.theme.Blue
import com.example.audioshopinventorymanagement.ui.theme.Error_Red
import com.example.audioshopinventorymanagement.ui.theme.Green
import com.example.audioshopinventorymanagement.ui.theme.Light_Gray

@Composable
fun ProductListScreen(
    productListScreenViewModel: ProductListScreenViewModel = hiltViewModel()
) {
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
                containerColor = Light_Gray
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
                        backgroundColor = Green,
                        onClick = { productListScreenViewModel.onNavigateToStartScreen()}
                    )
                    AllViewComponents.NavigationButtons(
                        buttonLogoId = R.drawable.add_list_logo,
                        buttonLogoHeight = 40.dp,
                        buttonLogoWidth = 40.dp,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        backgroundColor = Blue,
                        onClick = { productListScreenViewModel.onNavigateToNewitemScreen() }
                    )
                    AllViewComponents.NavigationButtons(
                        buttonLogoId = R.drawable.delete_list_logo,
                        buttonLogoHeight = 40.dp,
                        buttonLogoWidth = 40.dp,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        backgroundColor = Error_Red,
                        onClick = {}
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Light_Gray)
                .padding(paddingValues)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
            ) {
                AllViewComponents.SearchField(
                    value = "Search",
                    textFieldValue = "",
                    onValueChange = {},
                    deleteValueChange = {}
                )
                AllViewComponents.MatchesText(text = "All Matches: " + "12345")
                AllViewComponents.ItemCard(
                    cardNumber = 1,
                    deleteButtonLogo = R.drawable.card_delete_logo,
                    modifyButtonLogo = R.drawable.modify_logo,
                    cardIndicatorLogo = R.drawable.bottom_arrow_logo,
                    deleteCardFunction = {},
                    modifyCardFunction = {},
                    expandedCardFunction = false
                )
            }
        }
    }
}