package org.example.project.sanatanApp.presentation.screen.mainScrren.mantraScreen

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
import org.example.project.sanatanApp.presentation.components.BhagwanSwappableBox
import org.example.project.sanatanApp.presentation.components.SwappableBox
import org.example.project.sanatanApp.presentation.components.SwappableDots
import org.example.project.sanatanApp.presentation.components.TopBar
import org.example.project.sanatanApp.presentation.components.swipeGesture
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenAction
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenState
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenViewModel
import org.koin.compose.viewmodel.koinViewModel
import sanatanapp.composeapp.generated.resources.Res
import sanatanapp.composeapp.generated.resources.durga_ji
import sanatanapp.composeapp.generated.resources.hanumanji
import sanatanapp.composeapp.generated.resources.laxmiji
import sanatanapp.composeapp.generated.resources.ramji
import sanatanapp.composeapp.generated.resources.saraswatiji
import sanatanapp.composeapp.generated.resources.shivji
import sanatanapp.composeapp.generated.resources.vishnuji


@Composable
fun MantraBhagwanScreenRoot(
    viewModel: MantraScreenViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    onMantraClick: (name: String) -> Unit
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val screenSize = viewModel.getScreenSize()

    MantraBhagwanScreen(state = state, onAction = {
        viewModel.onAction(it)
    }, onBackClick = { onBackClick() },
        onMantraClick = { name -> onMantraClick(name) },
        screenSize = screenSize
    )
}

@Composable
fun MantraBhagwanScreen(
    state: MantraScreenState,
    onAction: (MantraScreenAction) -> Unit,
    onBackClick: () -> Unit,
    onMantraClick: (name: String) -> Unit,
    screenSize: Pair<Float, Float>
) {
    Column(modifier = Modifier.fillMaxSize().background(Gray).padding(bottom = 85.dp)) {
        TopBar(state.searchQuery, onSearchQueryChange = {
            onAction(MantraScreenAction.OnSearchQueryChange(it))
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
                SwappableDots( selectedIndex, Modifier,totalItems)
            }

            Spacer(modifier = Modifier.height(15.dp))
            Text("भगवन चुनेंं", fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp))
            val mantraRecommendedIndex = remember { mutableStateOf(0) }
            val mantraLastRecommendedSwipeTime = remember { mutableStateOf(0L) }

            BhagwanSwappableBox(
                mantraRecommendedIndex,
                mantraLastRecommendedSwipeTime,
                 onClick = { name ->
                    onMantraClick(name)
                },
                height = 80.dp,
                width = (screenSize.first.toInt() / 4 - 10).dp
            )
            SwappableDots( mantraRecommendedIndex, Modifier)


            Text("प्रमुख मंत्र", fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp))
            val kalakarRecommendedIndex = remember { mutableStateOf(0) }
            val kalakarLastRecommendedSwipeTime = remember { mutableStateOf(0L) }

            BhagwanSwappableBox(
                kalakarRecommendedIndex,
                kalakarLastRecommendedSwipeTime, onClick = { name ->
//                    onBhajanClick(name,true)
                },
                height = 80.dp,
                width = (screenSize.first.toInt() / 4 - 10).dp
            )
            SwappableDots( kalakarRecommendedIndex, Modifier)


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
