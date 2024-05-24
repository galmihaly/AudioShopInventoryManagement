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
import com.example.audioshopinventorymanagement.ui.theme.Blue
import com.example.audioshopinventorymanagement.ui.theme.CustomFonts
import com.example.audioshopinventorymanagement.ui.theme.DARK_GRAY
import com.example.audioshopinventorymanagement.ui.theme.DISABLED_GRAY
import com.example.audioshopinventorymanagement.ui.theme.ERROR_RED
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import com.example.audioshopinventorymanagement.ui.theme.LIGHT_GRAY

object NewItemScreenComponents {

    @Composable
    fun ModifyTextField(
        text: String,
        textFieldValue: String,
        keyboardType: KeyboardType,
        /*colorBorder : Color,
        textColor : Color,
        isEnabled : Boolean*/
    ){

        var textFieldState by remember { mutableStateOf(textFieldValue) }

        var colorBorderTextField by remember { mutableStateOf(GREEN) }
        var textColorState by remember { mutableStateOf(Color.White) }
        //val isEnabledTextField by remember { mutableStateOf(false) }

        colorBorderTextField = GREEN
        textColorState = GREEN

        /*if(isEnabledTextField) {
            colorBorderTextField = GREEN
            textColorState = GREEN
        } else {
            colorBorderTextField = DISABLED_GRAY
            textColorState = DISABLED_GRAY
        }*/

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
                value = textFieldState,
                /*enabled = isEnabledTextField,*/
                onValueChange = { textFieldState = it },
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
                    unfocusedLabelColor = colorBorderTextField
                ),
                textStyle = LocalTextStyle.current.copy(
                    color = textColorState,
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
        text1: String,
        optionsList1: List<String>,
        currentSelected1: String,
        dropDownIsExpand1: Boolean,
        /*colorBorder : Color,
        textColor : Color,
        isEnabled : Boolean*/
    ){

        var isExpanded by remember { mutableStateOf(dropDownIsExpand1) }
        var currentSelectedText by remember { mutableStateOf(currentSelected1) }

        var colorBorderTextField by remember { mutableStateOf(GREEN) }
        var textColorState by remember { mutableStateOf(Color.White) }
        /*val isEnabledTextField by remember { mutableStateOf(false) }*/

        colorBorderTextField = GREEN
        textColorState = GREEN

        /*if(isEnabledTextField) {
            colorBorderTextField = GREEN
            textColorState = GREEN
        } else {
            colorBorderTextField = DISABLED_GRAY
            textColorState = DISABLED_GRAY
            isExpanded = false
        }*/

        Column {
            Text(
                text = text1,
                color = Color.White,
                fontSize = 12.sp,
                fontFamily = CustomFonts.RobotoMono_Regular,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            ExposedDropdownMenuBox(
                modifier = Modifier.fillMaxWidth(),
                expanded = isExpanded,
                onExpandedChange = {
                    isExpanded = !isExpanded
                }
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    value = currentSelectedText,
                    onValueChange = {},
                    /*enabled = isEnabledTextField,*/
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = isExpanded
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
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false }
                ) {
                    optionsList1.forEach { selectionOption ->
                        DropdownMenuItem(
                            modifier = Modifier.fillMaxWidth(),
                            text = {
                                Text(
                                    text = selectionOption,
                                    color = Color.White,
                                    fontFamily = CustomFonts.RobotoMono_Regular
                                )
                            },
                            onClick = {
                                currentSelectedText = selectionOption
                                isExpanded = false
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
        keyboardType1: KeyboardType,
        /*colorBorder1 : Color,
        textColor1 : Color,
        isEnabled1 : Boolean,*/
        textField2: String,
        textFieldValue2 : String,
        keyboardType2: KeyboardType,
        /*colorBorder2 : Color,
        textColor2 : Color,
        isEnabled2 : Boolean*/
    ){
        var textFieldState1 by remember { mutableStateOf(textFieldValue1) }
        var textFieldState2 by remember { mutableStateOf(textFieldValue2) }

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
                    keyboardType = keyboardType1,
                    /*colorBorder = colorBorder1,
                    textColor = textColor1,
                    isEnabled = isEnabled1*/
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
                    keyboardType = keyboardType2,
                    /*colorBorder = colorBorder2,
                    textColor = textColor2,
                    isEnabled = isEnabled2*/
                )
            }

        }
    }

    @Composable
    fun TwoModifyDropDownMenu(
        text1: String,
        optionsList1: List<String>,
        currentSelected1: String,
        dropDownIsExpand1: Boolean,
        /*colorBorder1 : Color,
        textColor1 : Color,
        isEnabled1 : Boolean,*/
        text2: String,
        optionsList2: List<String>,
        currentSelected2: String,
        dropDownIsExpand2: Boolean,
        /*colorBorder2 : Color,
        textColor2 : Color,
        isEnabled2 : Boolean*/
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
                        text1 = text1,
                        optionsList1 = optionsList1,
                        currentSelected1 = currentSelected1,
                        dropDownIsExpand1 = dropDownIsExpand1,
                        /*colorBorder = colorBorder1,
                        textColor = textColor1,
                        isEnabled = isEnabled1*/
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
                        text1 = text2,
                        optionsList1 = optionsList2,
                        currentSelected1 = currentSelected2,
                        dropDownIsExpand1 = dropDownIsExpand2,
                        /*colorBorder = colorBorder2,
                        textColor = textColor2,
                        isEnabled = isEnabled2*/
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
                    isEnabled1 = false,*/
                    textField2 = "Stock Identifier:",
                    textFieldValue2 = "",
                    keyboardType2 = KeyboardType.Text,
                    /*colorBorder2 = GREEN,
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
