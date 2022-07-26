package com.dominikwieczynski.issuetracker.composables

import android.annotation.SuppressLint
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dominikwieczynski.issuetracker.MainActivity
import com.dominikwieczynski.issuetracker.R
import com.dominikwieczynski.issuetracker.common.composables.BasicButton
import com.dominikwieczynski.issuetracker.common.composables.BasicFabButton
import com.dominikwieczynski.issuetracker.common.composables.TextButton
import com.dominikwieczynski.issuetracker.theme.IssueTrackerTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@RunWith(AndroidJUnit4::class)
class ButtonComposables {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun basicButton_IsText_Displayed_Correctly()
    {
        composeTestRule.activity.setContent{
            IssueTrackerTheme() {
                BasicButton(text = R.string.app_name, action = {

                })
            }
        }

        val text = composeTestRule.activity.getText(R.string.app_name).toString()
        composeTestRule.onNodeWithText(text = text).assertExists()
    }
    @Test
    fun basicButton_IsPressable(){
        composeTestRule.activity.setContent{
            IssueTrackerTheme() {
                BasicButton(text = R.string.app_name, action = {

                })
            }
        }

        val text = composeTestRule.activity.getText(R.string.app_name).toString()
        composeTestRule.onNodeWithText(text = text).assert(hasClickAction())
    }
    @Test
    fun textButton_IsText_Displayed_Correctly()
    {
        composeTestRule.activity.setContent{
            IssueTrackerTheme() {
                TextButton(text = R.string.app_name, action = {

                })
            }
        }

        val text = composeTestRule.activity.getText(R.string.app_name).toString()
        composeTestRule.onNodeWithText(text = text).assertExists()
    }
    @Test
    fun textButton_IsPressable(){
        composeTestRule.activity.setContent{
            IssueTrackerTheme() {
                TextButton(text = R.string.app_name, action = {

                })
            }
        }

        val text = composeTestRule.activity.getText(R.string.app_name).toString()
        composeTestRule.onNodeWithText(text = text).assert(hasClickAction())
    }
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Test
    fun fabButton_IsText_Displayed_Correctly()
    {
        composeTestRule.activity.setContent{
            IssueTrackerTheme() {
                Scaffold(floatingActionButton = { BasicFabButton(onClick = { /*TODO*/ }) {
                    Text(text = "Text")
                }

                }) {

                }

            }
        }

        composeTestRule.onNodeWithText("Text").assertExists()
    }
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Test
    fun fabButton_textButton_IsPressable(){
        composeTestRule.activity.setContent{
            IssueTrackerTheme() {
                Scaffold(floatingActionButton = { BasicFabButton(onClick = { /*TODO*/ }) {
                Text(text = "Text")
            }

            }) {

            }

        }
        }

        composeTestRule.onNodeWithText(text = "Text").assert(hasClickAction())
    }

}