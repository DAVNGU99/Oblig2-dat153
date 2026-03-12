package com.example.quizapp2

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.ViewModelProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.quizapp2.ViewModel.QuizObjectViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.compose.ui.test.onAllNodesWithTag
@RunWith(AndroidJUnit4::class)
@LargeTest
class QuizTest {


    //some predefined variables

        private lateinit var viewModel: QuizObjectViewModel
        val MAIN_MENU_SCREEN = "main_menu_screen"
        val GALLERY_SCREEN = "gallery_screen"
        val PLAY_SCREEN = "play_screen"



    @Before
    fun setup() {
        composeTestRule.activityRule.scenario.onActivity { activity ->
            viewModel = ViewModelProvider(activity)[QuizObjectViewModel::class.java]
        }
    }


    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun testMainMenuTitle(){
        composeTestRule.onNodeWithText("Welcome to the Quiz App.v2!")
            .assertIsDisplayed()
    }


    @Test
    fun testNavBarNavigation(){
//check if home screen is the first one displayed
        composeTestRule.onNodeWithTag(MAIN_MENU_SCREEN).assertIsDisplayed()
//then clicking on gallery icon on the navbar and checks if the display is changed
        composeTestRule.onNodeWithContentDescription("Gallery", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithTag(GALLERY_SCREEN).assertIsDisplayed()

    }

    @Test
    fun testScoringSystemOfQuizApp(){
        //first click on the play button in the navbar to render the new screen
        composeTestRule.onNodeWithContentDescription("Play", useUnmergedTree = true).performClick()
        //check/assert that you are in the intended screen
        composeTestRule.onNodeWithTag("play_screen").assertIsDisplayed()

        //First check if the score is actually 0
        composeTestRule.onNodeWithText("Score: 0 / 0")

        //clicking on a button that have Zubat in it, could not find a smoother way
        composeTestRule.onNodeWithTag("answerZubat").performClick()

        //checking if it was correct
        val wasItCorrect = try{
            composeTestRule.onNodeWithText("Correct").assertIsDisplayed()
            true
        } catch (e: AssertionError){
            false
        }

        //go to the next round
        composeTestRule.onNodeWithTag("next_round").performClick()


        //if true, check that if not check that...
        if(wasItCorrect){
            composeTestRule.onNodeWithText("Score: 1 / 1").assertIsDisplayed()
        }else{
            composeTestRule.onNodeWithText("Score: 0 / 1")
        }




    }



}