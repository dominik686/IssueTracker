package com.dominikwieczynski.issuetracker.ui.screens.signup

data class SignUpUiState(
                         var username: String = "BigFeno",
                         var email: String = "test@test.com",
                         var password: String = "Testest1\$",
                         var repeatedPassword: String = "Testest1\$") {
}