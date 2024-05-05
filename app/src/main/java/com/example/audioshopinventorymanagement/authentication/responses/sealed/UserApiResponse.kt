package com.example.audioshopinventorymanagement.authentication.responses.sealed

import com.example.audioshopinventorymanagement.authentication.responses.UserLoginResponse

sealed class UserApiResponse() {
    class Success(val data: UserLoginResponse) : UserApiResponse()
    class Error(val data: UserLoginResponse) : UserApiResponse()
    class Exception(val e: String) : UserApiResponse()
}