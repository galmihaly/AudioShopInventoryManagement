package com.example.audioshopinventorymanagement.loginscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.audioshopinventorymanagement.AllViewComponents
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.ui.theme.LIGHT_GRAY

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    loginScreenViewModel: LoginScreenViewModel = hiltViewModel()
) {
    val emailText = loginScreenViewModel.viewState.collectAsState().value.validationEmailText
    val emailErrorTextColor = loginScreenViewModel.viewState.collectAsState().value.validationEmailColor
    val emailTextValue = loginScreenViewModel.viewState.collectAsState().value.email

    val passwordText = loginScreenViewModel.viewState.collectAsState().value.validationPasswordText
    val passwordErrorTextColor = loginScreenViewModel.viewState.collectAsState().value.validationPasswordColor
    val passwordTextValue = loginScreenViewModel.viewState.collectAsState().value.password

    val isShowErrorDialog = loginScreenViewModel.viewState.collectAsState().value.isShowErrorDialog
    val dialogText = loginScreenViewModel.viewState.collectAsState().value.textShowErrorDialog

    Scaffold (
        topBar = {
            AllViewComponents.HeadLineWithTextAndLogo(
                headLineText = "Inventory Management",
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
            LoginScreenComponents.LoginText(text = "Login")
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
                    LoginScreenComponents.EmailInputField(
                        text = "Email",
                        emailErrorText = emailText,
                        emailErrorTextColor = emailErrorTextColor,
                        emailTextValue = emailTextValue,
                        textChangeFunction = { loginScreenViewModel.updateUsername(it) }
                    )
                    LoginScreenComponents.PasswordInputField(
                        text = "Password",
                        passwordErrorText = passwordText,
                        passwordErrorTextColor = passwordErrorTextColor,
                        passwordTextValue = passwordTextValue,
                        textChangeFunction = { loginScreenViewModel.updatePassword(it) }
                    )
                }
            }

            LoginScreenComponents.LoginButtonAndLink(
                buttonText = "Login",
                linkText = "Device Registration",
                onClickFunction = { loginScreenViewModel.authLoginUser(emailTextValue, passwordTextValue) }
            )
        }

        LoginScreenComponents.ErrorDialog(
            isShowErrorDialog = isShowErrorDialog,
            dialogText = dialogText,
            dialogDismissFunction = { loginScreenViewModel.onErrorDialogDismiss() }
        )
    }

    /*Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LIGHT_GRAY),
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

        ViewComponents.LoginButtonAndLink(
            buttonText = "Login",
            linkText = "Device Registration",
            viewModel = loginScreenViewModel
        )
    }
    
    ViewComponents.BackSaveDialog(viewModel = loginScreenViewModel)*/
}