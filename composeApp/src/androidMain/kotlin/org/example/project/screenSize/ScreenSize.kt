package org.example.project.screenSize

import android.content.Context
import org.example.project.sanatanApp.presentation.components.PlatformConfiguration

class ScreenSize(private val context: Context): PlatformConfiguration {
    override fun screenWidth(): Float {
        val density = context.resources.displayMetrics.density
        return context.resources.displayMetrics.widthPixels/density
    }

    override fun screenHeight(): Float {
        val density = context.resources.displayMetrics.density
        return context.resources.displayMetrics.heightPixels/density
    }
}