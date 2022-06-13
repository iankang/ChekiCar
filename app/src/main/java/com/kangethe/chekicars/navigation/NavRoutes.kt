package com.kangethe.chekicars.navigation

sealed class NavRoutes(val route:String){
    object Home:NavRoutes("home")
    object HomeDetail:NavRoutes("home/{carId}")
    object HomeDetailGallery:NavRoutes("home/gallery/{carId}")
    object GalleryVideo:NavRoutes("home/gallery/video/{videoUrl}")
    object Likes:NavRoutes("likes")
    object Notifications:NavRoutes("notifications")
    object Messages:NavRoutes("messages")
}
