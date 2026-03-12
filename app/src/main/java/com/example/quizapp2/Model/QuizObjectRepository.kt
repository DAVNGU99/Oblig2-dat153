package com.example.quizapp2.Model

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//dependency injection, instead of calling it, it comes from the outside
class QuizObjectRepository (private val quizObjectDAO: QuizObjectDAO){

    val allQuizObjects : LiveData<List<QuizObject>> = quizObjectDAO.getAllQuizObjects();

    private val coroutineScope = CoroutineScope(Dispatchers.Main);

    fun insertQuizObject(quizObject: QuizObject){
        coroutineScope.launch(Dispatchers.IO){
            quizObjectDAO.insertQuizObject(quizObject)
        }
    }

    fun deleteQuizObject(name: String){
        coroutineScope.launch(Dispatchers.IO){
            quizObjectDAO.removeQuizObject(name)
        }
    }

    suspend fun isEmpty(): Boolean{
        return quizObjectDAO.getCount() == 0
    }

//    //maybe not necessary, but i just wanted to try it out
//    fun findObjectQuiz(name: String) {
//        coroutineScope.launch (Dispatchers.IO){
//            quizObjectDAO.findQuizObject(name)
//        }
//    }




}