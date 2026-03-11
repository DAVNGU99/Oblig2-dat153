package com.example.quizapp2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuizObjectViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: QuizObjectRepository
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val allQuizObjects: LiveData<List<QuizObject>>


    init {
        val database = QuizObjectDatabase.getDatabase(application)
        repository = QuizObjectRepository(database.quizObjectDAO())
        allQuizObjects = repository.allQuizObjects

        //seed images
        preSeedImages()


    }

  //preSeed the application if its empty

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


}