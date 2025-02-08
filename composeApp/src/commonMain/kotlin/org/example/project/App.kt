package org.example.project


import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.example.project.sanatanApp.presentation.navigation.Route
import org.example.project.sanatanApp.presentation.screen.logo.SplashScreenRoot
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@Preview
fun App() {
    SharedTransitionLayout {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.AuthGraph
        ) {
            navigation<Route.AuthGraph>(
                startDestination = Route.SplashScreen,

                ) {
                // Splash Screen
                composable<Route.SplashScreen> {
                    SplashScreenRoot {  }

                }
            }
//            navigation<Route.UserGraph>(
//                startDestination = Route.UserHomeScreen,
//            ) {
//
//            }
//            navigation<Route.RestaurantGraph>(
//                startDestination = Route.RestaurantHomeScreen
//            ) {
//
//            }

        }
    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}