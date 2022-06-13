package com.kangethe.chekicars.navigation

sealed class NavRoutes(val route:String){
    object Home:NavRoutes("home")
    object HomeDetail:NavRoutes("home/{carId}")
    object Likes:NavRoutes("likes")
    object Notifications:NavRoutes("notifications")
    object Messages:NavRoutes("messages")
}
