package org.example.project.sanatanApp.presentation.screen.mainScrren.homeScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.project.core.presentation.Gray
import org.example.project.core.presentation.Orange
import org.example.project.core.presentation.TextSize
import org.example.project.core.presentation.White
import org.example.project.sanatanApp.presentation.components.OrangeText
import org.example.project.sanatanApp.presentation.components.SwappableBox
import org.example.project.sanatanApp.presentation.components.SwappableDots
import org.example.project.sanatanApp.presentation.components.TopBar
import org.example.project.sanatanApp.presentation.components.swipeGesture
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenRoot
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.kathaScreen.KathaScreenRoot
import org.example.project.sanatanApp.presentation.screen.mainScrren.kathaScreen.KathaScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.mantraScreen.MantraScreenRoot
import org.example.project.sanatanApp.presentation.screen.mainScrren.mantraScreen.MantraScreenViewModel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import sanatanapp.composeapp.generated.resources.Res
import sanatanapp.composeapp.generated.resources.aarti
import sanatanapp.composeapp.generated.resources.ohm

@Composable
fun HomeScreenRoot(viewModel: HomeScreenViewModel = koinViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    when (state.screenState) {
        "Home" -> {
            HomeScreen(state = state, onAction = {
                viewModel.onAction(it)
            }, onSectionClick = { })
        }

        "Bhajan" -> {
            val bhajanViewModel = koinViewModel<BhajanScreenViewModel>()
            BhajanScreenRoot(bhajanViewModel, onScreenStateChange = {
                viewModel.onAction(HomeScreenAction.OnScreenStateChange("Home"))
            })
        }

        "Mantra" -> {
            val mantraScreenViewModel = koinViewModel<MantraScreenViewModel>()
            MantraScreenRoot(
                mantraScreenViewModel,
                onScreenStateChange = {
                    viewModel.onAction(HomeScreenAction.OnScreenStateChange("Home"))
                }
            )
        }

        "Katha" -> {
            val kathaScreenViewModel = koinViewModel<KathaScreenViewModel>()
            KathaScreenRoot(
                kathaScreenViewModel,
                onScreenStateChange = {
                    viewModel.onAction(HomeScreenAction.OnScreenStateChange("Home"))
                }
            )
        }
    }

}

@Composable
fun HomeScreen(
    state: HomeScreenState = HomeScreenState(),
    onAction: (HomeScreenAction) -> Unit,
    onSectionClick: (String) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize().background(Gray).verticalScroll(rememberScrollState())
            .padding(bottom = 85.dp)
    ) {
        TopBar(state.searchQuery, onSearchQueryChange = {
            onAction(HomeScreenAction.OnSearchQueryChange(it))
        }, profile = true)
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()).padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            repeat(8) {
                Image(
                    painter = painterResource(Res.drawable.aarti),
                    contentDescription = "profile",
                    modifier = Modifier.clip(CircleShape).size(60.dp).border(
                        1.dp,
                        Orange, CircleShape
                    ),
                    contentScale = ContentScale.Fit
                )
            }
        }

        val selectedIndex = remember { mutableStateOf(0) }
        val totalItems = 4
        val lastSwipeTime = remember { mutableStateOf(0L) }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                modifier = Modifier.fillMaxWidth().height(170.dp)
                    .padding(vertical = 8.dp, horizontal = 10.dp).background(
                        Gray
                    ).clip(RoundedCornerShape(8.dp))
                    .border(BorderStroke((0.5).dp, Orange), shape = RoundedCornerShape(8.dp))
                    .align(Alignment.CenterHorizontally)
                    .swipeGesture(selectedIndex, totalItems, lastSwipeTime),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {

            }
            SwappableDots(totalItems, selectedIndex, Modifier)
        }

        Spacer(modifier = Modifier.height(15.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                MenuItem("आरती सुनें", Res.drawable.aarti, onClick = { onSectionClick("Aarti") })
                MenuItem(
                    "भजन सुनें",
                    Res.drawable.aarti,
                    onClick = { onAction(HomeScreenAction.OnScreenStateChange("Bhajan")) })
                MenuItem("ग्रंथ पढ़ें", Res.drawable.aarti, onClick = { onSectionClick("Granth") })
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                MenuItem(
                    "कथा सुनें",
                    Res.drawable.ohm,
                    onClick = { onAction(HomeScreenAction.OnScreenStateChange("Katha")) })
                MenuItem(
                    "मंत्र सुनें",
                    Res.drawable.ohm,
                    onClick = { onAction(HomeScreenAction.OnScreenStateChange("Mantra")) })
                MenuItem("दर्शन करें", Res.drawable.ohm, onClick = { onSectionClick("Darshan") })
            }
        }

        Text("आपके लिए", fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp, start = 20.dp))

        // "आपके लिए" Circular Swipable Box with Two Items Visible
        val recommendedIndex = remember { mutableStateOf(0) }
        val recommendedItems = listOf("आपके लिए 1", "आपके लिए 2", "आपके लिए 3", "आपके लिए 4")
        val lastRecommendedSwipeTime = remember { mutableStateOf(0L) }

        SwappableBox(
            recommendedIndex,
            recommendedItems,
            lastRecommendedSwipeTime,
            2,
            120.dp,
            160.dp,
        )
        SwappableDots(recommendedItems.size, recommendedIndex, Modifier)
    }
}


@Composable
fun MenuItem(text: String, iconRes: DrawableResource, onClick: () -> Unit) {
    Card(
        modifier = Modifier.size(110.dp).clip(RoundedCornerShape(8.dp))
            .padding(4.dp).clickable { onClick() },
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(White)
    ) {
        Image(
            painter = painterResource(iconRes),
            contentDescription = text,
            modifier = Modifier.size(60.dp).fillMaxWidth().align(Alignment.CenterHorizontally),
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.height(4.dp))
        OrangeText(
            text,
            textSize = TextSize.regular,
            fontStyle = FontStyle.Normal,
            modifier = Modifier.align(Alignment.CenterHorizontally).background(White)
        )
    }
}