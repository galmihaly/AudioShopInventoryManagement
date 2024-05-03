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

    private fun isValidEmailInputField(email: String) : Boolean{
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

    private fun isValidPasswordInputField(password: String) : Boolean {
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

                // save tokens to DataStore
                jwtTokenRepository.saveAccessJwt(response.data.accessToken)
                jwtTokenRepository.saveRefreshJwt(response.data.refreshToken)

                token = response.data.accessToken
            }
            is AuthApiResponse.Error -> {
                Log.e("response.code", response.code.toString())
                Log.e("response.message", response.message.toString())
            }
            is AuthApiResponse.Exception -> {
                Log.e("ex message", response.e)
            }
        }

        return token
    }

    //Megszámoljuk, hogy tokenünk mennyi részből áll. Egy hivatalos JSON Web Token áll egy fejrészből, egy hasznos adat részből, valamint egy aláíró kulcsból.
    //A 3 rész közül csak egy érkezik meg, ami nem megfelelő token, ezért megszámoljuk, hogy az API-ból megérkezett kulcs mennyi részből áll.
    //Boolean értékkel visszatérve pedig megvizsgáljuk a token részeinek számát.
    private fun isUsefulTokenPartsCount(token : String) : Boolean {
        val tokenParts = Integer.valueOf(token.split(".").size)
        return tokenParts > 2
    }

    private suspend fun getUserDetails(token : String, currentEmail: String) {

        when (val responseDetails = userApiRepository.getUserDetails(token)){
            is UserServiceResponse.Success -> {
                if(currentEmail == token){
                    if(responseDetails.data.userActive && responseDetails.data.deviceActive){
                        onNavigateToStartScreen()
                    }
                    else if(!responseDetails.data.userActive){
                        Log.e("inactive user", "User is inactive!")
                        //errorMessage
                    }
                    else if(!responseDetails.data.deviceActive){
                        //errorMessage
                    }
                }

            }
            is UserServiceResponse.Error -> {
                Log.e("response.code", responseDetails.code.toString())
                Log.e("response.message", responseDetails.message.toString())
            }
            is UserServiceResponse.Exception -> {
                Log.e("ex message", responseDetails.e)
            }
        }
    }

    fun authenticateLoginUser(currentEmail : String, currentPassword : String){
        viewModelScope.launch(Dispatchers.IO) {

            // + password checking, Network Connection checking
            val isValidEmail = isValidEmailInputField(currentEmail)
            val isValidPassword = isValidPasswordInputField(currentPassword)

            if(isValidEmail && isValidPassword){

                val authenticatedToken = authenticateUser(currentEmail, currentPassword)
                if (isUsefulTokenPartsCount(authenticatedToken)){

//                    // Ha lejárt a token, akkor annak ellenőrzése után kijelenteztetés az alkalmazásból, ami annyit
//                    // jelent, hogy visszairányítjuk a bejelentkezés ablakra
//                    // + felugró ablak annak jelzésére, hogy a token lejárt és ezért kijelentkezik
//                    Log.e("expiresAt", jwtObject.expiresAt.toString());

                    val jwtObject = JWT(authenticatedToken)
                    val authenticatedEmailFromToken = jwtObject.subject.toString()
                    val authValidator = Validator.isValidEmail(authenticatedEmailFromToken)

                    if(authValidator.isValid){ getUserDetails(authenticatedEmailFromToken, currentEmail) }
                }
                else{
                    //errorMessage inValid Token From Server

                    // nem megfelelő token esetén nem alakítjuk át JWT tokenné, ez azt jelenti, hogy a felhasználó a saját email címét és jelszavát rosszul adta meg
                    // ezért a szerver nem tudta megfelelően legenerálni a szerveren, vagyis a szerver ezzel az email-el és jelszóval nem találta meg a user-t az adatbázisban
                }
            }
        }
    }

    private fun onNavigateToStartScreen() {
        appNavigator.tryNavigateTo(Destination.StartScreen.fullRoute)
    }
}