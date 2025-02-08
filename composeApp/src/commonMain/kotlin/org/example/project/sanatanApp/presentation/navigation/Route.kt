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
}