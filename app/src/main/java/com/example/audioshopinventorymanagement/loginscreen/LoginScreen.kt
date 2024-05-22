package com.example.audioshopinventorymanagement.loginscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.audioshopinventorymanagement.AllViewComponents
import com.example.audioshopinventorymanagement.R
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
        AllViewComponents.HeadLineWithTextAndLogo(
            headLineText = "Inventory Management",
            headLineLogo = R.drawable.audioshop_logo
        )
        ViewComponents.LoginText(text = "Login")
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
                ViewComponents.EmailInputField(
                    text = "Email",
                    viewModel = loginScreenViewModel
                )
                ViewComponents.PasswordInputField(
                    text = "Password",
                    viewModel = loginScreenViewModel
                )
            }
        }

        val currentUsername = loginScreenViewModel.viewState.collectAsState().value.email
        val currentPassword = loginScreenViewModel.viewState.collectAsState().value.password

        ViewComponents.LoginButtonAndLink(
            buttonText = "Login",
            linkText = "Device Registration",
            viewModel = loginScreenViewModel,
            currentEmail = currentUsername,
            currentPassword = currentPassword
        )
    }
    
    ViewComponents.BackSaveDialog(viewModel = loginScreenViewModel)
}