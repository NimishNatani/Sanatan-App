package org.example.project.sanatanApp.presentation.screen.mainScrren.kathaScreen

import androidx.compose.foundation.layout.fillMaxSize
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
fun KathaListenScreenRoot(){
    KathaListenScreen()
}

@Composable
fun KathaListenScreen(){
    val isPause = remember { mutableStateOf(false) }
    VideoPlayerView(
        modifier = Modifier.fillMaxWidth().height(500.dp),
        url = "https://youtu.be/H0Zi2WAIExs?si=QFQozxgE5GsLiLsu",
        playerConfig = PlayerConfig(isPause =isPause.value, pauseCallback = {isPause.value = it } )
    )
}