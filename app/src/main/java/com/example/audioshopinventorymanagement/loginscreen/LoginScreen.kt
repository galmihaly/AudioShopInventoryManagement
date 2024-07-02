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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
    val context = LocalContext.current

    val emailTextId = loginScreenViewModel.viewState.collectAsState().value.validationEmailTextId
    val emailErrorTextColor = loginScreenViewModel.viewState.collectAsState().value.validationEmailColor
    val emailTextValue = loginScreenViewModel.viewState.collectAsState().value.email

    val passwordTextId = loginScreenViewModel.viewState.collectAsState().value.validationPasswordTextId
    val passwordErrorTextColor = loginScreenViewModel.viewState.collectAsState().value.validationPasswordColor
    val passwordTextValue = loginScreenViewModel.viewState.collectAsState().value.password

    val isShowErrorDialog = loginScreenViewModel.viewState.collectAsState().value.isShowErrorDialog
    val dialogTextId = loginScreenViewModel.viewState.collectAsState().value.textShowErrorDialogId

    var emailText = ""
    var passwordText = ""
    var dialogText = ""
    if(emailTextId != -1){
        emailText = context.getString(emailTextId)
    }
    if(passwordTextId != -1){
        passwordText = context.getString(passwordTextId)
    }
    if(dialogTextId != -1){
        dialogText = context.getString(dialogTextId)
    }

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
            LoginScreenComponents.PageText(text = stringResource(R.string.LOGIN_EMAIL_TEXT))
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
                        labelText = stringResource(R.string.LOGIN_EMAIL_TEXT),
                        emailErrorText = emailText,
                        emailErrorTextColor = emailErrorTextColor,
                        emailTextValue = emailTextValue,
                        textChangeFunction = { loginScreenViewModel.updateUsername(it) }
                    )
                    LoginScreenComponents.PasswordInputField(
                        text = stringResource(R.string.LOGIN_PASSWORD_TEXT),
                        passwordErrorText = passwordText,
                        passwordErrorTextColor = passwordErrorTextColor,
                        passwordTextValue = passwordTextValue,
                        textChangeFunction = { loginScreenViewModel.updatePassword(it) }
                    )
                }
            }

            LoginScreenComponents.LoginButtonAndLink(
                buttonText = stringResource(R.string.LOGIN_TEXT),
                onClickFunction = { loginScreenViewModel.authLoginUser(emailTextValue, passwordTextValue) }
            )
        }

        LoginScreenComponents.ErrorDialog(
            isShowErrorDialog = isShowErrorDialog,
            dialogText = dialogText,
            buttonText = stringResource(R.string.DIALOG_BUTTON_OK_TEXT),
            dialogDismissFunction = { loginScreenViewModel.onErrorDialogDismiss() }
        )
    }
}