package com.kangethe.chekicars.navigation

sealed class NavRoutes(val route:String){
    object Home:NavRoutes("home")
    object Likes:NavRoutes("likes")
    object Notifications:NavRoutes("notifications")
    object Messages:NavRoutes("messages")
}
