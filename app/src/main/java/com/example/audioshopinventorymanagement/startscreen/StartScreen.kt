package com.example.audioshopinventorymanagement.startscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.audioshopinventorymanagement.AllViewComponents
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import com.example.audioshopinventorymanagement.ui.theme.LIGHT_GRAY

@Composable
fun StartScreen(
    startScreenViewModel: StartScreenViewModel = hiltViewModel()
) {
    Scaffold (
        topBar = {
            AllViewComponents.HeadLineWithTextAndLogo(
                headLineText = "Inventory Management",
                headLineLogo = R.drawable.audioshop_logo
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth().height(60.dp),
                contentPadding = PaddingValues(0.dp),
                containerColor = GREEN
            ) {
                AllViewComponents.BackButton(
                    buttonLogoId = R.drawable.back_logo,
                    buttonLogoHeight = 40.dp,
                    buttonLogoWidth = 40.dp,
                    onClick = { startScreenViewModel.onNavigateToLoginScreen() }
                )
            }
        }
    ) { paddingValues ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(LIGHT_GRAY)
                .padding(paddingValues)
        ){
            Column{
                StartScreenComponents.ButtonWithLogoAndText(
                    text = "Create New List",
                    logoId = R.drawable.controlled_inventory_logo,
                    logoHeight = 54.dp,
                    logoWidth = 46.dp,
                    onClick = { startScreenViewModel.onNavigateToProductListScreen() }
                )
                Spacer(modifier = Modifier.height(50.dp))
                StartScreenComponents.ButtonWithLogoAndText(
                    text = "Stocks",
                    logoId = R.drawable.stocks_logo,
                    logoHeight = 50.dp,
                    logoWidth = 50.dp,
                    onClick = { startScreenViewModel.onNavigateToWareHousesScreen() }
                )
            }
        }
    }
}