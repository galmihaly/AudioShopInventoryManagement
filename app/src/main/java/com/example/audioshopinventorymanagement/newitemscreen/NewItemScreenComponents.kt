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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomAppBar
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
import com.example.audioshopinventorymanagement.ui.theme.Error_Red
import com.example.audioshopinventorymanagement.ui.theme.Green
import com.example.audioshopinventorymanagement.ui.theme.Light_Gray

object NewItemScreenComponents {

    @Composable
    fun ModifyTextField(text: String, textFieldValue: String){

        var textFieldState by remember { mutableStateOf(textFieldValue) }

        Column(
            modifier = Modifier.padding(
                top = 20.dp,
                start = 10.dp,
                end = 10.dp
            )
        ){
            Text(
                text = text,
                color = Color.White,
                fontSize = 12.sp,
                fontFamily = CustomFonts.RobotoMono_Regular,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = textFieldState,
                onValueChange = { textFieldState = it },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Green,
                    unfocusedBorderColor = Green,
                    focusedLabelColor = Green,
                    cursorColor = Green,
                    focusedTextColor = Green,
                    unfocusedTextColor = Green,
                    unfocusedLabelColor = Color.White
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                )
            )
        }
    }
}

//@Preview(showBackground = true, device = Devices.PIXEL_2)
//@Composable
//fun previewComponent(){
//    Scaffold (
//        topBar = {
//            AllViewComponents.HeadLineWithText(
//                headLineText = "New Item",
//            )
//        },
//        bottomBar = {
//            BottomAppBar(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(60.dp),
//                contentPadding = PaddingValues(0.dp),
//                containerColor = Light_Gray
//            ) {
//                Row (
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .wrapContentHeight(),
//                    horizontalArrangement = Arrangement.Absolute.Center
//                ) {
//                    AllViewComponents.NavigationButtons(
//                        buttonLogoId = R.drawable.back_logo,
//                        buttonLogoHeight = 40.dp,
//                        buttonLogoWidth = 40.dp,
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .weight(1f),
//                        backgroundColor = Green,
//                        onClick = {}
//                    )
//                    AllViewComponents.NavigationButtons(
//                        buttonLogoId = R.drawable.add_list_logo,
//                        buttonLogoHeight = 40.dp,
//                        buttonLogoWidth = 40.dp,
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .weight(1f),
//                        backgroundColor = Blue,
//                        onClick = {}
//                    )
//                    AllViewComponents.NavigationButtons(
//                        buttonLogoId = R.drawable.delete_x_logo,
//                        buttonLogoHeight = 40.dp,
//                        buttonLogoWidth = 40.dp,
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .weight(1f),
//                        backgroundColor = Error_Red,
//                        onClick = {}
//                    )
//                }
//            }
//        }
//    ) { paddingValues ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Light_Gray)
//                .padding(paddingValues)
//        ){
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(horizontal = 10.dp),
//            ) {
//                NewItemScreenComponents.ModifyTextField(text = "Barcode:", "")
//                NewItemScreenComponents.ModifyTextField(text = "Brand:", "")
//                NewItemScreenComponents.ModifyTextField(text = "Model:", "")
//            }
//        }
//    }
//}