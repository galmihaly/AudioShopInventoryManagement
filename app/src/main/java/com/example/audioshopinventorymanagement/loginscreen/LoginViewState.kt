package com.example.audioshopinventorymanagement.loginscreen

import androidx.compose.ui.graphics.Color

data class LoginViewState(
    val email: String = "",
    val validationEmailTextId : Int = -1,
    val validationEmailColor : Color = Color.Transparent,

    val password: String = "",
    val validationPasswordTextId : Int = -1,
    val validationPasswordColor : Color = Color.Transparent,

    val isShowErrorDialog : Boolean = false,
    val textShowErrorDialogId : Int = -1
)
