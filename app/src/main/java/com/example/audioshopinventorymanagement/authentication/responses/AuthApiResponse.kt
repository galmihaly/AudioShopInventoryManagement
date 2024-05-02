package com.example.audioshopinventorymanagement.authentication.responses

sealed class AuthApiResponse() {
    class Success(val data: LoginResponse) : AuthApiResponse()
    class Error(val code: Int, val message: String?) : AuthApiResponse()
    class Exception(val e: String) : AuthApiResponse()
}