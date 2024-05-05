package com.example.audioshopinventorymanagement.authentication.responses.sealed

import com.example.audioshopinventorymanagement.authentication.responses.LoginAuthResponse

sealed class LoginApiResponse() {
    class Success(val data: LoginAuthResponse) : LoginApiResponse()
    class Error(val data: LoginAuthResponse) : LoginApiResponse()
    class Exception(val e: String) : LoginApiResponse()
}
