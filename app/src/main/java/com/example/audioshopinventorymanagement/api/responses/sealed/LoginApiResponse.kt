package com.example.audioshopinventorymanagement.api.responses.sealed

import com.example.audioshopinventorymanagement.api.responses.LoginAuthResponse

sealed class LoginApiResponse() {
    class Success(val data: LoginAuthResponse) : LoginApiResponse()
    class Error(val data: LoginAuthResponse) : LoginApiResponse()
    class Exception(val exceptionMessage: String) : LoginApiResponse()
}
