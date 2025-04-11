package org.example.project.sanatanApp.presentation.screen.mainScrren.youtubeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import chaintech.videoplayer.model.PlayerConfig
import chaintech.videoplayer.ui.video.VideoPlayerView
import org.example.project.core.presentation.Black
import org.example.project.core.presentation.Gray
import org.example.project.core.presentation.TextSize
import org.example.project.core.presentation.White
import org.example.project.sanatanApp.presentation.components.OrangeText
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun YoutubeScreenRoot(url: String?, viewModel: YoutubeScreenViewModel = koinViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()


    YoutubeScreen(url, state = state, onAction = {
        viewModel.onAction(it)
    })
}

@Composable
fun YoutubeScreen(
    url: String?,
    state: YoutubeScreenState,
    onAction: (YoutubeScreenAction) -> Unit,
) {
    LaunchedEffect(Unit) {
        onAction(YoutubeScreenAction.OnLoadingSubtitles(url!!))
    }
    if (state.isLoading) {

    } else if (state.errorMessage != null) {

    } else if (state.youtube?.subtitles != null) {

        val isPause = remember { mutableStateOf(false) }


        Column(
            modifier = Modifier.fillMaxSize().background(White).padding(vertical = 10.dp, horizontal = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // YouTube Player Box
            Box(
                modifier = Modifier.fillMaxWidth().background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                VideoPlayerView(
                    modifier = Modifier.fillMaxWidth().height(250.dp).clip(
                        RoundedCornerShape(8.dp)),
                    url = url!!,
                    playerConfig = PlayerConfig(
                        isPause = isPause.value,
                        pauseCallback = { isPause.value = it })
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Subtitle Section
            Column(
                modifier = Modifier.fillMaxSize().padding(10.dp)
            ) {
                Text(state.youtube.title, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Black)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Subtitle", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))

                // Scrollable Subtitle Texts
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier.fillMaxSize().verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val subtitlesStatus = parseStringFromSubtitles(state.youtube.subtitles)
                    OrangeText(if(subtitlesStatus?.status !="error"){
                        subtitlesStatus!!.message
                    }else{
                        "Sorry!\nSubtitle not found"
                    }, textSize = TextSize.large, alignment = TextAlign.Center)
                }
            }
        }
    }
}