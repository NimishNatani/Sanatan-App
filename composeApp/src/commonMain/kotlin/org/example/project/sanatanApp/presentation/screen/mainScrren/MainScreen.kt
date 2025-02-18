package org.example.project.sanatanApp.presentation.screen.mainScrren

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Down
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Up
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.project.core.presentation.Gray
import org.example.project.core.presentation.Orange
import org.example.project.core.presentation.White
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.homeScreen.HomeScreenRoot
import org.example.project.sanatanApp.presentation.screen.mainScrren.homeScreen.HomeScreenViewModel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import sanatanapp.composeapp.generated.resources.Res
import sanatanapp.composeapp.generated.resources.donation
import sanatanapp.composeapp.generated.resources.download
import sanatanapp.composeapp.generated.resources.home
import sanatanapp.composeapp.generated.resources.mic
import sanatanapp.composeapp.generated.resources.notification
import sanatanapp.composeapp.generated.resources.video
import kotlin.math.roundToInt

@Composable
fun MainScreenRoot(
    viewModel: MainScreenViewModel = koinViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    MainScreen(state = state, onAction = {
        viewModel.onAction(it)
    })

}

@Composable
fun MainScreen(state: MainScreenState, onAction: (MainScreenAction) -> Unit) {

    val offsetX = remember { mutableStateOf(-50f) }
    val offsetY = remember { mutableStateOf(600f) }
    val items = listOf(
        BottomNavItem(0, Res.drawable.home, "Home"),
        BottomNavItem(1, Res.drawable.video, "Video"),
        BottomNavItem(2, Res.drawable.mic, "Audio"),
        BottomNavItem(3, Res.drawable.donation, "Donation"),
        BottomNavItem(4, Res.drawable.download, "Download")
    )
    val homeScreenViewModel = koinViewModel<HomeScreenViewModel>()


    Box(modifier = Modifier.fillMaxSize()) {

        Box(modifier = Modifier.fillMaxSize()) {
            AnimatedContent(targetState = state.selectedTabIndex.iconNumber,
                transitionSpec = {
                    slideIntoContainer(
                        animationSpec = tween(durationMillis = 300, easing = EaseIn),
                        towards = Up
                    ).togetherWith(
                        slideOutOfContainer(
                            animationSpec = tween(300, easing = EaseOut),
                            towards = Down
                        )
                    )
                }) { targetState ->
                when (targetState) {
                    0 -> {
                        HomeScreenRoot(homeScreenViewModel)
                    }

                    1 -> {
                    }

                    2 -> {
                    }

                    3 -> {
                    }
                }
            }
        }

        Box(
            modifier = Modifier.offset {
                IntOffset(
                    x= offsetX.value.roundToInt(),
                    y= offsetY.value.roundToInt()
                )
            }.pointerInput(Unit){
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX.value += dragAmount.x
                    offsetY.value += dragAmount.y
                }
            }.size(60.dp).clip(CircleShape).background(Orange).align(Alignment.CenterEnd)
        ){Icon(
            painter = painterResource(Res.drawable.notification),
            contentDescription = "search",
            tint = White,
            modifier = Modifier.align(Alignment.Center)
        )}


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Gray)
                .border(width = (0.5).dp, color = Orange)
                .padding(vertical = 18.dp).align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                BottomNavItemView(
                    item = item,
                    isSelected = item.iconNumber == state.selectedTabIndex.iconNumber,
                    onClick = {
                        onAction(
                            MainScreenAction.OnTabSelected(
                                item.iconNumber,
                                item.iconResId,
                                item.name
                            )
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun BottomNavItemView(
    item: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.clip(CircleShape).background(if (isSelected) Orange else White)
            .clickable { onClick() }.padding(8.dp)
    ) {
        Icon(
            painter = painterResource(item.iconResId),
            contentDescription = "bottom nav",
            tint = if (isSelected) White else Orange,
            modifier = Modifier.size(30.dp),
        )
    }
}


data class BottomNavItem(
    val iconNumber: Int,
    val iconResId: DrawableResource,
    val name: String
)