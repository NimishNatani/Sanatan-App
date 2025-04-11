package org.example.project.screenSize

import kotlinx.cinterop.ExperimentalForeignApi
import org.example.project.sanatanApp.presentation.components.PlatformConfiguration
import platform.CoreGraphics.CGRectGetHeight
import platform.CoreGraphics.CGRectGetWidth
import platform.UIKit.UIScreen


class IOSScreenSize: PlatformConfiguration {
    @OptIn(ExperimentalForeignApi::class)
    override fun screenWidth(): Float {
        return (CGRectGetWidth(UIScreen.mainScreen.bounds)).toFloat()
    }

    @OptIn(ExperimentalForeignApi::class)
    override fun screenHeight(): Float {
        return CGRectGetHeight(UIScreen.mainScreen.bounds).toFloat()
    }
}