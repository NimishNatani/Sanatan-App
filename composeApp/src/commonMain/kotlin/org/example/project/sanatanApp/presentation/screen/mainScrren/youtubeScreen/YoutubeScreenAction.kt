package org.example.project.sanatanApp.presentation.screen.mainScrren.youtubeScreen

import org.example.project.sanatanApp.presentation.screen.mainScrren.aartiScreen.AartiScreenAction

interface YoutubeScreenAction {
    data class OnLoadingAartiSubtitles( val aarti:String): YoutubeScreenAction

}