package com.prashant.mvi.network

sealed class NetworkIntent {
    data class Success<Generic>(val data: Generic) : NetworkIntent()
    data class Error<Generic>(
        val message: String,
        val key: Int=301,
        val data: Generic? = null
    ) : NetworkIntent()

    object Loading : NetworkIntent()
    object Idle : NetworkIntent()
}