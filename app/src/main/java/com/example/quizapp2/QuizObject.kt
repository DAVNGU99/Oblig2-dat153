package com.example.quizapp2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "quiz_object")
data class QuizObject(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name : String = "",

    @ColumnInfo(name = "image_uri")
    var imageUri : String = ""
)