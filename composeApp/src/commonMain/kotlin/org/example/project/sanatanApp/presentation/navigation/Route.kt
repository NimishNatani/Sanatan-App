package org.example.project.sanatanApp.presentation.navigation


import kotlinx.serialization.Serializable


sealed interface Route {

    @Serializable
    data object AuthGraph:Route

    @Serializable
    data object UserGraph : Route

    @Serializable
    data object RestaurantGraph : Route

    @Serializable
    data object SplashScreen : Route

    @Serializable
    data object MainScreen : Route

    @Serializable
    data object AartiBhagwanScreen :Route

    @Serializable
    data object AartiScreen :Route

    @Serializable
    data object BhajanBhagwanScreen :Route

    @Serializable
    data object BhajanScreen :Route

    @Serializable
    data object GranthScreen :Route

    @Serializable
    data object KathaScreen :Route

    @Serializable
    data object MantraBhagwanScreen :Route

    @Serializable
    data object MantraScreen :Route

    @Serializable
    data object DarshanScreen :Route

    @Serializable
    data object KathaBhagwanScreen :Route

    @Serializable
    data object YoutubeScreen :Route

}