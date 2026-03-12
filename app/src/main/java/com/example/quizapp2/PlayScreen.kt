package com.example.quizapp2
import androidx.compose.foundation.Image
import androidx.compose.runtime.setValue
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun PlayScreen(quizViewModel : QuizObjectViewModel = viewModel()) {


    val quizObjects by quizViewModel.allQuizObjects.observeAsState(emptyList())

    if (quizObjects.size < 3) {
        Box(modifier = Modifier.fillMaxSize().background(Color(0xFFedede9)), contentAlignment = Alignment.Center) {
            Text("You need at least 3 objects in your gallery to play!")
        }
    } else {
        GameImpl(quizViewModel = quizViewModel)
    }

}

@Composable
fun GameImpl(quizViewModel : QuizObjectViewModel){

    var roundKey by remember { mutableIntStateOf(0) }
    val quizObjects by quizViewModel.allQuizObjects.observeAsState(emptyList())
    var quizScore by remember { mutableIntStateOf(0) }
    var attemps by remember {mutableIntStateOf(0)}
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var showResult by remember { mutableStateOf(false) }

    val theCorrectQuizAnswer by remember(roundKey) { mutableStateOf(quizObjects.random()) }



    val wrongQuizAnswers by remember(theCorrectQuizAnswer){
        mutableStateOf(
            quizObjects
                .filter { x -> x.id != theCorrectQuizAnswer.id }
                .shuffled()
                .take(2)
                .map { n -> n.name }
        )
    }

    val quizOptions by remember { mutableStateOf((wrongQuizAnswers + theCorrectQuizAnswer.name)) };

    Scaffold(containerColor = Color(0xFFedede9)) { padding ->

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Quiz")
            Text("Score: $quizScore / $attemps")

            AsyncImage(
                model = theCorrectQuizAnswer.imageUri,
                contentDescription = "Who is this pokemon?",
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop
            )


            quizOptions.forEach {
                option ->
                Button(onClick = {
                    selectedOption = option
                    showResult = true
                    attemps++
                    if(option == theCorrectQuizAnswer.name) quizScore++
                }, enabled = !showResult,
                    modifier = Modifier.fillMaxWidth()
                ) { Text(option)}
            }

            if(showResult){
                val isCorrect = selectedOption == theCorrectQuizAnswer.name

                Text(text = if (isCorrect) "Correct" else "Wrong! ${theCorrectQuizAnswer.name}" )

                Button(onClick = {
                    roundKey++
                    showResult = false
                    selectedOption = null
                }) {Text("Next Round") }

            }




        }

    }

}