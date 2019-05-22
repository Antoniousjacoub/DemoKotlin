package com.linkdev.demokotlin.ui.main

import com.linkdev.demokotlin.BuildConfig
import com.linkdev.demokotlin.common.helpers.Constants
import com.linkdev.demokotlin.models.news.NewsFeedResponse
import com.linkdev.demokotlin.retrofit.TmdbApi
import com.linkdev.demokotlin.ui.base.BaseRepository
import kotlinx.coroutines.Deferred
import retrofit2.Response

class MovieRepository(private val api: TmdbApi) : BaseRepository() {

    fun getPopularMovies(): Deferred<Response<NewsFeedResponse>> {

//        val movieResponse = safeApiCall(call = { api.getPopularMovie(Constants.SOURCE, BuildConfig.API_KEY).await() })
//
//        return movieResponse?.articles?.toMutableList()
        return api.getPopularMovie(Constants.SOURCE, BuildConfig.API_KEY)
    }

}