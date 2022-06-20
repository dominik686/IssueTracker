package com.example.issuetracker.composables

import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Label
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.issuetracker.MainActivity
import com.example.issuetracker.R
import com.example.issuetracker.common.composables.Banner
import com.example.issuetracker.common.composables.BasicField
import com.example.issuetracker.ui.theme.IssueTrackerTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@RunWith(AndroidJUnit4::class)
class TextFieldComposables {

    @get:Rule
val composeTestRule = createAndroidComposeRule<MainActivity>()
    val MY_TEXTFIELD_TAG = "MyTextFieldTag"

    @Test
    fun basicField_exists()
    {
        val text = composeTestRule.activity.getText(R.string.app_name).toString()

        composeTestRule.activity.setContent{
            IssueTrackerTheme() {
                BasicField(placeholderText = R.string.email_error, imageVector = Icons.Default.Label , text =text , onNewValue = {},)
            }
        }

        composeTestRule.onNodeWithText(text = text).assertExists()
    }

    @Test
    fun basicField_OnNewValueMethod_Updates_Correctly()
    {
        var startingText = ""
        var finalText = "dddasd"
        composeTestRule.activity.setContent{
            IssueTrackerTheme() {
                BasicField(placeholderText = R.string.email_error, imageVector = Icons.Default.Label ,
                    text =startingText , onNewValue = { startingText += it },
                    modifier = Modifier.testTag(MY_TEXTFIELD_TAG))
            }
        }


        composeTestRule.onNodeWithTag(MY_TEXTFIELD_TAG).performTextInput("dddasd")
        assert(startingText == finalText)
    }

    @Test
    fun basicField_textField_Updates_OnInput()
    {
        var finalText = "dddasd"
        composeTestRule.activity.setContent{
            var startingText =  remember{ mutableStateOf("")}
            IssueTrackerTheme() {
                BasicField(placeholderText = R.string.email_error, imageVector = Icons.Default.Label ,
                    text =startingText.value , onNewValue = { startingText.value = it },
                    modifier = Modifier.testTag(MY_TEXTFIELD_TAG))
            }
        }


        composeTestRule.onNodeWithTag(MY_TEXTFIELD_TAG).performTextInput(finalText)
        composeTestRule.onNodeWithTag(MY_TEXTFIELD_TAG).assertTextEquals(finalText)
    }
}