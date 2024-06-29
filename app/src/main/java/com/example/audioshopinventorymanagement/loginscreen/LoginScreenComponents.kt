package com.example.audioshopinventorymanagement.loginscreen

import androidx.compose.foundation.background
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.audioshopinventorymanagement.AllViewComponents
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.ui.theme.CustomFonts
import com.example.audioshopinventorymanagement.ui.theme.DARK_GRAY
import com.example.audioshopinventorymanagement.ui.theme.ERROR_RED
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import com.example.audioshopinventorymanagement.ui.theme.LIGHT_GRAY

object LoginScreenComponents {

    @Composable
    fun PageText(text: String){
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
        labelText: String,
        emailErrorText: String,
        emailErrorTextColor: Color,
        emailTextValue: String,
        textChangeFunction: (String) -> Unit
    ){
        Column{
            Text(
                text = emailErrorText,
                color = emailErrorTextColor,
                fontSize = 12.sp,
                fontFamily = CustomFonts.RobotoMono_Regular
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                value = emailTextValue,
                onValueChange = textChangeFunction,
                label = {
                    Text(
                        text = labelText,
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
        passwordErrorText: String,
        passwordErrorTextColor: Color,
        passwordTextValue: String,
        textChangeFunction: (String) -> Unit
    ){


        Column (
            modifier = Modifier.padding(
                top = 10.dp,
                bottom = 0.dp
            )
        ){
            Text(
                text = passwordErrorText,
                color = passwordErrorTextColor,
                fontSize = 12.sp,
                fontFamily = CustomFonts.RobotoMono_Regular
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                value = passwordTextValue,
                onValueChange = textChangeFunction,
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
        onClickFunction: () -> Unit
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onClickFunction ,
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
            }
        }
    }

    @Composable
    fun ErrorDialog(
        isShowErrorDialog: Boolean,
        dialogText: String,
        buttonText: String,
        dialogDismissFunction: () -> Unit,
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
                                    .padding(vertical = 20.dp, horizontal = 30.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Button(
                                    onClick = dialogDismissFunction,
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = ERROR_RED
                                    ),
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(40.dp)
                                ) {
                                    Text(
                                        text = buttonText,
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
fun PreviewComponent(){
    Scaffold (
        topBar = {
            AllViewComponents.HeadLineWithTextAndLogo(
                headLineText = stringResource(R.string.LOGIN_TOPBAR_HEADLINE_TEXT),
                headLineLogo = R.drawable.audioshop_logo
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LIGHT_GRAY)
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginScreenComponents.PageText(text = stringResource(R.string.LOGIN_TEXT))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ){
                Column(
                    modifier = Modifier.padding(
                        vertical = 0.dp
                    )
                ) {
                    LoginScreenComponents.EmailInputField(
                        labelText = stringResource(R.string.LOGIN_EMAIL_TEXT),
                        emailErrorText = "EmailStateText",
                        emailErrorTextColor = ERROR_RED,
                        emailTextValue = "emailTextValue",
                        textChangeFunction = { }
                    )
                    LoginScreenComponents.PasswordInputField(
                        text = stringResource(R.string.LOGIN_PASSWORD_TEXT),
                        passwordErrorText = "PasswordStateText",
                        passwordErrorTextColor = ERROR_RED,
                        passwordTextValue = "passwordTextValue",
                        textChangeFunction = { }
                    )
                }
            }

            LoginScreenComponents.LoginButtonAndLink(
                buttonText = stringResource(R.string.LOGIN_TEXT),
                onClickFunction = {}
            )
        }

        /*ViewComponents.BackSaveDialog(viewModel = loginScreenViewModel)*/
    }
}