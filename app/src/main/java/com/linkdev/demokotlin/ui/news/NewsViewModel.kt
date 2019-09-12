package com.linkdev.demokotlin.ui.news

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.linkdev.demokotlin.models.dto.NewsFeedResponse
import com.linkdev.demokotlin.models.network.ResultResponse
import com.linkdev.demokotlin.models.network.StatusCode.SUCCESS
import com.linkdev.demokotlin.models.news.Article
import com.linkdev.demokotlin.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers

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
        onSetLoading(true)
        add(repository.getNewsList(getApplication())
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
            .doOnSuccess { onSetLoading(false) }
            .doOnError { onSetLoading(false) }
            .subscribe { resultResponse -> onHandleResponse(resultResponse) }
        )
    }

    private fun onHandleResponse(response: ResultResponse<NewsFeedResponse>) {
        if (validateResponse(response) == SUCCESS) {
            oldArticles = response.data?.articles
            newsLiveData.postValue(response.data?.articles)
        }
    }


}
