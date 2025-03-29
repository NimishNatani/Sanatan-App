package org.example.project.sanatanApp.presentation.screen.mainScrren.aartiScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import chaintech.videoplayer.model.PlayerConfig
import chaintech.videoplayer.ui.video.VideoPlayerView

@Composable
fun AartiListenScreenRoot(url:String){
    AartiListenScreen(url)
}

@Composable
fun AartiListenScreen(url:String?){
    val isPause = remember { mutableStateOf(false) }
    VideoPlayerView(
        modifier = Modifier.fillMaxWidth().height(500.dp),
        url = url!!,
        playerConfig = PlayerConfig(isPause =isPause.value, pauseCallback = {isPause.value = it } )
    )
}