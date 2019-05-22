package com.linkdev.demokotlin.ui.main

import android.arch.lifecycle.MutableLiveData
import com.linkdev.demokotlin.models.news.Article
import com.linkdev.demokotlin.retrofit.Apifactory
import com.linkdev.demokotlin.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class NewsViewModel : BaseViewModel() {


    private val repository: MovieRepository = MovieRepository(Apifactory.tmdbApi)


    val popularMoviesLiveData = MutableLiveData<MutableList<Article>>()

    fun fetchMovies() {
        scope.launch {
            val movieResponse = safeApiCall(call = { repository.getPopularMovies().await() })
            popularMoviesLiveData.postValue(movieResponse?.articles?.toMutableList())
        }
    }


}