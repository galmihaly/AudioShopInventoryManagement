package com.example.audioshopinventorymanagement.loginscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.jwt.JWT
import com.example.audioshopinventorymanagement.authentication.AuthApiRepository
import com.example.audioshopinventorymanagement.authentication.UserApiRepository
import com.example.audioshopinventorymanagement.authentication.requests.LoginRequest
import com.example.audioshopinventorymanagement.authentication.responses.AuthApiResponse
import com.example.audioshopinventorymanagement.authentication.responses.UserServiceResponse
import com.example.audioshopinventorymanagement.jwttokensdatastore.JwtTokenRepository
import com.example.audioshopinventorymanagement.navigation.AppNavigator
import com.example.audioshopinventorymanagement.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Base64
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val authApiRepository: AuthApiRepository,
    private val userApiRepository: UserApiRepository,
    private val jwtTokenRepository: JwtTokenRepository
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

    private val _viewState = MutableStateFlow(LoginViewState())
    val viewState = _viewState.asStateFlow()

    fun updateUsername(newUsername : String){
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    email = newUsername
                )
            }
        }
    }

    fun updatePassword(newPassword : String){
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    password = newPassword
                )
            }
        }
    }

    fun authenticateLoginUser(currentEmail : String, currentPassword : String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = authApiRepository.authenticateWorker(
                LoginRequest(
                    email = currentEmail,
                    password = currentPassword
                )
            )

            var token = ""

            when (response){
                is AuthApiResponse.Success -> {
                    Log.e("access_token", response.data.accessToken)
                    Log.e("refresh_token", response.data.refreshToken)

                    jwtTokenRepository.saveAccessJwt(response.data.accessToken)
                    jwtTokenRepository.saveRefreshJwt(response.data.refreshToken)

                    token = response.data.accessToken

                    val authenticatedEmailFromToken = JWT(token).subject.toString()
                    if (currentEmail.equals(authenticatedEmailFromToken)) onNavigateToStartScreen()
                }
                is AuthApiResponse.Error -> {
                    Log.e("response.code", response.code.toString())
                    Log.e("response.message", response.message.toString())
                }
                is AuthApiResponse.Exception -> {
                    Log.e("ex message", response.e)
                }
            }

//            val response2 = userApiRepository.getSayHello("Bearer $token")
//
//            when (response2){
//                is UserServiceResponse.Success -> {
//                    Log.e("access_token", response2.data!!.sayHello)
//                }
//                is UserServiceResponse.Error -> {
//                    Log.e("response.code", response2.code.toString())
//                    Log.e("response.message", response2.message.toString())
//                }
//                is UserServiceResponse.Exception -> {
//                    Log.e("ex message", response2.e)
//                }
//            }
        }
    }

    fun onNavigateToStartScreen() {
        appNavigator.tryNavigateTo(Destination.StartScreen.fullRoute)
    }
}