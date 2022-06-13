package com.kangethe.chekicars.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.google.android.exoplayer2.MediaItem
import com.imherrera.videoplayer.VideoPlayer
import com.imherrera.videoplayer.VideoPlayerControl
import com.imherrera.videoplayer.rememberVideoPlayerState

@Composable
fun PlayerVideo(videoUrl: String?, navController: NavHostController? = null) {
    Log.e("videoUrl",videoUrl!!)
    val playerState = rememberVideoPlayerState()
    TopCarDetail(navController)
    VideoPlayer(playerState = playerState) {
        VideoPlayerControl(
            state = playerState,
            title = videoUrl,
        )
    }

    LaunchedEffect(Unit) {
        playerState.player.setMediaItem(MediaItem.fromUri(videoUrl))
        playerState.player.prepare()
        playerState.player.playWhenReady = true
    }
}