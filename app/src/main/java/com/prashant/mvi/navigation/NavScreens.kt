package com.prashant.mvi.navigation


const val HOME = "Home"

sealed class NavScreens(val name: String, val route: String) {
    object Home :NavScreens(name = HOME, route = HOME)
}
