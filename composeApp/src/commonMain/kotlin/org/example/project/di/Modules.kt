package org.example.project.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import org.example.project.core.di.HttpClientFactory
import org.example.project.sanatanApp.data.api.AartiApi
import org.example.project.sanatanApp.data.repository.AartiRepoImpl
import org.example.project.sanatanApp.domain.repository.AartiRepo
import org.example.project.sanatanApp.domain.repository.YoutubeRepo
import org.example.project.sanatanApp.presentation.StorageViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.MainScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.aartiScreen.AartiScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.granthScreen.GranthScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.homeScreen.HomeScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.kathaScreen.KathaScreenViewModel
import org.example.project.sanatanApp.presentation.screen.mainScrren.mantraScreen.MantraScreenViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.example.project.sanatanApp.data.api.YoutubeApi
import org.example.project.sanatanApp.data.repository.YoutubeRepoImpl
import org.example.project.sanatanApp.presentation.screen.mainScrren.youtubeScreen.YoutubeScreenViewModel

expect val platformModule: Module


val appModule = module {
//    single { CIO.create() } // Use CIO engine or any other engine you added

    single<HttpClientEngine> { CIO.create() } // Provide the CIO engine


//    single { HttpClientFactory.create(get()) }
    single { HttpClientFactory().create(get()) } // Pass the HttpClientEngine

    singleOf(::AartiApi)
    singleOf(::YoutubeApi)

    singleOf(::AartiRepoImpl).bind<AartiRepo>()
    singleOf(::YoutubeRepoImpl).bind<YoutubeRepo>()

    viewModelOf(::HomeScreenViewModel)
    viewModelOf(::MainScreenViewModel)
    viewModelOf(::BhajanScreenViewModel)
    viewModelOf(::MantraScreenViewModel)
    viewModelOf(::KathaScreenViewModel)
    viewModelOf(::AartiScreenViewModel)
    viewModelOf(::GranthScreenViewModel)
    viewModelOf(::StorageViewModel)
    viewModelOf(::YoutubeScreenViewModel)
}