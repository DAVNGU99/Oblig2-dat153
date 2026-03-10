package com.example.quizapp2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class QuizObjectViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: QuizObjectRepository
    val allQuizObjects: LiveData<List<QuizObject>>

    init {
        val database = QuizObjectDatabase.getDatabase(application)
        repository = QuizObjectRepository(database.quizObjectDAO())
        allQuizObjects = repository.allQuizObjects
    }

    fun insertQuizObject(quizObject: QuizObject){
        repository.insertQuizObject(quizObject)
    }

    fun removeQuizObject(name: String){
        repository.deleteQuizObject(name)
    }


}