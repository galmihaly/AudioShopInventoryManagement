package com.example.audioshopinventorymanagement.modifyitemscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.audioshopinventorymanagement.AllViewComponents
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.newitemscreen.NewItemScreenComponents
import com.example.audioshopinventorymanagement.ui.theme.BLUE
import com.example.audioshopinventorymanagement.ui.theme.CustomFonts
import com.example.audioshopinventorymanagement.ui.theme.DARK_GRAY
import com.example.audioshopinventorymanagement.ui.theme.ERROR_RED
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import com.example.audioshopinventorymanagement.ui.theme.LIGHT_GRAY

object ModifyItemScreenComponents {
    @Composable
    fun ErrorDialog(
        isShowErrorDialog: Boolean,
        dialogText: String,
        readyButtonText: String,
        cancelButtonText: String,
        dialogDismissFunction: () -> Unit,
        navigateFunction: () -> Unit
    ) {
        if(isShowErrorDialog){
            Dialog(
                onDismissRequest = dialogDismissFunction
            ) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = DARK_GRAY,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 10.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .border(
                                width = 0.8.dp,
                                color = ERROR_RED,
                                shape= RoundedCornerShape(16.dp)
                            )
                    ){
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = dialogText,
                                    fontSize = 18.sp,
                                    color = ERROR_RED,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 20.dp, horizontal = 40.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Button(
                                    onClick = navigateFunction,
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = BLUE
                                    ),
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(40.dp)
                                ) {
                                    Text(
                                        text = readyButtonText,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                        fontFamily = CustomFonts.RobotoMono_Bold
                                    )
                                }
                                Button(
                                    onClick = dialogDismissFunction,
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = BLUE
                                    ),
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(40.dp)
                                ) {
                                    Text(
                                        text = cancelButtonText,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                        fontFamily = CustomFonts.RobotoMono_Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, device = Devices.PIXEL_2)
@Composable
fun previewComponent(){
    Scaffold (
        topBar = {
            AllViewComponents.HeadLineWithText(
                headLineText = "Modify Product",
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
                        onClick = {  }
                    )
                    AllViewComponents.NavigationButtons(
                        buttonLogoId = R.drawable.save_logo,
                        buttonLogoHeight = 40.dp,
                        buttonLogoWidth = 40.dp,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        backgroundColor = BLUE,
                        onClick = {  }
                    )
                    AllViewComponents.NavigationButtons(
                        buttonLogoId = R.drawable.delete_x_logo,
                        buttonLogoHeight = 40.dp,
                        buttonLogoWidth = 40.dp,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        backgroundColor = ERROR_RED,
                        onClick = { }
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
                            text = "Warehouse Identifier:",
                            textFieldValue = "DE01",
                            textChangeFunction = {  },
                            keyboardType = KeyboardType.Text,
                            isEnabled = true,
                            colorBorderState = GREEN,
                            colorTextState = GREEN
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
                            textFieldValue = "01",
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
                                dropdownList = listOf(),
                                currentText = "",
                                expandedDropDown = true,
                                expandedFunction = {  },
                                currentFunction = {  },
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
                                dropdownList = listOf(),
                                currentText = "",
                                expandedDropDown = true,
                                expandedFunction = {  },
                                currentFunction = {  }
                            )
                        }
                    }
                }
                NewItemScreenComponents.ModifyDropDownMenu(
                    text = "Model:",
                    dropdownList = listOf(),
                    currentText = "",
                    expandedDropDown = true,
                    expandedFunction = {  },
                    currentFunction = {  }
                )
                NewItemScreenComponents.ModifyTextField(
                    text = "Barcode:",
                    textFieldValue = "123456789",
                    textChangeFunction = {  },
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
                            textFieldValue = "58620",
                            textChangeFunction = {  },
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
                            textFieldValue = "73990",
                            textChangeFunction = {  },
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
