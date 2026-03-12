package com.example.quizapp2.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

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
                    "quiz_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

        // add this new function
        fun getReadableDb(context: Context): SupportSQLiteDatabase {
            return getDatabase(context).openHelper.readableDatabase
        }
    }

}