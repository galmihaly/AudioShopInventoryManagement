package com.example.audioshopinventorymanagement.loginscreen

import androidx.compose.ui.graphics.Color

data class LoginViewState(
    val email: String = "",
    val validationEmailText : String = "",
    val validationEmailColor : Color = Color.Transparent,

    val password: String = "",
    val validationPasswordText : String = "",
    val validationPasswordColor : Color = Color.Transparent,

    val isShowErrorDialog : Boolean = false,
    val textShowErrorDialog : String = ""
)
