package org.example.project.sanatanApp.presentation.screen.mainScrren

import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.DrawableResource

sealed interface MainScreenAction {

    data class OnTabSelected(val index: Int, val icon: DrawableResource, val name:String): MainScreenAction

}