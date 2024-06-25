package com.example.audioshopinventorymanagement.utils

import java.util.regex.Pattern

class Validator {
    companion object{

        //Password Validation Messages
        private const val ERROR_LENGTH = "This must have at least 8 characters!"
        private const val ERROR_WHITESPACE = "This must not contain whitespace!"
        private const val ERROR_DIGIT = "This must contain at least one digit!"
        private const val ERROR_UPPER = "This must have at least one uppercase letter!"
        private const val ERROR_SPECIAL = "This must have at least one special character!"
        private const val CORRECT_PASSWORD = "Correct password!"
        private const val EMPTY_PASSWORD = "Password field is empty!"

        //Email Validation Messages
        private const val CORRECT_EMAIL = "Correct email!"
        private const val INCORRECT_EMAIL = "Incorrect email!"
        private const val EMPTY_EMAIL = "Email field is empty!"
        private fun String.isLongerThan8() = length >= 8
        private fun String.isWithoutWhitespace() = none { it.isWhitespace() }
        private fun String.isHasDigit() = any { it.isDigit() }
        private fun String.isHasUppercase() = any { it.isUpperCase() }
        private fun String.isHasSpecialChar() = any { !it.isLetterOrDigit() }

        fun isValidPassword(password: String) : ValidationResult {
            val validationResult = ValidationResult()

            if(password.isEmpty()) {
                validationResult.isValid = false
                validationResult.validationMessage = EMPTY_PASSWORD
            }
            else {
                if(!password.isLongerThan8()) {
                    validationResult.isValid = false
                    validationResult.validationMessage = ERROR_LENGTH
                }
                else if(!password.isWithoutWhitespace()) {
                    validationResult.isValid = false
                    validationResult.validationMessage = ERROR_WHITESPACE
                }
                else if(!password.isHasDigit()) {
                    validationResult.isValid = false
                    validationResult.validationMessage = ERROR_DIGIT
                }
                else if(!password.isHasUppercase()) {
                    validationResult.isValid = false
                    validationResult.validationMessage = ERROR_UPPER
                }
                else if(!password.isHasSpecialChar()) {
                    validationResult.isValid = false
                    validationResult.validationMessage = ERROR_SPECIAL
                }
                else{
                    validationResult.isValid = true
                    validationResult.validationMessage = CORRECT_PASSWORD
                }
            }

            return validationResult
        }

        private val  EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )

        fun isValidEmail(email: String) : ValidationResult {
            val validationResult = ValidationResult()

            if(email.isEmpty()){
                validationResult.isValid = false
                validationResult.validationMessage = EMPTY_EMAIL
            }
            else{
                if(EMAIL_ADDRESS_PATTERN.matcher(email).matches()){
                    validationResult.isValid = true
                    validationResult.validationMessage = CORRECT_EMAIL
                }
                else{
                    validationResult.isValid = false
                    validationResult.validationMessage = INCORRECT_EMAIL
                }
            }

            return validationResult;
        }
    }
}