package com.example.quizapp2.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri

class QuizContentProvider : ContentProvider(){


    companion object {
        const val AUTHORITY = "com.example.quizapp2.provider"
        const val PATH = "quiz_objects"
        const val TABLE_NAME = "quiz_objects"

        const val QUIZ_OBJECTS = 1
        const val QUIZ_OBJECT_ID = 2

        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, PATH, QUIZ_OBJECTS)
            addURI(AUTHORITY, "$PATH/#", QUIZ_OBJECT_ID)
        }
    }



    override fun getType(uri: Uri): String = when (uriMatcher.match(uri)) {
        QUIZ_OBJECTS -> "vnd.android.cursor.dir/$AUTHORITY.$PATH"
        QUIZ_OBJECT_ID -> "vnd.android.cursor.item/$AUTHORITY.$PATH"
        else -> throw IllegalArgumentException("Unknown URI: $uri")
    }



    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val context = context ?: return null


        val dbPath = context.getDatabasePath("quiz_database").absolutePath
        val db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY)

        val queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = TABLE_NAME

        when (uriMatcher.match(uri)) {
            QUIZ_OBJECTS -> { }
            QUIZ_OBJECT_ID -> {
                queryBuilder.appendWhere("id = ${uri.lastPathSegment}")
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }

        return queryBuilder.query(
            db,
            arrayOf("name", "image_uri AS URI"),
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )
    }

    override fun delete(
        p0: Uri,
        p1: String?,
        p2: Array<out String?>?
    ): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        p0: Uri,
        p1: ContentValues?,
        p2: String?,
        p3: Array<out String?>?
    ): Int {
        TODO("Not yet implemented")
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

}