package org.example.project.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.example.project.sanatanApp.presentation.components.PlatformConfiguration
import org.example.project.screenSize.IOSScreenSize
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlinx.cinterop.ExperimentalForeignApi



actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { Darwin.create() }
    }

@OptIn(ExperimentalForeignApi::class)
actual fun getPlatformConfiguration(): Module = module {
    single<PlatformConfiguration> { IOSScreenSize() }
}


