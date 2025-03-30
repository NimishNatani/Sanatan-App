package org.example.project.sanatanApp.presentation.screen.mainScrren.aartiScreen

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
import org.example.project.sanatanApp.presentation.components.SwappableBox
import org.example.project.sanatanApp.presentation.components.SwappableDots
import org.example.project.sanatanApp.presentation.components.TopBar
import org.example.project.sanatanApp.presentation.components.swipeGesture
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AartiScreenRoot(
    viewModel: AartiScreenViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    onAartiClick:(aarti:Aarti) ->Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    AartiScreen(state = state, onAction = {
        viewModel.onAction(it)
    }, onBackClick = { onBackClick() },
        onAartiClick = { aarti -> onAartiClick(aarti)})
}

@Composable
fun AartiScreen(
    state: AartiScreenState,
    onAction: (AartiScreenAction) -> Unit,
    onBackClick: () -> Unit,
    onAartiClick:(aarti:Aarti) ->Unit
) {
    LaunchedEffect(Unit){
        onAction(AartiScreenAction.OnLoadingAarti)
    }
    if (state.isLoading) {

    } else if (state.errorMessage != null) {

    } else if(state.aartiList != emptyList<Aarti>()) {
        Column(modifier = Modifier.fillMaxSize().background(Gray).padding(bottom = 85.dp)) {
            TopBar(state.searchQuery, onSearchQueryChange = {
                onAction(AartiScreenAction.OnSearchQueryChange(it))
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
                val aartiRecommendedIndex = remember { mutableStateOf(0) }
                val aartiRecommendedItems = state.aartiList.map { aarti-> aarti.name }
                val aartiLastRecommendedSwipeTime = remember { mutableStateOf(0L) }
                SwappableBox(
                    aartiRecommendedIndex,
                    aartiRecommendedItems,
                    aartiLastRecommendedSwipeTime, onClick = {name->
                        onAartiClick(findAartiByName(state.aartiList,name)!!)
                    }
                )
                SwappableDots(aartiRecommendedItems.size, aartiRecommendedIndex, Modifier)

                Text("आपके लिए", fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp))
                val bhagwanRecommendedIndex = remember { mutableStateOf(0) }
                val bhagwanRecommendedItems =
                    listOf("  1", "  2", "  3", " 4", "5", "6", "7")
                val bhagwanLastRecommendedSwipeTime = remember { mutableStateOf(0L) }

                SwappableBox(
                    bhagwanRecommendedIndex,
                    bhagwanRecommendedItems,
                    bhagwanLastRecommendedSwipeTime
                )
                SwappableDots(bhagwanRecommendedItems.size, bhagwanRecommendedIndex, Modifier)

            }
        }
    }
}

fun getFirstAartiLink(aartiList: List<Aarti>, name: String): String? {
    return aartiList.find { it.name == name }  // Find Aarti by name
        ?.aarti?.values?.firstOrNull()  // Get first Link object
        ?.link  // Extract link
}

fun findAartiByName(aartiList: List<Aarti>, name: String): Aarti? {
    return aartiList.find { it.name == name }  // Find the Aarti by name
}