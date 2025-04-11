package org.example.project.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.example.project.sanatanApp.presentation.components.PlatformConfiguration
import org.example.project.screenSize.ScreenSize
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { OkHttp.create() }
    }

actual fun getPlatformConfiguration(): Module = module {
    single<PlatformConfiguration> { ScreenSize(androidContext()) }
}

