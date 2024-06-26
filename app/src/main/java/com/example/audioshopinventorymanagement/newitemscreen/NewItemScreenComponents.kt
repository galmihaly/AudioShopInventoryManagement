package com.example.audioshopinventorymanagement.newitemscreen

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audioshopinventorymanagement.AllViewComponents
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.ui.theme.BLUE
import com.example.audioshopinventorymanagement.ui.theme.CustomFonts
import com.example.audioshopinventorymanagement.ui.theme.DARK_GRAY
import com.example.audioshopinventorymanagement.ui.theme.ERROR_RED
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import com.example.audioshopinventorymanagement.ui.theme.LIGHT_GRAY

object NewItemScreenComponents {

    @Composable
    fun ModifyTextField(
        text: String,
        textFieldValue: String,
        textChangeFunction : (String) -> Unit,
        keyboardType: KeyboardType,
        isEnabled: Boolean,
        colorBorderState: Color,
        colorTextState: Color
    ){
        Column{
            Text(
                text = text,
                color = Color.White,
                fontSize = 12.sp,
                fontFamily = CustomFonts.RobotoMono_Regular,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = textFieldValue,
                enabled = isEnabled,
                onValueChange = textChangeFunction ,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorBorderState,
                    unfocusedBorderColor = colorBorderState,
                    focusedLabelColor = colorBorderState,
                    cursorColor = colorBorderState,
                    focusedTextColor = colorBorderState,
                    unfocusedTextColor = colorBorderState,
                    disabledBorderColor = colorBorderState,
                    disabledLabelColor = colorBorderState,
                    disabledTextColor = colorBorderState,
                    unfocusedLabelColor = colorBorderState
                ),
                textStyle = LocalTextStyle.current.copy(
                    color = colorTextState,
                    fontFamily = CustomFonts.RobotoMono_Regular
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType
                )
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ModifyDropDownMenu(
        text: String,
        dropdownList : List<String>,
        currentText : String,
        expandedDropDown : Boolean,
        expandedFunction : (Boolean) -> Unit,
        currentFunction : (String) -> Unit,
    ){
        val colorBorderTextField by remember { mutableStateOf(GREEN) }
        val textColorState by remember { mutableStateOf(Color.White) }

        Column {
            Text(
                text = text,
                color = Color.White,
                fontSize = 12.sp,
                fontFamily = CustomFonts.RobotoMono_Regular,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            ExposedDropdownMenuBox(
                modifier = Modifier.fillMaxWidth(),
                expanded = expandedDropDown,
                onExpandedChange = { expandedFunction(!expandedDropDown) }
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    value = currentText,
                    onValueChange = {},
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expandedDropDown
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colorBorderTextField,
                        unfocusedBorderColor = colorBorderTextField,
                        focusedLabelColor = colorBorderTextField,
                        cursorColor = colorBorderTextField,
                        focusedTextColor = colorBorderTextField,
                        unfocusedTextColor = colorBorderTextField,
                        disabledBorderColor = colorBorderTextField,
                        disabledLabelColor = colorBorderTextField,
                        disabledTextColor = colorBorderTextField,
                        unfocusedTrailingIconColor = colorBorderTextField,
                        disabledTrailingIconColor = colorBorderTextField,
                        unfocusedLabelColor = colorBorderTextField,
                    ),
                    textStyle = LocalTextStyle.current.copy(
                        color = textColorState,
                        fontFamily = CustomFonts.RobotoMono_Regular
                    ),
                    singleLine = true,
                    readOnly = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    )
                )
                DropdownMenu(
                    modifier = Modifier
                        .exposedDropdownSize()
                        .background(DARK_GRAY),
                    expanded = expandedDropDown,
                    onDismissRequest = { expandedFunction(false)}
                ) {
                    dropdownList.forEach { selectedText ->
                        DropdownMenuItem(
                            modifier = Modifier.fillMaxWidth(),
                            text = {
                                Text(
                                    text = selectedText,
                                    color = Color.White,
                                    fontFamily = CustomFonts.RobotoMono_Regular
                                )
                            },
                            onClick = {
                                currentFunction(selectedText)
                                expandedFunction(false)
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                        HorizontalDivider(
                            thickness = 1.dp,
                            color = GREEN
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_2)
@Composable
fun PreviewComponent(){
    Scaffold (
        topBar = {
            AllViewComponents.HeadLineWithText(
                headLineText = stringResource(R.string.NEWITEM_TOPBAR_HEADLINE_TEXT),
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
                        backgroundColor = BLUE,
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
                            text = stringResource(R.string.NEWITEM_WAREHOUSE_ID_TEXT),
                            textFieldValue = "",
                            textChangeFunction = { },
                            keyboardType = KeyboardType.Text,
                            isEnabled = false,
                            colorBorderState = Color.Gray,
                            colorTextState = Color.Gray
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
                            text = stringResource(R.string.NEWITEM_STORAGE_ID_TEXT),
                            textFieldValue = "",
                            textChangeFunction = { },
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
                                text = stringResource(R.string.NEWITEM_BRAND_TEXT),
                                dropdownList = listOf(
                                    "Sennheiser HD 560s",
                                    "Bill Payment",
                                    "Recharges",
                                    "Outing",
                                    "Other"
                                ),
                                currentText = "",
                                expandedDropDown = false,
                                expandedFunction = { },
                                currentFunction = { }
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
                                text = stringResource(R.string.NEWITEM_CATEGORY_TEXT),
                                dropdownList = listOf(
                                    "Sennheiser HD 560s",
                                    "Bill Payment",
                                    "Recharges",
                                    "Outing",
                                    "Other"
                                ),
                                currentText = "",
                                expandedDropDown = false,
                                expandedFunction = { },
                                currentFunction = { }
                            )
                        }
                    }
                }
                NewItemScreenComponents.ModifyDropDownMenu(
                    text = stringResource(R.string.NEWITEM_MODEL_TEXT),
                    dropdownList = listOf(
                        "Sennheiser HD 560s",
                        "Bill Payment",
                        "Recharges",
                        "Outing",
                        "Other"
                    ),
                    currentText = "",
                    expandedDropDown = false,
                    expandedFunction = { },
                    currentFunction = { }
                )
                NewItemScreenComponents.ModifyTextField(
                    text = stringResource(R.string.NEWITEM_BARCODE_TEXT),
                    textFieldValue = "",
                    textChangeFunction = {},
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
                            text = stringResource(R.string.NEWITEM_BASE_PRICE_TEXT),
                            textFieldValue = "",
                            textChangeFunction = { },
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
                            text = stringResource(R.string.NEWITEM_WHOLESALE_PRICE_TEXT),
                            textFieldValue = "",
                            textChangeFunction = { },
                            keyboardType = KeyboardType.Number,
                            isEnabled = true,
                            colorBorderState = GREEN,
                            colorTextState = Color.White
                        )
                    }
                }
            }
        }
    }
}
