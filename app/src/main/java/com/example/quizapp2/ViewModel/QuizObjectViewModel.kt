package com.example.quizapp2.ViewModel

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.quizapp2.Model.QuizObject
import com.example.quizapp2.Model.QuizObjectDatabase
import com.example.quizapp2.Model.QuizObjectRepository
import com.example.quizapp2.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuizObjectViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: QuizObjectRepository
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val allQuizObjects: LiveData<List<QuizObject>>


    init {
        val database = QuizObjectDatabase.Companion.getDatabase(application)
        repository = QuizObjectRepository(database.quizObjectDAO())
        allQuizObjects = repository.allQuizObjects

        //seed images
        preSeedImages()


    }

  //preSeed the application if its empty

    @SuppressLint("SuspiciousIndentation")
    private fun preSeedImages(){
        coroutineScope.launch(Dispatchers.IO){
            if(repository.isEmpty()){
            val defaultImages = listOf<QuizObject>(
                QuizObject(name = "Torchic", imageUri = getDrawableUri(R.drawable.torchic)),
                QuizObject(name = "Ekans", imageUri = getDrawableUri(R.drawable.ekans)),
                QuizObject(name = "Zubat", imageUri = getDrawableUri(R.drawable.zubat))
                )


                defaultImages.forEach { quizObject -> repository.insertQuizObject(quizObject) }
            }

        }

    }

    private fun getDrawableUri(resId: Int): String {
        return "android.resource://${getApplication<Application>().packageName}/$resId"
    }

    fun insertQuizObject(quizObject: QuizObject){
        repository.insertQuizObject(quizObject)
    }

    fun removeQuizObject(name: String){
        repository.deleteQuizObject(name)
    }

   //game logic for the quiz
    var currentIndex by mutableIntStateOf(0)
        private set
    var quizScore by mutableIntStateOf(0)
        private set
    var attempts by mutableIntStateOf(0)
        private set
    var showResult by mutableStateOf(false)
        private set
    var selectedOption by mutableStateOf<String?>(null)
        private set

    fun nextRound() {
        currentIndex++
        showResult = false
        selectedOption = null
    }

    fun submitAnswer(option: String, correctAnswer: String) {
        selectedOption = option
        showResult = true
        attempts++
        if (option == correctAnswer) quizScore++
    }

    fun resetScore(){
        quizScore = 0
        attempts = 0
    }

}