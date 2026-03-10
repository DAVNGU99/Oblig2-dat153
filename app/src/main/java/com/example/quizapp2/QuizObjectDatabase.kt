package com.example.quizapp2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [QuizObject::class], version = 1)
abstract class QuizObjectDatabase : RoomDatabase() {
    abstract fun quizObjectDAO(): QuizObjectDAO

    companion object {
        private var INSTANCE: QuizObjectDatabase? = null

        fun getDatabase(context: Context): QuizObjectDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizObjectDatabase::class.java,
                    "quiz_database"  // this becomes the actual SQLite filename
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}