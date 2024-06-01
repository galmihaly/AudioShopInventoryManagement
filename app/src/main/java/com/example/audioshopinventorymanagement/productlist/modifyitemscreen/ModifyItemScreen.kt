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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.audioshopinventorymanagement.AllViewComponents
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.productlist.newitemscreen.NewItemScreenComponents
import com.example.audioshopinventorymanagement.ui.theme.BLUE
import com.example.audioshopinventorymanagement.ui.theme.ERROR_RED
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import com.example.audioshopinventorymanagement.ui.theme.LIGHT_GRAY

@Composable
fun ModifyItemScreen(
    viewModel: ModifyItemViewModel = hiltViewModel()
) {
    val warehouseTFValue = viewModel.viewState.collectAsState().value.warehouseTFValue
    val warehouseTFIsEnabled = viewModel.viewState.collectAsState().value.warehouseTFIsEnabled
    val warehouseTFIsEnabledBorderColor = viewModel.viewState.collectAsState().value.warehouseTFIsEnabledBorderColor
    val warehouseTFIsEnabledTextColor = viewModel.viewState.collectAsState().value.warehouseTFIsEnabledTextColor

    val storageTFValue = viewModel.viewState.collectAsState().value.storageTFValue

    val brandDDValue = viewModel.viewState.collectAsState().value.brandDDValue
    val brandDDList = viewModel.viewState.collectAsState().value.brandDDList
    val brandExpandedDD = viewModel.viewState.collectAsState().value.brandExpandedDD

    val categoryDDValue = viewModel.viewState.collectAsState().value.categoryDDValue
    val categoryDDList = viewModel.viewState.collectAsState().value.categoryDDList
    val categoryExpandedDD = viewModel.viewState.collectAsState().value.categoryExpandedDD

    val modelDDValue = viewModel.viewState.collectAsState().value.modelDDValue
    val modelDDList = viewModel.viewState.collectAsState().value.modelDDList
    val modelExpandedDD = viewModel.viewState.collectAsState().value.modelExpandedDD

    val barcodeTFValue = viewModel.viewState.collectAsState().value.barcodeTFValue
    val basePriceTFValue = viewModel.viewState.collectAsState().value.basePriceTFValue
    val wholeSalePriceTFValue = viewModel.viewState.collectAsState().value.wholeSalePriceTFValue

    val isShowErrorDialog = viewModel.viewState.collectAsState().value.isShowErrorDialog
    val dialogText = viewModel.viewState.collectAsState().value.textShowErrorDialog

    Scaffold (
        topBar = {
            AllViewComponents.HeadLineWithText(
                headLineText = "Modify Item",
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
                        onClick = { viewModel.backToProductListScreen() }
                    )
                    AllViewComponents.NavigationButtons(
                        buttonLogoId = R.drawable.save_logo,
                        buttonLogoHeight = 40.dp,
                        buttonLogoWidth = 40.dp,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        backgroundColor = BLUE,
                        onClick = { viewModel.saveChangesOnItem() }
                    )
                    AllViewComponents.NavigationButtons(
                        buttonLogoId = R.drawable.delete_x_logo,
                        buttonLogoHeight = 40.dp,
                        buttonLogoWidth = 40.dp,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        backgroundColor = ERROR_RED,
                        onClick = { viewModel.deleteAllTextField() }
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
                Row (horizontalArrangement = Arrangement.SpaceBetween)
                {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1.0f)
                            .padding(end = 5.dp)
                            .wrapContentHeight()
                    ){
                        NewItemScreenComponents.ModifyTextField(
                            text = "Warehouse Identifier:",
                            textFieldValue = warehouseTFValue,
                            textChangeFunction = { viewModel.updateWarehouseTFValue(it) },
                            keyboardType = KeyboardType.Text,
                            isEnabled = warehouseTFIsEnabled,
                            colorBorderState = warehouseTFIsEnabledBorderColor,
                            colorTextState = warehouseTFIsEnabledTextColor
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1.0f)
                            .padding(start = 5.dp)
                            .wrapContentHeight()
                    ){
                        NewItemScreenComponents.ModifyTextField(
                            text = "Storage Identifier:",
                            textFieldValue = storageTFValue,
                            textChangeFunction = { viewModel.updateStockTFValue(it) },
                            keyboardType = KeyboardType.Text,
                            isEnabled = true,
                            colorBorderState = GREEN,
                            colorTextState = Color.White
                        )
                    }
                }
                Row (horizontalArrangement = Arrangement.SpaceBetween)
                {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1.0f)
                            .padding(end = 5.dp)
                            .wrapContentHeight()
                    ){
                        Column {
                            NewItemScreenComponents.ModifyDropDownMenu(
                                text = "Brand:",
                                dropdownList = brandDDList,
                                currentText = brandDDValue,
                                expandedDropDown = brandExpandedDD,
                                expandedFunction = { viewModel.updateBrandExpandedDropDown(it) },
                                currentFunction = { viewModel.updateBrandDropDownValue(it) },
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1.0f)
                            .padding(start = 5.dp)
                            .wrapContentHeight()
                    ){
                        Column {
                            NewItemScreenComponents.ModifyDropDownMenu(
                                text = "Category:",
                                dropdownList = categoryDDList,
                                currentText = categoryDDValue,
                                expandedDropDown = categoryExpandedDD,
                                expandedFunction = { viewModel.updateCategoryExpandedDropDown(it) },
                                currentFunction = { viewModel.updateCategoryDropDownValue(it) }
                            )
                        }
                    }
                }
                NewItemScreenComponents.ModifyDropDownMenu(
                    text = "Model:",
                    dropdownList = modelDDList,
                    currentText = modelDDValue,
                    expandedDropDown = modelExpandedDD,
                    expandedFunction = { viewModel.updateModelExpandedDropDown(it) },
                    currentFunction = { viewModel.updateModelDropDownValue(it) }
                )
                NewItemScreenComponents.ModifyTextField(
                    text = "Barcode:",
                    textFieldValue = barcodeTFValue,
                    textChangeFunction = { viewModel.updateBarcodeTFValue(it) },
                    keyboardType = KeyboardType.Number,
                    isEnabled = true,
                    colorBorderState = GREEN,
                    colorTextState = Color.White
                )
                Row (horizontalArrangement = Arrangement.SpaceBetween)
                {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1.0f)
                            .padding(end = 5.dp)
                            .wrapContentHeight()
                    ){
                        NewItemScreenComponents.ModifyTextField(
                            text = "Base Price:",
                            textFieldValue = basePriceTFValue,
                            textChangeFunction = { viewModel.updateBasePriceTFValue(it) },
                            keyboardType = KeyboardType.Number,
                            isEnabled = true,
                            colorBorderState = GREEN,
                            colorTextState = Color.White
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1.0f)
                            .padding(start = 5.dp)
                            .wrapContentHeight()
                    ){
                        NewItemScreenComponents.ModifyTextField(
                            text = "WholeSale Price:",
                            textFieldValue = wholeSalePriceTFValue,
                            textChangeFunction = { viewModel.updateWholeSalePriceTFValue(it) },
                            keyboardType = KeyboardType.Number,
                            isEnabled = true,
                            colorBorderState = GREEN,
                            colorTextState = Color.White
                        )
                    }
                }
            }
        }

        ModifyItemScreenComponents.ErrorDialog(
            isShowErrorDialog = isShowErrorDialog,
            dialogText = dialogText,
            readyButtonText = "YES",
            cancelButtonText = "NO",
            dialogDismissFunction = { viewModel.onDialogDismiss() },
            navigateFunction = { viewModel.onNavigateBackToProductListScreen() }
        )
    }
}