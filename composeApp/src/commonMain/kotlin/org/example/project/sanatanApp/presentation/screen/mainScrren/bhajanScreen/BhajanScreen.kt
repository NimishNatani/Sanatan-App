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
import org.example.project.sanatanApp.domain.model.Aarti
import org.example.project.sanatanApp.domain.model.Bhajan
import org.example.project.sanatanApp.presentation.components.ShimmerEffect
import org.example.project.sanatanApp.presentation.components.SwappableBox
import org.example.project.sanatanApp.presentation.components.SwappableDots
import org.example.project.sanatanApp.presentation.components.TopBar
import org.example.project.sanatanApp.presentation.components.swipeGesture
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun BhajanScreenRoot(
    viewModel: BhajanScreenViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    onBhajanClick: (bhajan: Bhajan) -> Unit
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    BhajanScreen(state = state, onAction = {
        viewModel.onAction(it)
    }, onBackClick = { onBackClick() },
        onBhajanClick = { bhajan -> onBhajanClick(bhajan) })
}

@Composable
fun BhajanScreen(
    state: BhajanScreenState,
    onAction: (BhajanScreenAction) -> Unit,
    onBackClick: () -> Unit,
    onBhajanClick: (bhajan: Bhajan) -> Unit
) {

    LaunchedEffect(Unit) {
        onAction(BhajanScreenAction.OnLoadingBhajan)
        onAction(BhajanScreenAction.OnLoadingBhajanKalakar)
    }
    if (state.isLoading) {
ShimmerEffect()
    } else if (state.errorMessage != null) {

    } else if (state.bhajanList != emptyList<Aarti>() && state.bhajanKalakarList != emptyList<Aarti>()) {

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
                    SwappableDots(totalItems, selectedIndex, Modifier)
                }

                Spacer(modifier = Modifier.height(15.dp))
                Text("भजन चुनें", fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp))
                val bhagwanRecommendedIndex = remember { mutableStateOf(0) }
                val bhagwanRecommendedItems = extractFirstThumbnails(state.bhajanList)
                val bhagwanLastRecommendedSwipeTime = remember { mutableStateOf(0L) }

                SwappableBox(
                    bhagwanRecommendedIndex,
                    listOf(""),
                    bhagwanLastRecommendedSwipeTime, onClick = { name ->
                        onBhajanClick(findBhajanByName(state.bhajanList, name)!!)
                    }, items = bhagwanRecommendedItems
                )
                SwappableDots(bhagwanRecommendedItems.size, bhagwanRecommendedIndex, Modifier)


                Text("प्रमुख कलाकार", fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp))
                val kalakarRecommendedIndex = remember { mutableStateOf(0) }
                val kalakarRecommendedItems =
                    extractFirstThumbnails(state.bhajanKalakarList)
                val kalakarLastRecommendedSwipeTime = remember { mutableStateOf(0L) }

                SwappableBox(
                    kalakarRecommendedIndex,
                    listOf(""),
                    kalakarLastRecommendedSwipeTime, onClick = { name ->
                        onBhajanClick(findBhajanByName(state.bhajanKalakarList, name)!!)
                    }, items = kalakarRecommendedItems
                )
                SwappableDots(kalakarRecommendedItems.size, kalakarRecommendedIndex, Modifier)


                Text("आपके लिए", fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp))
                val recommendedIndex = remember { mutableStateOf(0) }
                val recommendedItems = listOf("  1", "  2", "  3", " 4", "5", "6", "7")
                val lastRecommendedSwipeTime = remember { mutableStateOf(0L) }

                SwappableBox(
                    recommendedIndex,
                    recommendedItems,
                    lastRecommendedSwipeTime,
                    2,
                    120.dp,
                    160.dp
                )
                SwappableDots(recommendedItems.size, recommendedIndex, Modifier)
            }
        }
    }
}

private fun extractFirstThumbnails(bhajanList: List<Bhajan>): List<Pair<String, String>> {
    return bhajanList.mapNotNull { bhajan ->
        val firstThumbnail =
            bhajan.bhajan.values.firstOrNull { it.thumbnail.isNotEmpty() }?.thumbnail
        firstThumbnail?.let { bhajan.name to it }
    }
}

private fun findBhajanByName(bhajanList: List<Bhajan>, name: String): Bhajan? {
    return bhajanList.find { it.name == name }  // Find the Aarti by name
}