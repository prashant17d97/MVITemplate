package com.prashant.mvi.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement

object NetworkExtensionFunction {
    inline fun <reified Generic> jsonElementToData(
        responseData: JsonElement?,
        response: (Generic) -> Unit
    ) {
        try {
            response(GsonBuilder().create().fromJson(responseData, Generic::class.java))
        } catch (e: java.lang.IllegalStateException) {
            Log.e("ContextExtension", "jsonStringToData Error: ${e.message}")
        }
    }

    inline fun <reified Generic> jsonStringToData(
        responseData: String?,
        response: (Generic) -> Unit
    ) {
        try {
            response(Gson().fromJson(responseData, Generic::class.java))
        } catch (e: java.lang.IllegalStateException) {
            Log.e("ContextExtension", "jsonStringToData Error: ${e.message}")
        }
    }

    inline fun <reified Generic> jsonArrayToData(
        responseData: JsonElement?,
        response: (List<Generic>) -> Unit
    ) {
        try {
            response(Gson().fromJson(responseData, Array<Generic>::class.java).toList())
        } catch (e: java.lang.IllegalStateException) {
            Log.e("ContextExtension", "jsonStringToData Error: ${e.message}")
        }
    }


    fun Context.isNetworkAvailable(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.getNetworkCapabilities(
            connectivityManager.activeNetwork ?: return false
        ) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}