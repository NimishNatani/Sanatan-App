package org.example.project.sanatanApp.presentation.screen.mainScrren.granthScreen

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
import org.example.project.sanatanApp.presentation.components.SwappableBox
import org.example.project.sanatanApp.presentation.components.SwappableDots
import org.example.project.sanatanApp.presentation.components.TopBar
import org.example.project.sanatanApp.presentation.components.swipeGesture
import org.example.project.sanatanApp.presentation.screen.mainScrren.kathaScreen.KathaScreen
import org.example.project.sanatanApp.presentation.screen.mainScrren.kathaScreen.KathaScreenAction
import org.example.project.sanatanApp.presentation.screen.mainScrren.kathaScreen.KathaScreenState
import org.example.project.sanatanApp.presentation.screen.mainScrren.kathaScreen.KathaScreenViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GranthScreenRoot(viewModel: GranthScreenViewModel = koinViewModel(),
                     onBackClick:()->Unit){
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    GranthScreen(state = state, onAction = {
        viewModel.onAction(it)
    },onBackClick={onBackClick()})
}

@Composable
fun GranthScreen(state: GranthScreenState, onAction: (GranthScreenAction) -> Unit, onBackClick:()->Unit){

//    Column(modifier = Modifier.fillMaxSize().background(Gray).padding(bottom = 85.dp)) {
//        TopBar(state.searchQuery, onSearchQueryChange = {
//            onAction(GranthScreenAction.OnSearchQueryChange(it))
//        },onBackClick = {onBackClick()})
//        Spacer(modifier = Modifier.height(10.dp))
//        Column(
//            modifier = Modifier.fillMaxSize().background(Gray).verticalScroll(rememberScrollState())
//                .padding(horizontal = 10.dp),
//        ) {
//            val selectedIndex = remember { mutableStateOf(0) }
//            val totalItems = 4
//            val lastSwipeTime = remember { mutableStateOf(0L) }
//
//            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                Card(
//                    modifier = Modifier.fillMaxWidth().height(170.dp)
//                        .padding(vertical = 8.dp, horizontal = 10.dp).background(
//                            Gray
//                        ).clip(RoundedCornerShape(8.dp))
//                        .border(BorderStroke((0.5).dp, Orange), shape = RoundedCornerShape(8.dp))
//                        .align(Alignment.CenterHorizontally)
//                        .swipeGesture(selectedIndex, totalItems, lastSwipeTime),
//                    elevation = CardDefaults.cardElevation(8.dp)
//                ) {
//                }
//                SwappableDots(totalItems, selectedIndex, Modifier)
//            }
//
//            Spacer(modifier = Modifier.height(15.dp))
//            Text("ग्रन्थ चुनें", fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp))
//            val mantraRecommendedIndex = remember { mutableStateOf(0) }
//            val mantraRecommendedItems = listOf(
//                listOf("  1", "  2", "  3", " 4", "5", "6", "7"),
//                listOf("  1", "  2", "  3", " 4", "5", "6", "7")
//            )
//            val mantraLastRecommendedSwipeTime = remember { mutableStateOf(0L) }
//
//            SwappableBox(
//                mantraRecommendedIndex,
//                mantraRecommendedItems[0],
//                mantraLastRecommendedSwipeTime, 2, 80.dp, 150.dp
//            )
//            SwappableBox(
//                mantraRecommendedIndex,
//                mantraRecommendedItems[1],
//                mantraLastRecommendedSwipeTime, 2, 120.dp, 80.dp
//            )
//            SwappableDots(mantraRecommendedItems[0].size, mantraRecommendedIndex, Modifier)
//
//
//            Text("आपके लिए", fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp))
//            val recommendedIndex = remember { mutableStateOf(0) }
//            val recommendedItems = listOf("  1", "  2", "  3", " 4", "5", "6", "7")
//            val lastRecommendedSwipeTime = remember { mutableStateOf(0L) }
//
//            SwappableBox(
//                recommendedIndex,
//                recommendedItems,
//                lastRecommendedSwipeTime,
//                2,
//                120.dp,
//                160.dp
//            )
//            SwappableDots(recommendedItems.size, recommendedIndex, Modifier)
//        }
//    }
}