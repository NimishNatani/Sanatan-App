package org.example.project.sanatanApp.presentation.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.core.presentation.Gray
import org.example.project.core.presentation.Orange
import org.example.project.core.presentation.White

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transtion = rememberInfiniteTransition(label = "")
    val startOffsetX by transtion.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(animation = tween(2000)), label = ""
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFD9D7DA),
                Color(0xFFC3C2C4),
//                Color(0xFFBEBCBC)
            ), start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned { size = it.size }
}

@Composable
fun ShimmerEffect() {
    Column(modifier = Modifier.fillMaxSize().background(Gray).padding(bottom = 85.dp)) {
        TopBarShimmerEffect()
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier.fillMaxSize().background(Gray)
                .padding(horizontal = 10.dp).verticalScroll(rememberScrollState()),
        ) {
            val selectedIndex = remember { mutableStateOf(0) }
            val totalItems = 4
            val lastSwipeTime = remember { mutableStateOf(0L) }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Card(
                    modifier = Modifier.fillMaxWidth().height(170.dp)
                        .padding(vertical = 8.dp, horizontal = 10.dp).background(
                            Gray
                        ).clip(RoundedCornerShape(8.dp))
                        .border(
                            BorderStroke((0.5).dp, Orange),
                            shape = RoundedCornerShape(8.dp)
                        ).shimmerEffect()
                        .align(Alignment.CenterHorizontally)
                        .swipeGesture(selectedIndex, totalItems, lastSwipeTime),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                }
                SwappableDots(totalItems, selectedIndex, Modifier)
            }

            Spacer(modifier = Modifier.height(15.dp))
            Text(" मंत्र चुनें", fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp))
            val mantraRecommendedIndex = remember { mutableStateOf(0) }
            val mantraRecommendedItems = listOf(Pair("",""),Pair("",""),Pair("",""),Pair("",""))
            val mantraLastRecommendedSwipeTime = remember { mutableStateOf(0L) }

            SwappableBoxShimmerEffect(
                mantraRecommendedIndex,
                mantraLastRecommendedSwipeTime, 2,
                items = mantraRecommendedItems,
            )
            SwappableBoxShimmerEffect(
                mantraRecommendedIndex,
                mantraLastRecommendedSwipeTime, 2,
                items = mantraRecommendedItems
            )
            SwappableBoxShimmerEffect(
                mantraRecommendedIndex,
                mantraLastRecommendedSwipeTime, 2,
                items = mantraRecommendedItems
            )
            SwappableDots(mantraRecommendedItems.size, mantraRecommendedIndex, Modifier)


            Text("आपके लिए", fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp))
            val recommendedIndex = remember { mutableStateOf(0) }
            val recommendedItems = listOf(Pair("",""),Pair("",""),Pair("",""))
            val lastRecommendedSwipeTime = remember { mutableStateOf(0L) }

            SwappableBoxShimmerEffect(
                recommendedIndex,
                lastRecommendedSwipeTime,2,
                items = recommendedItems,
                120.dp,
                160.dp
            )
            SwappableDots(recommendedItems.size, recommendedIndex, Modifier)
        }
    }
}

@Composable
fun SwappableBoxShimmerEffect(
    recommendedIndex: MutableState<Int>, lastRecommendedSwipeTime: MutableState<Long>,
    totalItems: Int = 4,
    items: List<Pair<String, String>> = listOf(Pair("", "")), height: Dp = 80.dp,
    width: Dp = 150.dp,
) {
    Row(
        modifier = Modifier.fillMaxWidth().height(100.dp).padding(vertical = 8.dp)
            .swipeGesture(
                recommendedIndex,
                items.size,
                lastRecommendedSwipeTime
            ),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        repeat(totalItems) { offset ->
            val index =
                (recommendedIndex.value + offset) % items.size
            Box(
                modifier = Modifier.size(height = height, width = width).clip(
                    RoundedCornerShape(4.dp)
                )
                    .border(
                        BorderStroke((0.5).dp, Orange),
                        RoundedCornerShape(4.dp)
                    ).background(White).shimmerEffect()
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
            }
            Spacer(modifier = Modifier.width(10.dp))

        }
    }
}

@Composable
fun TopBarShimmerEffect() {
    Box(modifier = Modifier.fillMaxWidth().height(70.dp).background(Orange)) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 5.dp, start = 10.dp, end = 10.dp)
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                tint = Gray,
                contentDescription = "arrow",
                modifier = Modifier.size(30.dp)
            )

            Spacer(modifier = Modifier.width(5.dp))
            Box(
                modifier = Modifier.clip(RoundedCornerShape(8.dp))
                    .background(
                        shape = RoundedCornerShape(8.dp),
                        color = White
                    ).height(50.dp).shimmerEffect().width(250.dp)
                    .minimumInteractiveComponentSize()
            )
        }
    }
}