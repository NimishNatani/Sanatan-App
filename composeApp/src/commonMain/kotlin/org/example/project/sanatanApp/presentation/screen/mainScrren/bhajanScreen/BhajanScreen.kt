package org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.project.core.presentation.Gray
import org.example.project.core.presentation.Orange
import org.example.project.sanatanApp.domain.model.Bhajan
import org.example.project.sanatanApp.presentation.components.BhagwanSwappableBox
import org.example.project.sanatanApp.presentation.components.ShimmerEffect
import org.example.project.sanatanApp.presentation.components.SwappableBox
import org.example.project.sanatanApp.presentation.components.SwappableDots
import org.example.project.sanatanApp.presentation.components.TopBar
import org.example.project.sanatanApp.presentation.components.swipeGesture
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun BhajanScreenRoot(
    viewModel: BhajanScreenViewModel = koinViewModel(),
    name: String,
    isKalakar: Boolean,
    onBackClick: () -> Unit,
    onBhajanClick: (link: String) -> Unit
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val screenSize = viewModel.getScreenSize()

    BhajanScreen(state = state, onAction = {
        viewModel.onAction(it)
    }, onBackClick = { onBackClick() },
        onBhajanClick = { link -> onBhajanClick(link) },
        screenSize = screenSize, name = name, isKalakar = isKalakar
    )
}

@Composable
fun BhajanScreen(
    state: BhajanScreenState,
    name: String,
    isKalakar: Boolean,
    onAction: (BhajanScreenAction) -> Unit,
    onBackClick: () -> Unit,
    onBhajanClick: (link: String) -> Unit,
    screenSize: Pair<Float, Float>
) {

    LaunchedEffect(Unit) {
        if (isKalakar) {
//            onAction(BhajanScreenAction.OnLoadingBhajanKalakar(name))
        } else {
            onAction(BhajanScreenAction.OnLoadingBhajan(name))
        }
    }
    if (state.isLoading) {
        ShimmerEffect()
    } else if (state.errorMessage != null) {

    } else if (state.bhajan != null || state.bhajanKalakar != null) {
        val bhajan = (state.bhajan ?: state.bhajanKalakar)!!
        val thumbnail = splitBhajanLinks(bhajan)
        Column(modifier = Modifier.fillMaxSize().background(Gray).padding(bottom = 85.dp)) {
            TopBar(state.searchQuery, onSearchQueryChange = {
                onAction(BhajanScreenAction.OnSearchQueryChange(it))
            }, onBackClick = {
                onBackClick()
            })
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier.fillMaxSize().background(Gray)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 10.dp),
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
                            )
                            .align(Alignment.CenterHorizontally)
                            .swipeGesture(selectedIndex, totalItems, lastSwipeTime),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                    }
                    SwappableDots( selectedIndex, Modifier,totalItems,)
                }

                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    if (isKalakar) {
                        "प्रमुख कलाकार"
                    } else "भजन चुनें", fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp)
                )
                val bhagwanRecommendedIndex = remember { mutableStateOf(0) }
                val bhagwanRecommendedItems1 = thumbnail.first
                val bhagwanRecommendedItems2 = thumbnail.second
                val bhagwanLastRecommendedSwipeTime = remember { mutableStateOf(0L) }

                SwappableBox(
                    bhagwanRecommendedIndex,
                    bhagwanLastRecommendedSwipeTime,
                    bhagwanRecommendedItems1,
                    onClick = { name ->
                        onBhajanClick(getLinkUrlByThumbnail(bhajan, name)!!)
                    },
                    height = 120.dp,
                    width = (screenSize.first.toInt() / 2 - 16).dp
                )
                SwappableBox(
                    bhagwanRecommendedIndex,
                    bhagwanLastRecommendedSwipeTime,
                    bhagwanRecommendedItems2,
                    onClick = { name ->
                        onBhajanClick(getLinkUrlByThumbnail(bhajan, name)!!)
                    },
                    height = 120.dp,
                    width = (screenSize.first.toInt() / 2 - 16).dp
                )
                SwappableDots( bhagwanRecommendedIndex, Modifier,bhagwanRecommendedItems1.size,)

                Text("आपके लिए", fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp))
                val recommendedIndex = remember { mutableStateOf(0) }
                val recommendedItems = listOf("  1", "  2", "  3", " 4", "5", "6", "7")
                val lastRecommendedSwipeTime = remember { mutableStateOf(0L) }

                BhagwanSwappableBox(
                    recommendedIndex,
                    lastRecommendedSwipeTime,
                    height = 120.dp,
                    width = 160.dp
                )
                SwappableDots( recommendedIndex, Modifier)
            }
        }
    }
}

fun splitBhajanLinks(bhajan: Bhajan): Pair<List<Pair<String, String>>, List<Pair<String, String>>> {
    val thumbnailsWithNames = bhajan.bhajan.values.map { it.thumbnail to bhajan.name }
    val halfSize = thumbnailsWithNames.size / 2

    val firstHalf = thumbnailsWithNames.take(halfSize)
    val secondHalf = thumbnailsWithNames.drop(halfSize)

    return firstHalf to secondHalf
}

fun getLinkUrlByThumbnail(bhajan: Bhajan, thumbnail: String): String? {
    return bhajan.bhajan.values.find { it.thumbnail == thumbnail }?.link
}
