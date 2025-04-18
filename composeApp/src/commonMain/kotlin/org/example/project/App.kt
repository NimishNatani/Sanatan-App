package org.example.project


import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.example.project.sanatanApp.presentation.StorageViewModel
import org.example.project.sanatanApp.presentation.navigation.Route
import org.example.project.sanatanApp.presentation.screen.logo.SplashScreenRoot
import org.example.project.sanatanApp.presentation.screen.mainScrren.MainScreenRoot
import org.example.project.sanatanApp.presentation.screen.mainScrren.MainScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.aartiScreen.AartiBhagwanScreenRoot
import org.example.project.sanatanApp.presentation.screen.mainScrren.aartiScreen.AartiScreenRoot
import org.example.project.sanatanApp.presentation.screen.mainScrren.aartiScreen.AartiScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanBhagwanScreenRoot
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenRoot
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.granthScreen.GranthScreenRoot
import org.example.project.sanatanApp.presentation.screen.mainScrren.granthScreen.GranthScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.kathaScreen.KathaListenScreenRoot
import org.example.project.sanatanApp.presentation.screen.mainScrren.kathaScreen.KathaScreenRoot
import org.example.project.sanatanApp.presentation.screen.mainScrren.kathaScreen.KathaScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.mantraScreen.MantraScreenRoot
import org.example.project.sanatanApp.presentation.screen.mainScrren.mantraScreen.MantraScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.youtubeScreen.YoutubeScreenRoot
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
                            "AartiDto" -> navController.navigate(Route.AartiScreen)
                            "Bhajan" -> navController.navigate(Route.BhajanScreen)
                            "Granth" -> navController.navigate(Route.GranthScreen)
                            "Katha" -> navController.navigate(Route.KathaScreen)
                            "Mantra" -> navController.navigate(Route.MantraScreen)
                            "Darshan" -> navController.navigate(Route.DarshanScreen)
                        }
                    })
                }
                composable<Route.AartiBhagwanScreen> {
                    val sharedUserViewModel =
                        it.sharedKoinViewModel<StorageViewModel>(navController)
                    AartiBhagwanScreenRoot(
                        onBackClick = { navController.popBackStack() },
                        onBhagwanClick = { name -> sharedUserViewModel.setBhagwanName(name,false)
                            navController.navigate(Route.AartiScreen)})
                }
                composable<Route.AartiScreen> {
                    val viewModel = koinViewModel<AartiScreenViewModel>()
                    val sharedUserViewModel =
                        it.sharedKoinViewModel<StorageViewModel>(navController)
                    sharedUserViewModel.bhagwanNameState.value?.let { value ->
                        AartiScreenRoot(
                            viewModel = viewModel,
                            name  = value.first,
                            onBackClick = { navController.popBackStack() },
                            onAartiClick = { link -> sharedUserViewModel.setLink(link)
                                navController.navigate(Route.YoutubeScreen)})
                    }

                }
                composable<Route.BhajanBhagwanScreen>{
                    val viewModel = koinViewModel<BhajanScreenViewModel>()
                    val sharedUserViewModel =
                        it.sharedKoinViewModel<StorageViewModel>(navController)
                    BhajanBhagwanScreenRoot(
                        viewModel = viewModel,
                        onBackClick = { navController.popBackStack() },
                        onBhajanClick = { name,isKalakar -> sharedUserViewModel.setBhagwanName(name,isKalakar)
                            navController.navigate(Route.BhajanScreen)})
                }
                composable<Route.BhajanScreen> {
                    val viewModel = koinViewModel<BhajanScreenViewModel>()
                    val sharedUserViewModel =
                        it.sharedKoinViewModel<StorageViewModel>(navController)
                    sharedUserViewModel.bhagwanNameState.value?.let { value ->
                    BhajanScreenRoot(
                        viewModel = viewModel,
                        name = value.first,
                        isKalakar = value.second,
                        onBackClick = { navController.popBackStack() },
                        onBhajanClick = { bhajan -> sharedUserViewModel.setBhajan(bhajan)
                        navController.navigate(Route.YoutubeScreen)}
                        )}
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
                    val sharedUserViewModel =
                        it.sharedKoinViewModel<StorageViewModel>(navController)
                    val viewModel = koinViewModel<MantraScreenViewModel>()
                    MantraScreenRoot(
                        viewModel = viewModel,
                        onBackClick = { navController.popBackStack() },
                        onMantraClick = { mantra, type -> sharedUserViewModel.setMantra(mantra,type)
                        navController.navigate(Route.YoutubeScreen)})
                }
                composable<Route.KathaListenScreen>{
                    KathaListenScreenRoot()
                }
                composable<Route.YoutubeScreen>{
                    val url = mutableStateOf<String?>(null)
                    val sharedUserViewModel =
                        it.sharedKoinViewModel<StorageViewModel>(navController)
                    sharedUserViewModel.listTypeState.value?.let{value->
                        when(value){
                             "Aarti"->{
                                 sharedUserViewModel.aartiState.value?.let {
                                         aarti-> url.value = aarti.aarti.values.firstOrNull()?.link
                                 }
                             }
                            "Bhajan" -> {
                                sharedUserViewModel.bhajanState.value?.let {
                                        bhajan-> url.value = bhajan.bhajan.values.firstOrNull()?.link
                                }
                            }
                            "Mantra" ->{
                                sharedUserViewModel.mantraState.value?.let {
                                        mantra-> when(mantra.second){
                                            1-> {
                                                url.value =
                                                    mantra.first.mantra.getValue("mantra1").link
                                            }
                                            2-> {
                                                url.value =
                                                    mantra.first.mantra.getValue("mantra2").link
                                            }
                                            3-> {
                                                url.value =
                                                    mantra.first.mantra.getValue("mantra3").link
                                            }
                                        }
                                }
                            }
                            else ->{}
                        }
                        url.value?.let {
                            YoutubeScreenRoot(url = url.value)
                        }
                    }
                    sharedUserViewModel.linkState.value?.let {
                        link->
                        YoutubeScreenRoot(
                            url = link,
                        )

                    }
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