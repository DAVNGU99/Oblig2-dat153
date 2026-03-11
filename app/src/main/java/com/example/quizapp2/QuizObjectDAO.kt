package com.example.quizapp2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuizObjectDAO {

    @Insert
    fun insertQuizObject(quizObject: QuizObject)

    @Query("SELECT * FROM quiz_objects")
    fun getAllQuizObjects(): LiveData<List<QuizObject>>

    @Query("SELECT * FROM quiz_objects WHERE name = :name")
    fun findQuizObject(name: String): List<QuizObject>

    @Query("DELETE FROM quiz_objects WHERE name = :name")
    fun removeQuizObject(name: String)


    @Query("SELECT COUNT(*) FROM quiz_objects")
    fun getCount() : Int

}