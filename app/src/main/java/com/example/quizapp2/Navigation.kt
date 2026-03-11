package com.example.quizapp2

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.serialization.Serializable

//litt som en enum + singleton
@Serializable
sealed class Navigation(val route: String) {
    @Serializable
    object Home : Navigation("home")
    @Serializable
    object Gallery : Navigation("gallery")
    @Serializable
    object Play : Navigation("play")
}


data class BarItem(
    val title: String,
    val image: ImageVector,
    val navRoute: Navigation,
    val route: String
)


object NavBarItems {
    val BarItems = listOf(
        BarItem("Home", Icons.Filled.Home, Navigation.Home, Navigation.Home.route),
        BarItem("Gallery", Icons.Filled.PhotoLibrary, Navigation.Gallery, Navigation.Gallery.route),
        BarItem("Play", Icons.Filled.PlayCircleFilled, Navigation.Play, Navigation.Play.route)
    )
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.navRoute) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(imageVector = navItem.image, contentDescription = navItem.title) },
                label = { Text(navItem.title) }
            )
        }
    }
}