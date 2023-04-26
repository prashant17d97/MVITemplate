package com.prashant.mvi.ui.home

sealed class HomeIntent {
    object Increment : HomeIntent()
    object Decrement : HomeIntent()
    object DoNothing : HomeIntent()
}