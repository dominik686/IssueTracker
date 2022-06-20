package com.example.issuetracker.common.extensions

import com.example.issuetracker.common.extensions.isValidEmail
import com.example.issuetracker.common.extensions.isValidPassword
import com.example.issuetracker.common.extensions.isValidUsername
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class StringExtUnitTest {


    @Test
    fun check_Email_Is_Correct() {
        val email = "dddsad@windowslive.com"

        assertTrue(email.isValidEmail())
    } @Test
    fun check_Email_Is_Correct2() {
        val email = "allorify@gmail.com"

        assertTrue(email.isValidEmail())
    }
    @Test
    fun check_Email_Is_Incorrect_IncompleteEmail() {
        val email = "dddsad@windowslive"

        assertFalse(email.isValidEmail())
    }
    @Test
    fun check_Email_Is_Incorrect_IncompleteEmail2() {
        val email = "dddsad@"

        assertFalse(email.isValidEmail())
    }
    @Test
    fun check_Email_Is_Incorrect_IncompleteEmail3() {
        val email = "dddsad"

        assertFalse(email.isValidEmail())
    }


    @Test
    fun check_Username_Is_Correct()
    {
        val username = "username"

        assertTrue(username.isValidUsername())

    }
    @Test
    fun check_Username_Is_Correct2()
    {
        val username = "username45"

        assertTrue(username.isValidUsername())

    }
    @Test
    fun check_Username_Is_Incorrect_TooShort()
    {
        val username = "user"

        assertFalse(username.isValidUsername())

    }
    @Test
    fun check_Username_Is_Incorrect_SpecialCharacters()
    {
        val username = "username$$$"

        assertFalse(username.isValidUsername())

    }
    @Test
    fun check_Username_Is_Incorrect_SpecialCharactersAndTooLong()
    {
        val username = "username$$$$213213"

        assertFalse(username.isValidUsername())

    }
    @Test
    fun check_Username_Is_Incorrect_TooLong()
    {
        val username = "usernameeee44434324234234"

        assertFalse(username.isValidUsername())

    }

    @Test
    fun check_Password_Is_Correct()
    {
        val password = "passworD1$"

        assertTrue(password.isValidPassword())
    }
    //     Your password should have at least eight digits
    //     and include one digit, one lower case letter, one upper case letter and one special character.
    @Test
    fun check_Password_Is_Correct2()
    {
        val password = "passworD1$#"

        assertTrue(password.isValidPassword())
    }
    @Test
    fun check_Password_Is_Incorrect_noUpperCaseLetters()
    {
        val password = "password1$#"

        assertFalse(password.isValidPassword())
    }
    @Test
    fun check_Password_Is_Incorrect_NoUpperCaseLettersAndSpecialCharacters()
    {
        val password = "password1"

        assertFalse(password.isValidPassword())
    }
    @Test
    fun check_Password_Is_Incorrect_TooLong()
    {
        val password = "passworD1dasdasdadsasdasddSD#asdasd"

        assertFalse(password.isValidPassword())
    }
    @Test
    fun check_Password_Is_Incorrect_TooShort()
    {
        val password = "pas"

        assertFalse(password.isValidPassword())
    }
}