package com.example.audioshopinventorymanagement.loginscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.jwt.JWT
import com.example.audioshopinventorymanagement.authentication.repositories.AuthApiRepository
import com.example.audioshopinventorymanagement.authentication.repositories.UserApiRepository
import com.example.audioshopinventorymanagement.authentication.requests.LoginAuthRequest
import com.example.audioshopinventorymanagement.authentication.responses.sealed.LoginApiResponse
import com.example.audioshopinventorymanagement.authentication.responses.sealed.UserApiResponse
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

    private fun isValidInputEmail(email: String) : Boolean{
        val isValidEmail: Boolean

        val emailValidationResult = Validator.isValidEmail(email)

        if(!emailValidationResult.isValid){
            isValidEmail = false

            viewModelScope.launch {
                _viewState.update {
                    it.copy(
                        validationEmailText = emailValidationResult.validationMessage,
                        validationEmailColor = ERROR_RED
                    )
                }
            }
        }
        else{
            isValidEmail = true

            viewModelScope.launch {
                _viewState.update {
                    it.copy(
                        validationEmailText = emailValidationResult.validationMessage,
                        validationEmailColor = GREEN
                    )
                }
            }
        }

        return isValidEmail
    }

    private fun isValidInputPassword(password: String) : Boolean {
        val isValidPassord: Boolean

        val passwordValidationResult = Validator.isValidPassword(password)

        if(!passwordValidationResult.isValid){
            isValidPassord = false

            viewModelScope.launch {
                _viewState.update {
                    it.copy(
                        validationPasswordText = passwordValidationResult.validationMessage,
                        validationPasswordColor = ERROR_RED
                    )
                }
            }
        }
        else{
            isValidPassord = true

            viewModelScope.launch {
                _viewState.update {
                    it.copy(
                        validationPasswordText = passwordValidationResult.validationMessage,
                        validationPasswordColor = GREEN
                    )
                }
            }
        }

        return isValidPassord
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
                if(response.data.statusCode == 200){
                    Log.e("access_token", response.data.loginAuthTokens.accessToken)
                    Log.e("refresh_token", response.data.loginAuthTokens.refreshToken)

                    // save tokens to DataStore
                    jwtTokenRepository.saveAccessJwt(response.data.loginAuthTokens.accessToken)
                    jwtTokenRepository.saveRefreshJwt(response.data.loginAuthTokens.refreshToken)

                    token = response.data.loginAuthTokens.accessToken
                }
            }
            is LoginApiResponse.Error -> {
                if(response.data.statusCode == 401){
                    viewModelScope.launch {
                        _viewState.update {
                            it.copy(
                                textShowErrorDialog = "Login Error!"
                            )
                        }
                    }
                    onErrorDialogShow()
                    token = ""
                }
                else if(response.data.statusCode == 403){
                    viewModelScope.launch {
                        _viewState.update {
                            it.copy(
                                textShowErrorDialog = "Login Error!"
                            )
                        }
                    }
                    onErrorDialogShow()
                    token = ""
                }
            }
            is LoginApiResponse.Exception -> {
                Log.e("ex message", response.e)
            }
        }

        return token
    }

    private suspend fun getUserDetails(token : String, currentEmail: String) {
        val response = userApiRepository.getUserDetails(token)

        when (response){
            is UserApiResponse.Success -> {
                if(response.data.statusCode == 200){
                    if(response.data.userDetail.userActive && response.data.userDetail.deviceActive){
                        onNavigateToStartScreen()
                    }
                    else if(!response.data.userDetail.userActive){

                        viewModelScope.launch {
                            _viewState.update {
                                it.copy(
                                    textShowErrorDialog = "User is inactive!"
                                )
                            }
                        }
                        onErrorDialogShow()
                    }
                    else if(!response.data.userDetail.deviceActive){

                        viewModelScope.launch {
                            _viewState.update {
                                it.copy(
                                    textShowErrorDialog = "Device is inactive!"
                                )
                            }
                        }
                        onErrorDialogShow()
                    }
                }
            }
            is UserApiResponse.Error -> {
                if(response.data.statusCode == 401){
                    viewModelScope.launch {
                        _viewState.update {
                            it.copy(
                                textShowErrorDialog = "Login Error!"
                            )
                        }
                    }
                    onErrorDialogShow()
                }
                else if(response.data.statusCode == 403){
                    viewModelScope.launch {
                        _viewState.update {
                            it.copy(
                                textShowErrorDialog = "Login Error!"
                            )
                        }
                    }
                    onErrorDialogShow()
                }
            }
            is UserApiResponse.Exception -> {
                Log.e("ex message", response.e)
            }
        }
    }

    //Megszámoljuk, hogy tokenünk mennyi részből áll. Egy hivatalos JSON Web Token áll egy fejrészből, egy hasznos adat részből, valamint egy aláíró kulcsból.
    //A 3 rész közül csak egy érkezik meg, ami nem megfelelő token, ezért megszámoljuk, hogy az API-ból megérkezett kulcs mennyi részből áll.
    //Boolean értékkel visszatérve pedig megvizsgáljuk a token részeinek számát.
    private fun isUsefulTokenPartsCount(token : String) : Boolean {
        val tokenParts = Integer.valueOf(token.split(".").size)
        return tokenParts > 2
    }

    fun authLoginUser(inputEmail : String, inputPassword : String){
        viewModelScope.launch(Dispatchers.IO) {

            // + password checking, Network Connection checking

            if(isValidInputEmail(inputEmail) && isValidInputPassword(inputPassword)){

                val authenticatedToken = authenticateUser(inputEmail, inputPassword)
                if (isUsefulTokenPartsCount(authenticatedToken) && authenticatedToken != ""){

//                    // Ha lejárt a token, akkor annak ellenőrzése után kijelenteztetés az alkalmazásból, ami annyit
//                    // jelent, hogy visszairányítjuk a bejelentkezés ablakra
//                    // + felugró ablak annak jelzésére, hogy a token lejárt és ezért kijelentkezik
//                    Log.e("expiresAt", jwtObject.expiresAt.toString());

                    val jwtObject = JWT(authenticatedToken)
                    val authenticatedEmailFromToken = jwtObject.subject.toString()
                    val authValidator = Validator.isValidEmail(authenticatedEmailFromToken)

                    if(authValidator.isValid){ getUserDetails(authenticatedEmailFromToken, inputEmail) }
                }
            }
        }
    }

    private fun onNavigateToStartScreen() {
        appNavigator.tryNavigateTo(Destination.StartScreen.fullRoute)
    }

    fun onErrorDialogShow(){
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    isShowErrorDialog = true
                )
            }
        }
    }

    fun onErrorDialogDismiss() {
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    textShowErrorDialog = "",
                    isShowErrorDialog = false
                )
            }
        }
    }
}