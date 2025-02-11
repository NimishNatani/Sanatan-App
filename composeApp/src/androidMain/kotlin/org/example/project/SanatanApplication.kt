package org.example.project

import android.app.Application
import org.example.project.di.appModule
import org.example.project.di.initKoin
import org.example.project.di.platformModule
import org.koin.android.ext.koin.androidContext

class SanatanApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@SanatanApplication)
            modules(listOf(appModule, platformModule))  // Include both shared and platform-specific modules

        }
    }
}