package com.linkdev.demokotlin.retrofit

import com.linkdev.demokotlin.models.dto.NewsFeedResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ServicesInterface {
    @GET("articles")
    fun getNews(@Query("source") source: String, @Query("apiKey") apiKey: String):
            Deferred<NewsFeedResponse>

}