package org.example.project.sanatanApp.presentation.screen.mainScrren

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import sanatanapp.composeapp.generated.resources.Res
import sanatanapp.composeapp.generated.resources.home

data class MainScreenState (
    var selectedTabIndex: BottomNavItem = BottomNavItem(0, Res.drawable.home,"Home"),
    var callingApi :Int  = 0
)