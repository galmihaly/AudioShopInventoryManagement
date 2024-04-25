package com.example.audioshopinventorymanagement.loginscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audioshopinventorymanagement.AllViewComponents
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.ui.theme.CustomFonts
import com.example.audioshopinventorymanagement.ui.theme.Dark_Gray
import com.example.audioshopinventorymanagement.ui.theme.Error_Red
import com.example.audioshopinventorymanagement.ui.theme.Green
import com.example.audioshopinventorymanagement.ui.theme.Light_Gray

object ViewComponents {

    @Composable
    fun LoginText(text: String){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        ){
            Text(
                text = text,
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = CustomFonts.RobotoMono_Bold,
            )
        }
    }

    @Composable
    private fun UsernameInputField(text: String, errorMessage: String){
        Column(
            modifier = Modifier.padding(
                top = 0.dp,
                bottom = 10.dp
            )
        ){
            Text(
                text = errorMessage,
                color = Error_Red,
                fontSize = 12.sp,
                fontFamily = CustomFonts.RobotoMono_Bold,
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = { },
                label = {
                    Text(
                        text = text,
                        color = Green,
                        fontFamily = CustomFonts.RobotoMono_Bold,
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Green,
                    unfocusedBorderColor = Green,
                    focusedLabelColor = Green,
                    cursorColor = Green,
                    focusedTextColor = Green,
                    unfocusedTextColor = Green,
                    unfocusedLabelColor = Color.White
                ),
                singleLine = true
            )

        }
    }

    @Composable
    private fun PasswordInputField(text: String, errorMessage: String){
        Column (
            modifier = Modifier.padding(
                top = 10.dp,
                bottom = 0.dp
            )
        ){
            Text(
                text = errorMessage,
                color = Error_Red,
                fontSize = 12.sp,
                fontFamily = CustomFonts.RobotoMono_Bold,
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = { },
                label = {
                    Text(
                        text = text,
                        color = Green,
                        fontFamily = CustomFonts.RobotoMono_Bold,
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Green,
                    unfocusedBorderColor = Green,
                    focusedLabelColor = Green,
                    cursorColor = Green,
                    focusedTextColor = Green,
                    unfocusedTextColor = Green,
                    unfocusedLabelColor = Color.White
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true
            )
        }
    }

    @ExperimentalMaterial3Api
    @Composable
    fun LoginInputFields(usernameText: String, passwordText: String, usernameError: String, passwordError: String){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ){
            Column(
                modifier = Modifier.padding(
                    vertical = 0.dp
                )
            ) {
                UsernameInputField(
                    text = usernameText,
                    errorMessage = usernameError
                )
                PasswordInputField(
                    text = passwordText,
                    errorMessage = passwordError
                )
            }
        }
    }

    @ExperimentalMaterial3Api
    @Composable
    fun LoginButtonAndLink(buttonText: String, linkText: String, onClick: () -> Unit){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onClick,
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Green
                    )
                ) {
                    Text(
                        text = buttonText,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = CustomFonts.RobotoMono_Regular,
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = linkText,
                    color = Color.White,
                    fontSize = 15.sp,
                    fontFamily = CustomFonts.RobotoMono_Regular,
                )
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true, device = Devices.PIXEL_2)
//@Composable
//fun previewComponent(){
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Light_Gray),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        AllViewComponents.HeadLineWithTextAndLogo(
//            headLineText = "Inventory Management",
//            headLineLogo = R.drawable.audioshop_logo
//        )
//        ViewComponents.LoginText("Login")
//        ViewComponents.LoginInputFields(
//            usernameText = "Username",
//            usernameError = "Error Message",
//            passwordText = "Password",
//            passwordError = "Error Message"
//        )
//        ViewComponents.LoginButtonAndLink(
//            buttonText = "Login",
//            linkText = "Device Registration",
//            onClick = {}
//        )
//    }
//}