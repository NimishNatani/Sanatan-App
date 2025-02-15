package org.example.project

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.core.presentation.White
import org.example.project.sanatanApp.presentation.screen.mainScrren.MainScreen
import org.example.project.sanatanApp.presentation.screen.mainScrren.MainScreenState
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreen
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenState
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.homeScreen.HomeScreen

@Preview( showBackground = true)
@Composable
fun LoginScreenPreview() {
   val viewModel = viewModel<BhajanScreenViewModel>()
   val state by viewModel.uiState.collectAsStateWithLifecycle()
   BhajanScreen (state = state, onAction = {
      viewModel.onAction(it)
   })
}