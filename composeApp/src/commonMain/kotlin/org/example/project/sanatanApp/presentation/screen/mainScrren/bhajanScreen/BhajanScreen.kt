package org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.Clock
import org.example.project.core.presentation.Gray
import org.example.project.core.presentation.Orange
import org.example.project.core.presentation.White
import org.example.project.core.presentation.lightOrange
import org.example.project.sanatanApp.presentation.components.SearchBar
import org.jetbrains.compose.resources.painterResource
import sanatanapp.composeapp.generated.resources.Res
import sanatanapp.composeapp.generated.resources.aarti
import sanatanapp.composeapp.generated.resources.notification
import kotlin.math.abs
import kotlin.time.Duration.Companion.milliseconds

private const val SWIPE_THRESHOLD = 100f
private val SWIPE_DELAY = 300.milliseconds

@Composable
fun BhajanScreenRoot() {


}

@Composable
fun BhajanScreen(state: BhajanScreenState, onAction: (BhajanScreenAction) -> Unit) {

    Column(modifier = Modifier.fillMaxSize().background(Gray).padding(bottom = 85.dp)) {
        Box(modifier = Modifier.fillMaxWidth().height(70.dp).background(Orange)) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 5.dp, start = 10.dp, end = 10.dp)
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(Res.drawable.aarti),
                    contentDescription = "profile",
                    modifier = Modifier.clip(CircleShape).size(50.dp).border(
                        1.dp,
                        White, CircleShape
                    ),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.width(5.dp))
                SearchBar(
                    searchQuery = state.searchQuery,
                    onSearchQueryChange = {
                        onAction(BhajanScreenAction.OnSearch(query = state.searchQuery))
                    },
                    onImeSearch = { /*TODO*/ })

                Icon(
                    painter = painterResource(Res.drawable.notification),
                    contentDescription = "search",
                    modifier = Modifier.size(50.dp),
                    tint = White
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier.fillMaxSize().background(Gray).verticalScroll(rememberScrollState())
                .padding(horizontal = 10.dp),
        ) {
            var selectedIndex by remember { mutableStateOf(0) }
            val totalItems = 4
            var lastSwipeTime by remember { mutableStateOf(0L) }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Card(
                    modifier = Modifier.fillMaxWidth().height(170.dp)
                        .padding(vertical = 8.dp, horizontal = 10.dp).background(
                            Gray
                        ).clip(RoundedCornerShape(8.dp))
                        .border(BorderStroke((0.5).dp, Orange), shape = RoundedCornerShape(8.dp))
                        .align(Alignment.CenterHorizontally)
                        .pointerInput(Unit) {
                            var totalDrag = 0f
                            detectHorizontalDragGestures(
                                onDragStart = { totalDrag = 0f },
                                onHorizontalDrag = { _, dragAmount ->
                                    totalDrag += dragAmount
                                    val currentTime = Clock.System.now().toEpochMilliseconds()

                                    if (abs(totalDrag) > SWIPE_THRESHOLD &&
                                        (currentTime - lastSwipeTime) > SWIPE_DELAY.inWholeMilliseconds
                                    ) {
                                        // Add different thresholds for left and right swipes
                                        val rightSwipeThreshold = SWIPE_THRESHOLD * 1.2f
                                        val leftSwipeThreshold = SWIPE_THRESHOLD

                                        when {
                                            totalDrag > 0 && totalDrag > rightSwipeThreshold -> {
                                                selectedIndex =
                                                    ((selectedIndex - 1 + totalItems) % totalItems)
                                                lastSwipeTime = currentTime
                                                totalDrag = 0f
                                            }

                                            totalDrag < 0 && abs(totalDrag) > leftSwipeThreshold -> {
                                                selectedIndex = ((selectedIndex + 1) % totalItems)
                                                lastSwipeTime = currentTime
                                                totalDrag = 0f
                                            }
                                        }
                                    }
                                }
                            )
                        }, elevation = CardDefaults.cardElevation(8.dp)
                ) {

                }
                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(totalItems) { index ->
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(if (index == selectedIndex) Orange else lightOrange)
                                .padding(4.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))
            Text("भगवन चुनें", fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp))
            var bhagwanRecommendedIndex by remember { mutableStateOf(0) }
            val bhagwanRecommendedItems = listOf("  1", "  2", "  3", " 4","5","6","7")
            var bhagwanLastRecommendedSwipeTime by remember { mutableStateOf(0L) }

            Row(
                modifier = Modifier.fillMaxWidth().height(100.dp).padding(vertical = 8.dp)
                    .pointerInput(Unit) {
                        var totalDrag = 0f
                        detectHorizontalDragGestures(
                            onDragStart = { totalDrag = 0f },
                            onHorizontalDrag = { _, dragAmount ->
                                totalDrag += dragAmount
                                val currentTime = Clock.System.now().toEpochMilliseconds()

                                if (abs(totalDrag) > SWIPE_THRESHOLD &&
                                    (currentTime - bhagwanLastRecommendedSwipeTime) > SWIPE_DELAY.inWholeMilliseconds) {
                                    // Add different thresholds for left and right swipes
                                    val rightSwipeThreshold = SWIPE_THRESHOLD * 1.2f
                                    val leftSwipeThreshold = SWIPE_THRESHOLD

                                    when {
                                        totalDrag > 0 && totalDrag > rightSwipeThreshold -> {
                                            bhagwanRecommendedIndex = ((bhagwanRecommendedIndex - 1 + bhagwanRecommendedItems.size) % bhagwanRecommendedItems.size)
                                            bhagwanLastRecommendedSwipeTime = currentTime
                                            totalDrag = 0f
                                        }
                                        totalDrag < 0 && abs(totalDrag) > leftSwipeThreshold -> {
                                            bhagwanRecommendedIndex = ((bhagwanRecommendedIndex + 1) % bhagwanRecommendedItems.size)
                                            bhagwanLastRecommendedSwipeTime = currentTime
                                            totalDrag = 0f
                                        }
                                    }
                                }
                            }
                        )
                    },
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(4) { offset ->
                    val index = (bhagwanRecommendedIndex + offset) % bhagwanRecommendedItems.size
                    Box(
                        modifier = Modifier.size(80.dp).clip(
                            RoundedCornerShape(4.dp)
                        )
                            .border(
                                BorderStroke((0.5).dp, Orange),
                                RoundedCornerShape(4.dp)
                            ).background(White)
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(bhagwanRecommendedItems[index], fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                }
            }
            Row(modifier = Modifier.padding(top = 4.dp).align(Alignment.CenterHorizontally), horizontalArrangement = Arrangement.Center) {
                repeat(bhagwanRecommendedItems.size) { index ->
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(if (index == bhagwanRecommendedIndex) Orange else lightOrange)
                            .padding(4.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }


            Text("प्रमुख कलाकार", fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp))
            var kalakarRecommendedIndex by remember { mutableStateOf(0) }
            val kalakarRecommendedItems = listOf("  1", "  2", "  3", " 4","5","6","7")
            var kalakarLastRecommendedSwipeTime by remember { mutableStateOf(0L) }

            Row(
                modifier = Modifier.fillMaxWidth().height(100.dp).padding(vertical = 8.dp)
                    .pointerInput(Unit) {
                        var totalDrag = 0f
                        detectHorizontalDragGestures(
                            onDragStart = { totalDrag = 0f },
                            onHorizontalDrag = { _, dragAmount ->
                                totalDrag += dragAmount
                                val currentTime = Clock.System.now().toEpochMilliseconds()

                                if (abs(totalDrag) > SWIPE_THRESHOLD &&
                                    (currentTime - kalakarLastRecommendedSwipeTime) > SWIPE_DELAY.inWholeMilliseconds) {
                                    // Add different thresholds for left and right swipes
                                    val rightSwipeThreshold = SWIPE_THRESHOLD * 1.2f
                                    val leftSwipeThreshold = SWIPE_THRESHOLD

                                    when {
                                        totalDrag > 0 && totalDrag > rightSwipeThreshold -> {
                                            kalakarRecommendedIndex = ((kalakarRecommendedIndex - 1 + kalakarRecommendedItems.size) % kalakarRecommendedItems.size)
                                            kalakarLastRecommendedSwipeTime = currentTime
                                            totalDrag = 0f
                                        }
                                        totalDrag < 0 && abs(totalDrag) > leftSwipeThreshold -> {
                                            kalakarRecommendedIndex = ((kalakarRecommendedIndex + 1) % kalakarRecommendedItems.size)
                                            kalakarLastRecommendedSwipeTime = currentTime
                                            totalDrag = 0f
                                        }
                                    }
                                }
                            }
                        )
                    },
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(4) { offset ->
                    val index = (kalakarRecommendedIndex + offset) % kalakarRecommendedItems.size
                    Box(
                        modifier = Modifier.size(80.dp).clip(
                            RoundedCornerShape(4.dp)
                        )
                            .border(
                                BorderStroke((0.5).dp, Orange),
                                RoundedCornerShape(4.dp)
                            ).background(White)
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(kalakarRecommendedItems[index], fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                }
            }
            Row(modifier = Modifier.padding(top = 4.dp).align(Alignment.CenterHorizontally), horizontalArrangement = Arrangement.Center) {
                repeat(kalakarRecommendedItems.size) { index ->
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(if (index == kalakarRecommendedIndex) Orange else lightOrange)
                            .padding(4.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }


            Text("आपके लिए", fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp))
            var recommendedIndex by remember { mutableStateOf(0) }
            val recommendedItems = listOf("  1", "  2", "  3", " 4","5","6","7")
            var lastRecommendedSwipeTime by remember { mutableStateOf(0L) }

            Row(
                modifier = Modifier.fillMaxWidth().height(100.dp).padding(vertical = 8.dp)
                    .pointerInput(Unit) {
                        var totalDrag = 0f
                        detectHorizontalDragGestures(
                            onDragStart = { totalDrag = 0f },
                            onHorizontalDrag = { _, dragAmount ->
                                totalDrag += dragAmount
                                val currentTime = Clock.System.now().toEpochMilliseconds()

                                if (abs(totalDrag) > SWIPE_THRESHOLD &&
                                    (currentTime - lastRecommendedSwipeTime) > SWIPE_DELAY.inWholeMilliseconds) {
                                    // Add different thresholds for left and right swipes
                                    val rightSwipeThreshold = SWIPE_THRESHOLD * 1.2f
                                    val leftSwipeThreshold = SWIPE_THRESHOLD

                                    when {
                                        totalDrag > 0 && totalDrag > rightSwipeThreshold -> {
                                            recommendedIndex = ((recommendedIndex - 1 + recommendedItems.size) % recommendedItems.size)
                                            lastRecommendedSwipeTime = currentTime
                                            totalDrag = 0f
                                        }
                                        totalDrag < 0 && abs(totalDrag) > leftSwipeThreshold -> {
                                            recommendedIndex = ((recommendedIndex + 1) % recommendedItems.size)
                                            lastRecommendedSwipeTime = currentTime
                                            totalDrag = 0f
                                        }
                                    }
                                }
                            }
                        )
                    },
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(4) { offset ->
                    val index = (recommendedIndex + offset) % recommendedItems.size
                    Box(
                        modifier = Modifier.size(80.dp).clip(
                            RoundedCornerShape(4.dp)
                        )
                            .border(
                                BorderStroke((0.5).dp, Orange),
                                RoundedCornerShape(4.dp)
                            ).background(White)
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(recommendedItems[index], fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                }
            }
            Row(modifier = Modifier.padding(top = 4.dp).align(Alignment.CenterHorizontally), horizontalArrangement = Arrangement.Center) {
                repeat(recommendedItems.size) { index ->
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(if (index == recommendedIndex) Orange else lightOrange)
                            .padding(4.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
        }
    }
}