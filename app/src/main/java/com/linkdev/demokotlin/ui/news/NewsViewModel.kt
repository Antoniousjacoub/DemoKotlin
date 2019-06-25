package com.linkdev.demokotlin.ui.news

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.linkdev.demokotlin.models.ResultResponse
import com.linkdev.demokotlin.models.StatusCode.SUCCESS
import com.linkdev.demokotlin.models.dto.NewsFeedResponse
import com.linkdev.demokotlin.models.news.Article
import com.linkdev.demokotlin.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : BaseViewModel(application) {
    private val repository: NewsRepository = NewsRepository()
    private var oldArticles: List<Article>? = null
    private val newsLiveData = MutableLiveData<List<Article>>()

    fun getSuccessObserver(): MutableLiveData<List<Article>> {
        return newsLiveData
    }

    fun onRequestNews() {
        if (oldArticles == null) {
            fetchNews()
        } else {
            newsLiveData.postValue(oldArticles)
        }
    }

    private fun fetchNews() {
        scope.launch {
            onSetLoading(true)
            val response = repository.getNewsList(getApplication())
            onSetLoading(false)
            onHandleResponse(response)
        }
    }

    private fun onHandleResponse(response: ResultResponse<NewsFeedResponse>) {
        if (validateResponse(response) == SUCCESS) {
            oldArticles = response.data?.articles
            newsLiveData.postValue(response.data?.articles)
        }
    }


}
