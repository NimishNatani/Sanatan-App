package org.example.project.sanatanApp.presentation.screen.mainScrren.kathaScreen

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
import org.example.project.sanatanApp.domain.model.Katha
import org.example.project.sanatanApp.presentation.components.BhagwanSwappableBox
import org.example.project.sanatanApp.presentation.components.ShimmerEffect
import org.example.project.sanatanApp.presentation.components.SwappableBox
import org.example.project.sanatanApp.presentation.components.SwappableDots
import org.example.project.sanatanApp.presentation.components.TopBar
import org.example.project.sanatanApp.presentation.components.swipeGesture
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun KathaScreenRoot(
    viewModel: KathaScreenViewModel = koinViewModel(),
    name: String,
    isKalakar: Boolean,
    onBackClick: () -> Unit,
    onKathaClick: (link: String) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val screenSize = viewModel.getScreenSize()

    KathaScreen(
        state = state,
        onAction = { viewModel.onAction(it) },
        onBackClick = { onBackClick() },
        onKathaClick = { link -> onKathaClick(link) },
        screenSize = screenSize,
        name = name,
        isKalakar = isKalakar
    )
}

@Composable
fun KathaScreen(
    state: KathaScreenState,
    name: String,
    isKalakar: Boolean,
    onAction: (KathaScreenAction) -> Unit,
    onBackClick: () -> Unit,
    onKathaClick: (link: String) -> Unit,
    screenSize: Pair<Float, Float>
) {
    LaunchedEffect(Unit) {
        if (isKalakar) {
            onAction(KathaScreenAction.OnLoadingKathaKalakar(name))
        } else {
            onAction(KathaScreenAction.OnLoadingKatha(name))
        }
    }

    if (state.isLoading) {
        ShimmerEffect()
    } else if (state.errorMessage != null) {
        // TODO: Show error UI
    } else if (state.katha != null || state.kathaKalakar != null) {
        val katha = (state.katha ?: state.kathaKalakar)!!
        val thumbnail = splitKathaLinks(katha)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Gray)
                .padding(bottom = 85.dp)
        ) {
            TopBar(
                state.searchQuery,
                onSearchQueryChange = { onAction(KathaScreenAction.OnSearchQueryChange(it)) },
                onBackClick = { onBackClick() }
            )
            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Gray)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 10.dp),
            ) {
                val selectedIndex = remember { mutableStateOf(0) }
                val totalItems = 4
                val lastSwipeTime = remember { mutableStateOf(0L) }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp)
                            .padding(vertical = 8.dp, horizontal = 10.dp)
                            .background(Gray)
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                BorderStroke((0.5).dp, Orange),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .align(Alignment.CenterHorizontally)
                            .swipeGesture(selectedIndex, totalItems, lastSwipeTime),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {}
                    SwappableDots(selectedIndex, Modifier, totalItems)
                }

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    if (isKalakar) "प्रमुख कथाकार" else "कथा चुनें",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )

                val recommendedIndex = remember { mutableStateOf(0) }
                val kathaItems1 = thumbnail.first
                val kathaItems2 = thumbnail.second
                val lastSwipeTimeRec = remember { mutableStateOf(0L) }

                SwappableBox(
                    recommendedIndex,
                    lastSwipeTimeRec,
                    kathaItems1,
                    onClick = { thumb ->
                        onKathaClick(getLinkUrlByThumbnail(katha, thumb)!!)
                    },
                    height = 120.dp,
                    width = (screenSize.first.toInt() / 2 - 16).dp
                )

                SwappableBox(
                    recommendedIndex,
                    lastSwipeTimeRec,
                    kathaItems2,
                    onClick = { thumb ->
                        onKathaClick(getLinkUrlByThumbnail(katha, thumb)!!)
                    },
                    height = 120.dp,
                    width = (screenSize.first.toInt() / 2 - 16).dp
                )

                SwappableDots(recommendedIndex, Modifier, kathaItems1.size)

                Text("आपके लिए", fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp))
                val forYouIndex = remember { mutableStateOf(0) }
                val lastForYouSwipeTime = remember { mutableStateOf(0L) }

                BhagwanSwappableBox(
                    forYouIndex,
                    lastForYouSwipeTime,
                    height = 120.dp,
                    width = 160.dp
                )
                SwappableDots(forYouIndex, Modifier)
            }
        }
    }
}

fun splitKathaLinks(katha: Katha): Pair<List<Pair<String, String>>, List<Pair<String, String>>> {
    val thumbnailsWithNames = katha.katha.values.map { it.thumbnail to katha.name }
    val halfSize = thumbnailsWithNames.size / 2

    val firstHalf = thumbnailsWithNames.take(halfSize)
    val secondHalf = thumbnailsWithNames.drop(halfSize)

    return firstHalf to secondHalf
}

fun getLinkUrlByThumbnail(katha: Katha, thumbnail: String): String? {
    return katha.katha.values.find { it.thumbnail == thumbnail }?.link
}
