package com.example.audioshopinventorymanagement.utils

import com.example.audioshopinventorymanagement.R
import java.util.regex.Pattern

class Validator {
    companion object{

        //Password Validation Messages
        private var ERROR_LENGTH = R.string.PASSWORD_ERROR_LENGTH
        private val ERROR_WHITESPACE = R.string.PASSWORD_ERROR_WHITESPACE
        private val ERROR_DIGIT = R.string.PASSWORD_ERROR_DIGIT
        private val ERROR_UPPER = R.string.PASSWORD_ERROR_UPPER
        private val ERROR_SPECIAL = R.string.PASSWORD_ERROR_SPECIAL
        private val CORRECT_PASSWORD = R.string.PASSWORD_CORRECT
        private val EMPTY_PASSWORD = R.string.PASSWORD_EMPTY

        //Email Validation Messages
        private val CORRECT_EMAIL = R.string.EMAIL_CORRECT
        private val INCORRECT_EMAIL = R.string.EMAIL_INCORRECT
        private val EMPTY_EMAIL = R.string.EMAIL_EMPTY
        private fun String.isLongerThan8() = length >= 8
        private fun String.isWithoutWhitespace() = none { it.isWhitespace() }
        private fun String.isHasDigit() = any { it.isDigit() }
        private fun String.isHasUppercase() = any { it.isUpperCase() }
        private fun String.isHasSpecialChar() = any { !it.isLetterOrDigit() }

        fun isValidPassword(password: String) : ValidationResult {
            val validationResult = ValidationResult()

            if(password.isEmpty()) {
                validationResult.isValid = false
                validationResult.validationResourceMessageId = EMPTY_PASSWORD
            }
            else {
                if(!password.isLongerThan8()) {
                    validationResult.isValid = false
                    validationResult.validationResourceMessageId = ERROR_LENGTH
                }
                else if(!password.isWithoutWhitespace()) {
                    validationResult.isValid = false
                    validationResult.validationResourceMessageId = ERROR_WHITESPACE
                }
                else if(!password.isHasDigit()) {
                    validationResult.isValid = false
                    validationResult.validationResourceMessageId = ERROR_DIGIT
                }
                else if(!password.isHasUppercase()) {
                    validationResult.isValid = false
                    validationResult.validationResourceMessageId = ERROR_UPPER
                }
                else if(!password.isHasSpecialChar()) {
                    validationResult.isValid = false
                    validationResult.validationResourceMessageId = ERROR_SPECIAL
                }
                else{
                    validationResult.isValid = true
                    validationResult.validationResourceMessageId = CORRECT_PASSWORD
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
                validationResult.validationResourceMessageId = EMPTY_EMAIL
            }
            else{
                if(EMAIL_ADDRESS_PATTERN.matcher(email).matches()){
                    validationResult.isValid = true
                    validationResult.validationResourceMessageId = CORRECT_EMAIL
                }
                else{
                    validationResult.isValid = false
                    validationResult.validationResourceMessageId = INCORRECT_EMAIL
                }
            }

            return validationResult;
        }
    }
}