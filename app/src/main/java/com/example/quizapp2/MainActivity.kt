package com.example.quizapp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizapp2.ui.theme.QuizApp2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizApp2Theme {
                BottomNavBar()
            }
        }
    }
}


//testing navbar
@Composable
fun BottomNavBar() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Navigation.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Navigation.Home.route) { MainMenuScreen() }
            composable(Navigation.Gallery.route) { GalleryScreen() }
            composable(Navigation.Play.route) { PlayScreen() }
        }
    }
}

@Composable
fun MainMenuScreen(){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFedede9))
            .padding(all = 20.dp)
            ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        //Top text
        Text("Welcome to the Quiz App.v2!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline)
        //Undertext
        Text("Assignment number 2",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium

        )

    }

}



