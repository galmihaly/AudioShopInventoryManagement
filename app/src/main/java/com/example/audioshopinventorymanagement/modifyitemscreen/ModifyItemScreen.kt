package com.example.audioshopinventorymanagement.modifyitemscreen

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.audioshopinventorymanagement.AllViewComponents
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.loginscreen.LoginScreenComponents
import com.example.audioshopinventorymanagement.newitemscreen.NewItemScreenComponents
import com.example.audioshopinventorymanagement.ui.theme.BLUE
import com.example.audioshopinventorymanagement.ui.theme.ERROR_RED
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import com.example.audioshopinventorymanagement.ui.theme.LIGHT_GRAY

@Composable
fun ModifyItemScreen(
    viewModel: ModifyItemViewModel = hiltViewModel()
) {
    val context = LocalContext.current

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

    val isShowErrorDialogOne = viewModel.viewState.collectAsState().value.isShowErrorDialogOne
    val dialogTextOneId = viewModel.viewState.collectAsState().value.textShowErrorDialogOneId

    val isShowErrorDialogTwo = viewModel.viewState.collectAsState().value.isShowErrorDialogTwo
    val dialogTextTwoId = viewModel.viewState.collectAsState().value.textShowErrorDialogTwoId

    var dialogTextOne = ""
    var dialogTextTwo = ""
    if(dialogTextOneId != -1){
        dialogTextOne = context.getString(dialogTextOneId)
    }
    if(dialogTextTwoId != -1){
        dialogTextTwo = context.getString(dialogTextTwoId)
    }

    Scaffold (
        topBar = {
            AllViewComponents.HeadLineWithText(
                headLineText = stringResource(R.string.MODIFY_TOPBAR_HEADLINE_TEXT),
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
                    .padding(horizontal = 10.dp),
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
                            text = stringResource(R.string.MODIFY_WAREHOUSE_ID_TEXT),
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
                            text = stringResource(R.string.MODIFY_STORAGE_ID_TEXT),
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
                                text = stringResource(R.string.MODIFY_BRAND_TEXT),
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
                                text = stringResource(R.string.MODIFY_CATEGORY_TEXT),
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
                    text = stringResource(R.string.MODIFY_MODEL_TEXT),
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
                            text = stringResource(R.string.MODIFY_BASE_PRICE_TEXT),
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
                            text = stringResource(R.string.MODIFY_WHOLESALE_PRICE_TEXT),
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
            isShowErrorDialog = isShowErrorDialogOne,
            dialogText = dialogTextOne,
            readyButtonText = stringResource(R.string.DIALOG_BUTTON_YES_TEXT),
            cancelButtonText = stringResource(R.string.DIALOG_BUTTON_NO_TEXT),
            dialogDismissFunction = { viewModel.onOneDialogDismiss() },
            navigateFunction = { viewModel.onNavigateBackToProductListScreen() }
        )

        LoginScreenComponents.ErrorDialog(
            isShowErrorDialog = isShowErrorDialogTwo,
            dialogText = dialogTextTwo,
            buttonText = stringResource(R.string.DIALOG_BUTTON_OK_TEXT),
            dialogDismissFunction = { viewModel.onTwoDialogDismiss() }
        )
    }
}