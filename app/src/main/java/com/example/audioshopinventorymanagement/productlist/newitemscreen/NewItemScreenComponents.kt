package com.example.audioshopinventorymanagement.productlist.newitemscreen

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
        var colorBorderTextField by remember { mutableStateOf(GREEN) }
        var textColorState by remember { mutableStateOf(Color.White) }

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

    @Composable
    fun TwoModifyTextField(
        textField1: String,
        textFieldValue1 : String,
        textChangeFunction1 : (String) -> Unit,
        keyboardType1: KeyboardType,
        isEnabled1: Boolean,
        colorBorderState1: Color,
        colorTextState1: Color,
        textField2: String,
        textFieldValue2 : String,
        textChangeFunction2 : (String) -> Unit,
        keyboardType2: KeyboardType,
        isEnabled2: Boolean,
        colorBorderState2: Color,
        colorTextState2: Color,
    ){
        Row (horizontalArrangement = Arrangement.SpaceBetween)
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.0f)
                    .padding(end = 5.dp)
                    .wrapContentHeight()
            ){
                ModifyTextField(
                    text = textField1,
                    textFieldValue = textFieldValue1,
                    textChangeFunction = textChangeFunction1,
                    keyboardType = keyboardType1,
                    isEnabled = isEnabled1,
                    colorBorderState = colorBorderState1,
                    colorTextState = colorTextState1
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.0f)
                    .padding(start = 5.dp)
                    .wrapContentHeight()
            ){
                ModifyTextField(
                    text = textField2,
                    textFieldValue = textFieldValue2,
                    textChangeFunction = textChangeFunction2,
                    keyboardType = keyboardType2,
                    isEnabled = isEnabled2,
                    colorBorderState = colorBorderState2,
                    colorTextState = colorTextState2
                )
            }
        }
    }

    @Composable
    fun TwoModifyDropDownMenu(
        text1: String,
        dropdownList1 : List<String>,
        currentText1 : String,
        expandedDropDown1 : Boolean,
        expandedFunction1 : (Boolean) -> Unit,
        currentFunction1 : (String) -> Unit,

        text2: String,
        dropdownList2 : List<String>,
        currentText2 : String,
        expandedDropDown2 : Boolean,
        expandedFunction2 : (Boolean) -> Unit,
        currentFunction2 : (String) -> Unit,
    ){
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
                    ModifyDropDownMenu(
                        text = text1,
                        dropdownList = dropdownList1,
                        currentText = currentText1,
                        expandedDropDown = expandedDropDown1,
                        expandedFunction = expandedFunction1,
                        currentFunction = currentFunction1,
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
                    ModifyDropDownMenu(
                        text = text2,
                        dropdownList = dropdownList2,
                        currentText = currentText2,
                        expandedDropDown = expandedDropDown2,
                        expandedFunction = expandedFunction2,
                        currentFunction = currentFunction2,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_2)
@Composable
fun previewComponent(){
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
                            text = "Warehouse Identifier:",
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
                            text = "Stock Identifier:",
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
                                text = "Brand:",
                                dropdownList = listOf("Sennheiser HD 560s", "Bill Payment", "Recharges", "Outing", "Other"),
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
                                text = "Category:",
                                dropdownList = listOf("Sennheiser HD 560s", "Bill Payment", "Recharges", "Outing", "Other"),
                                currentText = "",
                                expandedDropDown = false,
                                expandedFunction = { },
                                currentFunction = { }
                            )
                        }
                    }
                }
                NewItemScreenComponents.ModifyDropDownMenu(
                    text = "Model:",
                    dropdownList = listOf("Sennheiser HD 560s", "Bill Payment", "Recharges", "Outing", "Other"),
                    currentText = "",
                    expandedDropDown = false,
                    expandedFunction = { },
                    currentFunction = { }
                )
                NewItemScreenComponents.ModifyTextField(
                    text = "Barcode:",
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
                            text = "Base Price:",
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
                            text = "WholeSale Price:",
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
