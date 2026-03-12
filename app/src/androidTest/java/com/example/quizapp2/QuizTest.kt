package com.example.quizapp2

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class QuizTest {


    //some predefined variables
    companion object{

    }


    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun testMainMenuTitle(){
        composeTestRule.onNodeWithText("Welcome to the Quiz App.v2!")
            .assertIsDisplayed()
    }


    @Test
    fun testClickingOnGalleryScreen(){



    }



}