package com.prashant.mvi.network

import com.google.gson.JsonElement
import retrofit2.Response

interface ApiProcessor {
    suspend fun sendRequest(retrofitApi: RetrofitCalls):Response<JsonElement>
}