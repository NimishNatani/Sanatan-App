package org.example.project.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import org.example.project.core.di.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.example.project.sanatanApp.presentation.screen.mainScrren.homeScreen.HomeScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.MainScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.kathaScreen.KathaScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.mantraScreen.MantraScreenViewModel

expect val platformModule: Module
//expect fun VideoPlayer(modifier: Modifier, url: String)




val appModule = module {
//    single { CIO.create() } // Use CIO engine or any other engine you added

    single<HttpClientEngine> { CIO.create() } // Provide the CIO engine


//    single { HttpClientFactory.create(get()) }
    single { HttpClientFactory().create(get()) } // Pass the HttpClientEngine

    viewModel { HomeScreenViewModel() }
    viewModel { MainScreenViewModel() }
    viewModel { BhajanScreenViewModel() }
    viewModel { MantraScreenViewModel() }
    viewModel { KathaScreenViewModel() }
}