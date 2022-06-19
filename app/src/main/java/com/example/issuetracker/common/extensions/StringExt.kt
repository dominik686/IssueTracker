package com.example.issuetracker.common.extensions

import androidx.core.util.PatternsCompat
import java.util.regex.Pattern

private const val MIN_PASSWORD_LENGTH = 8
private const val PASSWORD_PATTERN = "^(?=.*[0-8])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"

private const val MIN_USERNAME_LENGTH = 8
private const val MAX_USERNAME_LENGTH = 16
private const val USERNAME_PATTERN = "^[a-zA-Z0-9]{8,16}$"
fun String.isValidEmail() : Boolean{
    return this.isNotBlank()
            && PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean{
   return this.isNotBlank()
           && this.length >= MIN_PASSWORD_LENGTH
           && Pattern.compile(
       PASSWORD_PATTERN).matcher(this).matches()
}


fun String.isValidUsername(): Boolean{
    return this.isNotBlank() && this.length >= 8 && this.length <= 16 && Pattern.compile(
        USERNAME_PATTERN).matcher(this).matches()
}