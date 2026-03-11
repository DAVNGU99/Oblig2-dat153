package com.example.quizapp2

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PlayScreen(quizViewModel : QuizObjectViewModel = viewModel()) {


    val quizObjects by quizViewModel.allQuizObjects.observeAsState(emptyList())
    val quizScore by remember { mutableStateOf("0") }

    if (quizObjects.size < 3) {
        Box(modifier = Modifier.fillMaxSize().background(Color(0xFFedede9)), contentAlignment = Alignment.Center) {
            Text("You need at least 3 objects in your gallery to play!")
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Placeholder for the actual game impl")
        }
    }




}