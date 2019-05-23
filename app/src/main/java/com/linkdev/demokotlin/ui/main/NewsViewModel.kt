package com.linkdev.demokotlin.ui.main

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.linkdev.demokotlin.models.news.Article
import com.linkdev.demokotlin.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class NewsViewModel(private val application: Application) : BaseViewModel() {
    private val repository: MovieRepository = MovieRepository()

    val newsLiveData = MutableLiveData<List<Article>>()

    fun fetchNews() {
        scope.launch {
            val movieResponse = repository.getNewsList(application.applicationContext)
            newsLiveData.postValue(movieResponse.data?.articles)
        }
    }


}