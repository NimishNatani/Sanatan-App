package org.example.project.sanatanApp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.http.Url
import kotlinx.datetime.Clock
import org.example.project.core.presentation.Orange
import org.example.project.core.presentation.White
import org.example.project.core.presentation.lightOrange
import kotlin.math.abs
import kotlin.time.Duration.Companion.milliseconds

private const val SWIPE_THRESHOLD = 100f
private val SWIPE_DELAY = 300.milliseconds

@Composable
fun Modifier.swipeGesture(
    recommendedIndex: MutableState<Int>,
    itemsCount: Int,
    lastRecommendedSwipeTime: MutableState<Long>
): Modifier = composed {
    pointerInput(Unit) {
        var totalDrag = 0f
        detectHorizontalDragGestures(
            onDragStart = { totalDrag = 0f },
            onHorizontalDrag = { _, dragAmount ->
                totalDrag += dragAmount
                val currentTime = Clock.System.now().toEpochMilliseconds()

                if (abs(totalDrag) > SWIPE_THRESHOLD &&
                    (currentTime - lastRecommendedSwipeTime.value) > SWIPE_DELAY.inWholeMilliseconds
                ) {
                    // Add different thresholds for left and right swipes
                    val rightSwipeThreshold = SWIPE_THRESHOLD * 1.2f
                    val leftSwipeThreshold = SWIPE_THRESHOLD

                    when {
                        totalDrag > 0 && totalDrag > rightSwipeThreshold -> {
                            recommendedIndex.value =
                                ((recommendedIndex.value - 1 + itemsCount) % itemsCount)
                            lastRecommendedSwipeTime.value = currentTime
                            totalDrag = 0f
                        }

                        totalDrag < 0 && abs(totalDrag) > leftSwipeThreshold -> {
                            recommendedIndex.value = ((recommendedIndex.value + 1) % itemsCount)
                            lastRecommendedSwipeTime.value = currentTime
                            totalDrag = 0f
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun SwappableDots(totalItems: Int, selectedIndex: MutableState<Int>, modifier: Modifier) {
    Row(
        modifier = modifier.padding(top = 4.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(totalItems) { index ->
            Box(
                modifier = modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(if (index == selectedIndex.value) Orange else lightOrange)
                    .padding(4.dp)
            )
            Spacer(modifier = modifier.width(4.dp))
        }
    }
}

@Composable
fun SwappableBox(
    recommendedIndex: MutableState<Int>,
    item:List<String> = listOf(""),
    lastRecommendedSwipeTime: MutableState<Long>,
    totalItems: Int=4,
    height:Dp=80.dp,
    width:Dp=80.dp,
    items: List<Pair<String,String>> = listOf( Pair("", "")),
    onClick:(name:String)->Unit = {}
){
    Row(
        modifier = Modifier.fillMaxWidth().height(100.dp).padding(vertical = 8.dp)
            .swipeGesture(
                recommendedIndex,
                items.size,
                lastRecommendedSwipeTime
            ),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        repeat(totalItems) { offset ->
            val index =
                (recommendedIndex.value + offset) % items.size
            Box(
                modifier = Modifier.size(height=height, width = width).clip(
                    RoundedCornerShape(4.dp)
                ).clickable { onClick(items[index].first) }
                    .border(
                        BorderStroke((0.5).dp, Orange),
                        RoundedCornerShape(4.dp)
                    ).background(White)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                KamelImage(
                    { asyncPainterResource(data = Url(items[index].second)) },
                    contentDescription = "Image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.width(10.dp))

        }
    }
}