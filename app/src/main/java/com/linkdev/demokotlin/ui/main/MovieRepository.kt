package com.linkdev.demokotlin.ui.main

import com.linkdev.demokotlin.BuildConfig
import com.linkdev.demokotlin.common.helpers.Constants
import com.linkdev.demokotlin.models.Article
import com.linkdev.demokotlin.retrofit.TmdbApi
import com.linkdev.demokotlin.ui.base.BaseRepository

class MovieRepository(private val api: TmdbApi) : BaseRepository() {

    suspend fun getPopularMovies(): MutableList<Article>? {

        val movieResponse = safeApiCall(
            call = { api.getPopularMovie(Constants.SOURCE, BuildConfig.API_KEY).await() },
            errorMessage = "Error Fetching Popular News"
        )

        return movieResponse?.articles?.toMutableList()

    }

}