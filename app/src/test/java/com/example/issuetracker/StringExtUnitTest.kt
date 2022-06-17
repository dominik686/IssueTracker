package com.example.issuetracker

import com.example.issuetracker.common.extensions.isValidEmail
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
    fun check_Email_Is_Not_Correct() {
        val email = "dddsad@windowslive"

        assertFalse(email.isValidEmail())
    }
    @Test
    fun check_Email_Is_Not_Correct2() {
        val email = "dddsad@"

        assertFalse(email.isValidEmail())
    }
    @Test
    fun check_Email_Is_Not_Correct3() {
        val email = "dddsad"

        assertFalse(email.isValidEmail())
    }
}