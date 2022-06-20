package com.example.issuetracker.composables

import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.issuetracker.MainActivity
import com.example.issuetracker.common.composables.Banner
import com.example.issuetracker.ui.theme.IssueTrackerTheme
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.example.issuetracker.R
import com.example.issuetracker.resources


@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@RunWith(AndroidJUnit4::class)
class BannerComposables {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun is_Text_Displayed_Correctly()
    {
        composeTestRule.activity.setContent{
            IssueTrackerTheme() {
                Banner(text = R.string.app_name)
            }
        }

        val text = composeTestRule.activity.getText(R.string.app_name).toString()
        composeTestRule.onNodeWithText(text = text).assertExists()
    }
    @Test
    fun is_Text_Displayed_Correctly2()
    {

        composeTestRule.activity.setContent{
            IssueTrackerTheme() {
                Banner(text = R.string.email_error)
            }
        }


        val text = composeTestRule.activity.getText(R.string.email_error).toString()
        composeTestRule.onNodeWithText(text = text).assertExists()
    }
}