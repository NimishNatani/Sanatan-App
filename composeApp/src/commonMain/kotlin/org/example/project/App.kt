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
import org.example.project.sanatanApp.presentation.screen.mainScrren.MainScreenRoot
import org.example.project.sanatanApp.presentation.screen.mainScrren.MainScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.aartiScreen.AartiScreenRoot
import org.example.project.sanatanApp.presentation.screen.mainScrren.aartiScreen.AartiScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenRoot
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.granthScreen.GranthScreenRoot
import org.example.project.sanatanApp.presentation.screen.mainScrren.granthScreen.GranthScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.kathaScreen.KathaListenScreenRoot
import org.example.project.sanatanApp.presentation.screen.mainScrren.kathaScreen.KathaScreenRoot
import org.example.project.sanatanApp.presentation.screen.mainScrren.kathaScreen.KathaScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.mantraScreen.MantraScreenRoot
import org.example.project.sanatanApp.presentation.screen.mainScrren.mantraScreen.MantraScreenViewModel
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
                    SplashScreenRoot(onSplash = {
                        navController.navigate(Route.UserGraph) {
                            navController.popBackStack(Route.UserGraph, true)
                        }
                    })

                }
            }
            navigation<Route.UserGraph>(
                startDestination = Route.MainScreen,
            ) {
                composable<Route.MainScreen> {
                    val viewModel = koinViewModel<MainScreenViewModel>()
                    MainScreenRoot(viewModel = viewModel, onSectionClick = {
                        when (it) {
                            "Aarti" -> navController.navigate(Route.AartiScreen)
                            "Bhajan" -> navController.navigate(Route.BhajanScreen)
                            "Granth" -> navController.navigate(Route.GranthScreen)
                            "Katha" -> navController.navigate(Route.KathaScreen)
                            "Mantra" -> navController.navigate(Route.MantraScreen)
                            "Darshan" -> navController.navigate(Route.DarshanScreen)
                        }
                    })
                }
                composable<Route.AartiScreen> {
                    val viewModel = koinViewModel<AartiScreenViewModel>()
                    AartiScreenRoot(
                        viewModel = viewModel,
                        onBackClick = { navController.popBackStack() })
                }
                composable<Route.BhajanScreen> {
                    val viewModel = koinViewModel<BhajanScreenViewModel>()
                    BhajanScreenRoot(
                        viewModel = viewModel,
                        onBackClick = { navController.popBackStack() })
                }
                composable<Route.GranthScreen> {
                    val viewModel = koinViewModel<GranthScreenViewModel>()
                    GranthScreenRoot(
                        viewModel = viewModel,
                        onBackClick = { navController.popBackStack() })
                }
                composable<Route.KathaScreen> {
                    val viewModel = koinViewModel<KathaScreenViewModel>()
                    KathaScreenRoot(
                        viewModel = viewModel,
                        kathaListen = {navController.navigate(Route.KathaListenScreen)},
                        onBackClick = { navController.popBackStack() })
                }
                composable<Route.MantraScreen> {
                    val viewModel = koinViewModel<MantraScreenViewModel>()
                    MantraScreenRoot(
                        viewModel = viewModel,
                        onBackClick = { navController.popBackStack() })
                }
                composable<Route.KathaListenScreen>{
                    KathaListenScreenRoot()
                }
            }
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