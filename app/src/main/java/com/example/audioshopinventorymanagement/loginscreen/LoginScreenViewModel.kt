package com.example.audioshopinventorymanagement.loginscreen

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.jwt.JWT
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.api.repositories.AuthApiRepository
import com.example.audioshopinventorymanagement.api.requests.LoginAuthRequest
import com.example.audioshopinventorymanagement.api.responses.sealed.LoginApiResponse
import com.example.audioshopinventorymanagement.jwttokensdatastore.JwtTokenRepository
import com.example.audioshopinventorymanagement.navigation.AppNavigator
import com.example.audioshopinventorymanagement.navigation.Destination
import com.example.audioshopinventorymanagement.ui.theme.ERROR_RED
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import com.example.audioshopinventorymanagement.utils.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val authApiRepository: AuthApiRepository,
    private val jwtTokenRepository: JwtTokenRepository
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

    private val _viewState = MutableStateFlow(LoginViewState())
    val viewState = _viewState.asStateFlow()

    private fun isValidInputEmail(email: String) : Boolean{
        val emailValidationResult = Validator.isValidEmail(email)

        if(!emailValidationResult.isValid){
            onErrorEmailTextBox(
                stringResourceId = emailValidationResult.validationResourceMessageId,
                color = ERROR_RED
            )

            return emailValidationResult.isValid
        }

        onErrorEmailTextBox(
            stringResourceId = emailValidationResult.validationResourceMessageId,
            color = GREEN
        )

        return emailValidationResult.isValid
    }

    private fun isValidInputPassword(password: String) : Boolean {
        val passwordValidationResult = Validator.isValidPassword(password)

        if(!passwordValidationResult.isValid){
            onErrorPasswordTextBox(
                stringResourceId = passwordValidationResult.validationResourceMessageId,
                color = ERROR_RED
            )

            return passwordValidationResult.isValid
        }
        onErrorPasswordTextBox(
            stringResourceId = passwordValidationResult.validationResourceMessageId,
            color = GREEN
        )

        return passwordValidationResult.isValid
    }

    private suspend fun authenticateUser(currentEmail : String, currentPassword: String) : String{
        val response = authApiRepository.authenticateUser(
            LoginAuthRequest(
                email = currentEmail,
                password = currentPassword
            )
        )

        var token = ""

        when (response){
            is LoginApiResponse.Success -> {
                if(response.data.statusCode == 200 && response.data.loginUserDetails.isLogin){
                    val isLogin = response.data.loginUserDetails.isLogin
                    if(isLogin){
                        jwtTokenRepository.saveAccessJwt(response.data.loginUserDetails.accessToken)
                        jwtTokenRepository.saveRefreshJwt(response.data.loginUserDetails.refreshToken)

                        token = response.data.loginUserDetails.accessToken
                    }
                    else{
                        onDialogShow(R.string.LOGIN_AUTHENTICATION_FAILED)
                    }
                }
            }
            is LoginApiResponse.Error -> {
                if(response.data.statusCode == 401){
                    onDialogShow(R.string.LOGIN_AUTHENTICATION_FAILED)
                }
                else if(response.data.statusCode == 403){
                    onDialogShow(R.string.LOGIN_AUTHENTICATION_FAILED)
                }
            }
            is LoginApiResponse.Exception -> {
                onDialogShow(response.exceptionMessageId)
            }
        }
        
        return token
    }

    private fun isUsefulTokenPartsCount(token : String) : Boolean {
        val tokenParts = Integer.valueOf(token.split(".").size)
        return tokenParts > 2
    }

    fun authLoginUser(inputEmail : String, inputPassword : String){
        viewModelScope.launch(Dispatchers.IO) {
            if(isValidInputEmail(inputEmail) && isValidInputPassword(inputPassword)){

                val authenticatedToken = authenticateUser(inputEmail, inputPassword)
                if (isUsefulTokenPartsCount(authenticatedToken) && authenticatedToken != ""){

                    val jwtObject = JWT(authenticatedToken)
                    val isExpiredToken = jwtObject.isExpired(0)
                    val isActiveDevice = jwtObject.getClaim("device_active").asString()!!

                    val authenticatedEmailFromToken = jwtObject.getClaim("email").asString()!!
                    val authValidator = Validator.isValidEmail(authenticatedEmailFromToken)

                    if(isActiveDevice == "True"){
                        if(!isExpiredToken && authValidator.isValid){
                            onNavigateToStartScreen()
                        }
                    }
                    else{
                        onDialogShow(R.string.LOGIN_DEVICE_INACTIVE)
                    }
                }
            }
        }
    }

    private fun onNavigateToStartScreen() {
        appNavigator.tryNavigateTo(Destination.StartScreen.fullRoute)
    }

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

    fun onDialogShow(dialogTextId : Int){
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    textShowErrorDialogId = dialogTextId,
                    isShowErrorDialog = true
                )
            }
        }
    }

    fun onErrorDialogDismiss() {
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    textShowErrorDialogId = -1,
                    isShowErrorDialog = false
                )
            }
        }
    }

    private fun onErrorEmailTextBox(stringResourceId : Int, color : Color){
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    validationEmailTextId = stringResourceId,
                    validationEmailColor = color
                )
            }
        }
    }

    private fun onErrorPasswordTextBox(stringResourceId : Int, color : Color){
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    validationPasswordTextId = stringResourceId,
                    validationPasswordColor = color
                )
            }
        }
    }
}