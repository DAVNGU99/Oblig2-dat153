package com.example.quizapp2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuizObjectDAO {

    //
    @Insert
    fun insertQuizObject(quizObject : QuizObject)

    //Finds all the rows in the database
    @Query("SELECT * FROM quiz_object")
    fun getAllQuizObjects() : LiveData<List<QuizObject>>






}