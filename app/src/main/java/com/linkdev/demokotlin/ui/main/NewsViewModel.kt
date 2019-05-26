package com.linkdev.demokotlin.ui.main

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.linkdev.demokotlin.models.ResultResponse
import com.linkdev.demokotlin.models.StatusCode.SUCCESS
import com.linkdev.demokotlin.models.news.Article
import com.linkdev.demokotlin.models.news.NewsFeedResponse
import com.linkdev.demokotlin.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : BaseViewModel(application) {
    private val repository: MovieRepository = MovieRepository()

    private val newsLiveData = MutableLiveData<List<Article>>()

    fun fetchNews() {

        scope.launch {
            onSetLoading(true)
            val response = repository.getNewsList(getApplication())
            onSetLoading(false)
            onHandleResponse(response)
        }
    }

    fun getSuccessObserver(): MutableLiveData<List<Article>> {
        return newsLiveData
    }

    private  fun onHandleResponse(response: ResultResponse<NewsFeedResponse>) {
        when (validateResponse(response)) {
            SUCCESS -> {
                newsLiveData.postValue(response.data?.articles)
            }
        }
    }

}