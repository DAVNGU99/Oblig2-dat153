package com.example.quizapp2

import kotlinx.serialization.Serializable

//Sealed class betyr at ingenting kan extende den (:), litt som en ENUM
@Serializable
sealed class Navigation(val route: String) {

    @Serializable
    object Home : Navigation("home")

    @Serializable
    object P


}