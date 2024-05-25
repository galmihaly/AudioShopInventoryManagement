package com.example.audioshopinventorymanagement.loginscreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.audioshopinventorymanagement.ui.theme.CustomFonts
import com.example.audioshopinventorymanagement.ui.theme.DARK_GRAY
import com.example.audioshopinventorymanagement.ui.theme.ERROR_RED
import com.example.audioshopinventorymanagement.ui.theme.GREEN

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
    fun EmailInputField(
        text: String,
        viewModel: LoginScreenViewModel
    ){
        val emailText = viewModel.viewState.collectAsState().value.validationEmailText
        val emailTextColor = viewModel.viewState.collectAsState().value.validationEmailColor
        val emailTextValue = viewModel.viewState.collectAsState().value.email

        Column(
            modifier = Modifier.padding(
                top = 0.dp,
                bottom = 10.dp
            )
        ){
            Text(
                text = emailText,
                color = emailTextColor,
                fontSize = 12.sp,
                fontFamily = CustomFonts.RobotoMono_Regular,
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = emailTextValue,
                onValueChange = { viewModel.updateUsername(it) },
                label = {
                    Text(
                        text = text,
                        color = GREEN,
                        fontFamily = CustomFonts.RobotoMono_Regular,
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = GREEN,
                    unfocusedBorderColor = GREEN,
                    focusedLabelColor = GREEN,
                    cursorColor = GREEN,
                    focusedTextColor = GREEN,
                    unfocusedTextColor = GREEN,
                    unfocusedLabelColor = Color.White
                ),
                singleLine = true
            )
        }
    }

    @Composable
    fun PasswordInputField(
        text: String,
        viewModel : LoginScreenViewModel
    ){
        val passwordText = viewModel.viewState.collectAsState().value.validationPasswordText
        val passwordTextColor = viewModel.viewState.collectAsState().value.validationPasswordColor
        val passwordTextValue = viewModel.viewState.collectAsState().value.password

        Column (
            modifier = Modifier.padding(
                top = 10.dp,
                bottom = 0.dp
            )
        ){
            Text(
                text = passwordText,
                color = passwordTextColor,
                fontSize = 12.sp,
                fontFamily = CustomFonts.RobotoMono_Regular,
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = passwordTextValue,
                onValueChange = { viewModel.updatePassword(it) },
                label = {
                    Text(
                        text = text,
                        color = GREEN,
                        fontFamily = CustomFonts.RobotoMono_Regular,
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = GREEN,
                    unfocusedBorderColor = GREEN,
                    focusedLabelColor = GREEN,
                    cursorColor = GREEN,
                    focusedTextColor = GREEN,
                    unfocusedTextColor = GREEN,
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
    fun LoginButtonAndLink(
        buttonText: String,
        linkText: String,
        viewModel: LoginScreenViewModel
    ){
        val currentEmail = viewModel.viewState.collectAsState().value.email
        val currentPassword = viewModel.viewState.collectAsState().value.password

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { viewModel.authLoginUser(currentEmail, currentPassword) },
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GREEN
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

    @Composable
    fun BackSaveDialog(viewModel: LoginScreenViewModel) {
        val isShowErrorDialog = viewModel.viewState.collectAsState().value.isShowErrorDialog
        val dialogText = viewModel.viewState.collectAsState().value.textShowErrorDialog

        if(isShowErrorDialog){
            Dialog(
                onDismissRequest = { viewModel.onErrorDialogDismiss() }
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
                                    .padding(vertical = 20.dp, horizontal = 30.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Button(
                                    onClick = { viewModel.onErrorDialogDismiss() },
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = ERROR_RED
                                    ),
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(40.dp)
                                ) {
                                    Text(
                                        text = "OK",
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