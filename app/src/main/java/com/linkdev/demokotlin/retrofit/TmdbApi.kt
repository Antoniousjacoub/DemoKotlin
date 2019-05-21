package com.linkdev.demokotlin.retrofit

import com.linkdev.demokotlin.models.NewsFeedResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {
    @GET("articles")
    fun getPopularMovie(@Query("source") source: String, @Query("apiKey") apiKey: String):
            Deferred<Response<NewsFeedResponse>>

}