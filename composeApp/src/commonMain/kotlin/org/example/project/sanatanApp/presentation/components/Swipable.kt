package org.example.project.sanatanApp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import sanatanapp.composeapp.generated.resources.Res
import sanatanapp.composeapp.generated.resources.anirudhacharya
import sanatanapp.composeapp.generated.resources.anoop_jalota
import sanatanapp.composeapp.generated.resources.anuradha_paudwal
import sanatanapp.composeapp.generated.resources.bageshwar_dham
import sanatanapp.composeapp.generated.resources.chitralekha_ji
import sanatanapp.composeapp.generated.resources.chotu_singh_raavana
import sanatanapp.composeapp.generated.resources.devkinandan_thakur
import sanatanapp.composeapp.generated.resources.durga_ji
import sanatanapp.composeapp.generated.resources.gulshan_kumar
import sanatanapp.composeapp.generated.resources.hanumanji
import sanatanapp.composeapp.generated.resources.indreshji_maharaj
import sanatanapp.composeapp.generated.resources.jayakishoriji
import sanatanapp.composeapp.generated.resources.kanhaiya_mittal
import sanatanapp.composeapp.generated.resources.lakhbir_singh_lakkha
import sanatanapp.composeapp.generated.resources.laxmiji
import sanatanapp.composeapp.generated.resources.pradeep_mishra
import sanatanapp.composeapp.generated.resources.rajan_ji
import sanatanapp.composeapp.generated.resources.ramji
import sanatanapp.composeapp.generated.resources.saraswatiji
import sanatanapp.composeapp.generated.resources.shivji
import sanatanapp.composeapp.generated.resources.vishnuji
import kotlin.math.abs
import kotlin.time.Duration.Companion.milliseconds

private const val SWIPE_THRESHOLD = 100f
private val SWIPE_DELAY = 300.milliseconds

val bhagwanList =
    listOf(
        Pair("hanumanji", Res.drawable.hanumanji),
        Pair("durgaji", Res.drawable.durga_ji),
        Pair("ganeshji", Res.drawable.ramji),
        Pair("laxmiji", Res.drawable.laxmiji),
        Pair("saraswatiji", Res.drawable.saraswatiji),
        Pair("shivji", Res.drawable.shivji),
        Pair("vishnuji", Res.drawable.vishnuji)
    )

val kalakarList =
    listOf(
        Pair("lakhbir_singh_lakkha", Res.drawable.lakhbir_singh_lakkha),
        Pair("anoop_jalota", Res.drawable.anoop_jalota),
        Pair("anuradha_paudwal", Res.drawable.anuradha_paudwal),
        Pair("kanhaiya_mittal", Res.drawable.kanhaiya_mittal),
        Pair("gulshan_kumar", Res.drawable.gulshan_kumar),
        Pair("rajan_ji", Res.drawable.rajan_ji),
        Pair("chotu_singh_raavana", Res.drawable.chotu_singh_raavana)
    )

val kathaBhagwanList = listOf(
    Pair("hanuman_katha",Res.drawable.hanumanji),
    Pair("shiv_katha",Res.drawable.shivji),
    Pair("vishnu_katha",Res.drawable.vishnuji),
    Pair("krishna_katha",Res.drawable.durga_ji),)

val kathaKalakarList = listOf(
    Pair("bageshwar_dham",Res.drawable.bageshwar_dham),
    Pair("pradeep_mishra",Res.drawable.pradeep_mishra),
    Pair("devkinandan_thakur",Res.drawable.devkinandan_thakur),
    Pair("anirudhacharya",Res.drawable.anirudhacharya),
    Pair("indreshji_maharaj",Res.drawable.indreshji_maharaj),
    Pair("chitralekha_ji",Res.drawable.chitralekha_ji),
    Pair("jayakishoriji",Res.drawable.jayakishoriji)
)


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
fun SwappableDots(
    selectedIndex: MutableState<Int>,
    modifier: Modifier,
    totalItems: Int = bhagwanList.size,
) {
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
    lastRecommendedSwipeTime: MutableState<Long>,
    item: List<Pair<String, String>>,
    totalItems: Int = 4,
    height: Dp = 80.dp,
    width: Dp = 80.dp,
    onClick: (name: String) -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth().height(height).padding(vertical = 8.dp)
            .swipeGesture(
                recommendedIndex,
                item.size,
                lastRecommendedSwipeTime
            ),
        horizontalArrangement = if(item.size==1){Arrangement.Center} else Arrangement.SpaceEvenly
    ) {
        repeat(item.size) { offset ->
            val index =
                (recommendedIndex.value + offset) % item.size
            Box(
                modifier = Modifier.size(height = height, width = width).clip(
                    RoundedCornerShape(4.dp)
                ).clickable { onClick(item[index].first) }
                    .border(
                        BorderStroke((0.5).dp, Orange),
                        RoundedCornerShape(4.dp)
                    ).background(White)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                    KamelImage(
                        { asyncPainterResource(data = Url(item[index].first)) },
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

@Composable
fun BhagwanSwappableBox(
    recommendedIndex: MutableState<Int>,
    lastRecommendedSwipeTime: MutableState<Long>,
//    bhagwanListItems:List<Pair<String,DrawableResource>> = bhagwanList,
    totalItems: Int = 4,
    height: Dp = 80.dp,
    width: Dp = 80.dp,
    isKalakar:Boolean=false,
    isKatha:Boolean = false,
    onClick: (name: String) -> Unit = {}
) {
    val bhagwanListItems = if(isKatha){
        if(isKalakar) kathaKalakarList else kathaBhagwanList
    }else{
        if(isKalakar) kalakarList else bhagwanList
    }
    Row(
        modifier = Modifier.fillMaxWidth().height(height).padding(vertical = 8.dp,)
            .swipeGesture(
                recommendedIndex,
                bhagwanListItems.size,
                lastRecommendedSwipeTime
            ),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        repeat(totalItems) { offset ->
            val index =
                (recommendedIndex.value + offset) % bhagwanListItems.size
            Box(
                modifier = Modifier.size(height = height, width = width).clip(
                    RoundedCornerShape(4.dp)
                ).clickable { onClick(bhagwanListItems[index].first) }
                    .border(
                        BorderStroke((0.5).dp, Orange),
                        RoundedCornerShape(4.dp)
                    ).background(White)
                    .padding(vertical = 8.dp, horizontal = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(bhagwanListItems[index].second),
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

@Composable
fun forYou(width: Dp){
    Text("आपके लिए", fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp))
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth().height(500.dp),
        contentPadding = PaddingValues(horizontal = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(bhagwanList.take(4)) { item ->
            Box(
                modifier = Modifier
                    .size(width =width, height = 100.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .clickable { /* Handle click */ }
                    .border(
                        BorderStroke(0.5.dp, Orange),
                        RoundedCornerShape(4.dp)
                    )
                    .background(White)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(item.second),
                    contentDescription = "Image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}