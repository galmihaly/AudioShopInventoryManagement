package com.example.audioshopinventorymanagement.authentication.responses

sealed class UserServiceResponse() {
    class Success(val data: UserDetailsResponse) : UserServiceResponse()
    class Error(val code: Int, val message: String?) : UserServiceResponse()
    class Exception(val e: String) : UserServiceResponse()
}