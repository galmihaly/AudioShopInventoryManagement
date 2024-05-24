package com.example.audioshopinventorymanagement.porductlist.newitemscreen

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.audioshopinventorymanagement.AllViewComponents
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.porductlist.SharedViewModel
import com.example.audioshopinventorymanagement.ui.theme.Blue
import com.example.audioshopinventorymanagement.ui.theme.ERROR_RED
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import com.example.audioshopinventorymanagement.ui.theme.LIGHT_GRAY

@Composable
fun NewItemScreen(
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    Scaffold (
        topBar = {
            AllViewComponents.HeadLineWithText(
                headLineText = "New Item",
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
                        onClick = {}
                    )
                    AllViewComponents.NavigationButtons(
                        buttonLogoId = R.drawable.add_list_logo,
                        buttonLogoHeight = 40.dp,
                        buttonLogoWidth = 40.dp,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        backgroundColor = Blue,
                        onClick = {}
                    )
                    AllViewComponents.NavigationButtons(
                        buttonLogoId = R.drawable.delete_x_logo,
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
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
            ) {
                NewItemScreenComponents.TwoModifyTextField(
                    textField1 = "Warehouse Identifier:",
                    textFieldValue1 = "",
                    keyboardType1 = KeyboardType.Text,
                    /*colorBorder1 = GREEN,
                    textColor1 = Color.White,
                    isEnabled1 = true,*/
                    textField2 = "Stock Identifier:",
                    textFieldValue2 = "",
                    keyboardType2 = KeyboardType.Text,
                   /* colorBorder2 = GREEN,
                    textColor2 = Color.White,
                    isEnabled2 = false*/
                )
                NewItemScreenComponents.TwoModifyDropDownMenu(
                    text1 = "Brand:",
                    optionsList1 = listOf(
                        "Sennheiser HD 560s",
                        "Bill Payment",
                        "Recharges",
                        "Outing",
                        "Other"
                    ),
                    currentSelected1 = "",
                    dropDownIsExpand1 = false,
                    /*colorBorder1 = GREEN,
                    textColor1 = Color.White,
                    isEnabled1 = false,*/

                    text2 = "Category:",
                    optionsList2 = listOf(
                        "Sennheiser HD 560s",
                        "Bill Payment",
                        "Recharges",
                        "Outing",
                        "Other"
                    ),
                    currentSelected2 = "",
                    dropDownIsExpand2 = false,
                    /*colorBorder2 = GREEN,
                    textColor2 = Color.White,
                    isEnabled2 = false*/
                )
                NewItemScreenComponents.ModifyDropDownMenu(
                    text1 = "Model:",
                    optionsList1 = listOf(
                        "Sennheiser HD 560s",
                        "Bill Payment",
                        "Recharges",
                        "Outing",
                        "Other"
                    ),
                    currentSelected1 = "",
                    dropDownIsExpand1 = false,
                    /*colorBorder = GREEN,
                    textColor = Color.White,
                    isEnabled = false*/
                )
                NewItemScreenComponents.ModifyTextField(
                    text = "Barcode:",
                    textFieldValue = "",
                    keyboardType = KeyboardType.Number,
                    /*colorBorder = GREEN,
                    textColor = Color.White,
                    isEnabled = false*/
                )
                NewItemScreenComponents.TwoModifyTextField(
                    textField1 = "Base Price:",
                    textFieldValue1 = "",
                    keyboardType1 = KeyboardType.Number,
                    /*colorBorder1 = GREEN,
                    textColor1 = Color.White,
                    isEnabled1 = false,*/
                    textField2 = "WholeSale Price:",
                    textFieldValue2 = "",
                    keyboardType2 = KeyboardType.Number,
                    /*colorBorder2 = GREEN,
                    textColor2 = Color.White,
                    isEnabled2 = false*/
                )
            }
        }
    }
}