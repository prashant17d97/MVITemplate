package com.prashant.mvi.network

import com.google.gson.JsonElement
import com.prashant.mvi.ui.home.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitCalls {
    @GET(POSTS)
    suspend fun getPosts(): Response<JsonElement>

    @GET(POSTS)
    suspend fun getPostById(
        @Query("id") id: Int
    ): Result<Post>
}