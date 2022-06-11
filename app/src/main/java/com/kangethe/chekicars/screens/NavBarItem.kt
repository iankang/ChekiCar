package com.kangethe.chekicars.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.rounded.Email
import com.kangethe.chekicars.utils.BarItem

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "home",
            image = Icons.Filled.Home,
            route = "home"
        ),
        BarItem(
            title = "likes",
            image = Icons.Filled.Favorite,
            route = "likes"
        ),
        BarItem(
            title = "notifications",
            image = Icons.Filled.Notifications,
            route = "notifications"
        ),
        BarItem(
            title = "messages",
            image = Icons.Rounded.Email,
            route = "messages"
        ),

        )
}