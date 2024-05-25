package com.example.audioshopinventorymanagement.productlist.modifyitemscreen

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.audioshopinventorymanagement.AllViewComponents
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.productlist.SharedViewModel
import com.example.audioshopinventorymanagement.productlist.newitemscreen.NewItemScreenComponents
import com.example.audioshopinventorymanagement.ui.theme.Blue
import com.example.audioshopinventorymanagement.ui.theme.ERROR_RED
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import com.example.audioshopinventorymanagement.ui.theme.LIGHT_GRAY

@Composable
fun ModifyItemScreen(
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    val warehouseTFValue = sharedViewModel.newItemViewState.collectAsState().value.warehouseTFValue
    val storageTFValue = sharedViewModel.newItemViewState.collectAsState().value.storageTFValue

    val brandDropDownValue = sharedViewModel.newItemViewState.collectAsState().value.brandDropDownValue
    val brandDropDownList = sharedViewModel.newItemViewState.collectAsState().value.brandDropDownList
    val brandExpandedDropDown = sharedViewModel.newItemViewState.collectAsState().value.brandExpandedDropDown

    val categoryDropDownValue = sharedViewModel.newItemViewState.collectAsState().value.categoryDropDownValue
    val categoryDropDownList = sharedViewModel.newItemViewState.collectAsState().value.categoryDropDownList
    val categoryExpandedDropDown = sharedViewModel.newItemViewState.collectAsState().value.categoryExpandedDropDown

    val modelDropDownValue = sharedViewModel.newItemViewState.collectAsState().value.modelDropDownValue
    val modelDropDownList = sharedViewModel.newItemViewState.collectAsState().value.modelDropDownList
    val modelExpandedDropDown = sharedViewModel.newItemViewState.collectAsState().value.modelExpandedDropDown

    val barcodeTFValue = sharedViewModel.newItemViewState.collectAsState().value.barcodeTFValue
    val basePriceTFValue = sharedViewModel.newItemViewState.collectAsState().value.basePriceTFValue
    val wholeSalePriceTFValue = sharedViewModel.newItemViewState.collectAsState().value.wholeSalePriceTFValue

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
                    textFieldValue1 = warehouseTFValue,
                    textChangeFunction1 = { sharedViewModel.updateWarehouseTFValue(it) },
                    keyboardType1 = KeyboardType.Text,
                    textField2 = "Storage Identifier:",
                    textFieldValue2 = storageTFValue,
                    textChangeFunction2 = { sharedViewModel.updateStockTFValue(it) },
                    keyboardType2 = KeyboardType.Text,
                )
                NewItemScreenComponents.TwoModifyDropDownMenu(
                    text1 = "Brand:",
                    dropdownList1 = brandDropDownList,
                    currentText1 = brandDropDownValue,
                    expandedDropDown1 = brandExpandedDropDown,
                    expandedFunction1 = { sharedViewModel.updateBrandExpandedDropDown(it) },
                    currentFunction1 = { sharedViewModel.updateBrandDropDownValue(it) },
                    text2 = "Category:",
                    dropdownList2 = categoryDropDownList,
                    currentText2 = categoryDropDownValue,
                    expandedDropDown2 = categoryExpandedDropDown,
                    expandedFunction2 = { sharedViewModel.updateCategoryExpandedDropDown(it) },
                    currentFunction2 = { sharedViewModel.updateCategoryDropDownValue(it) },
                )
                NewItemScreenComponents.ModifyDropDownMenu(
                    text = "Model:",
                    dropdownList = modelDropDownList,
                    currentText = modelDropDownValue,
                    expandedDropDown = categoryExpandedDropDown,
                    expandedFunction = { sharedViewModel.updateModelExpandedDropDown(it) },
                    currentFunction = { sharedViewModel.updateModelDropDownValue(it) }
                )
                NewItemScreenComponents.ModifyTextField(
                    text = "Barcode:",
                    textFieldValue = barcodeTFValue,
                    textChangeFunction = { sharedViewModel.updateBarcodeTFValue(it) },
                    keyboardType = KeyboardType.Number,
                )
                NewItemScreenComponents.TwoModifyTextField(
                    textField1 = "Base Price:",
                    textFieldValue1 = basePriceTFValue,
                    textChangeFunction1 = { sharedViewModel.updateBarcodeTFValue(it) },
                    keyboardType1 = KeyboardType.Number,
                    textField2 = "WholeSale Price:",
                    textFieldValue2 = wholeSalePriceTFValue,
                    textChangeFunction2 = { sharedViewModel.updateWholeSalePriceTFValue(it) },
                    keyboardType2 = KeyboardType.Number,
                )
            }
        }
    }
}