package org.example.project

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.core.presentation.White
import org.example.project.sanatanApp.presentation.screen.mainScrren.MainScreen
import org.example.project.sanatanApp.presentation.screen.mainScrren.MainScreenState
import org.example.project.sanatanApp.presentation.screen.mainScrren.homeScreen.HomeScreen

@Preview( showBackground = true)
@Composable
fun LoginScreenPreview() {
   MainScreen(state = MainScreenState(), onAction = {})
}