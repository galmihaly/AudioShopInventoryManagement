package com.example.audioshopinventorymanagement.loginscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.ViewComponents
import com.example.audioshopinventorymanagement.ViewComponents.HeadLineWithTextAndLogo
import com.example.audioshopinventorymanagement.ViewComponents.LoginInputFields
import com.example.audioshopinventorymanagement.ViewComponents.LoginText
import com.example.audioshopinventorymanagement.ui.theme.Light_Gray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    loginScreenViewModel: LoginScreenViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Light_Gray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ViewComponents.HeadLineWithTextAndLogo(
            headLineText = "Inventory Management",
            headLineLogo = R.drawable.audioshop_logo
        )
        ViewComponents.LoginText(
            text = "Login"
        )
        ViewComponents.LoginInputFields(
            usernameText = "Username",
            usernameError = "Error Message",
            passwordText = "Password",
            passwordError = "Error Message"
        )
        ViewComponents.LoginButtonAndLink(
            buttonText = "Login",
            linkText = "Device Registration"
        )
    }
}