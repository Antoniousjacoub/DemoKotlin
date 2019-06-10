package com.linkdev.demokotlin.ui.newsDetails

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.os.Bundle
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.common.helpers.Constants.Keys.ARTICLE_KEY
import com.linkdev.demokotlin.common.helpers.Utils
import com.linkdev.demokotlin.models.news.Article
import com.linkdev.demokotlin.ui.base.BaseViewModel

class NewsDetailsViewModel(application: Application?, private val bundle: Bundle?) : BaseViewModel(application) {

    private var articleMutableLiveData: MutableLiveData<Article> = MutableLiveData()
    private var article: Article? = null

    fun requestNewsFeedDetailsData() {
        if (bundle == null) {
            onSetError(R.string.somthing_went_wrong)
            return
        }
        article = bundle.getParcelable(ARTICLE_KEY)
        articleMutableLiveData.postValue(article)

    }

    fun getArticleMutableLiveData(): MutableLiveData<Article> {
        return articleMutableLiveData
    }

    fun openWebsiteOnBrowser() {
        Utils.openWebsiteOnBrowser(
            getApplication<Application>().applicationContext,
            article?.url
        )
    }
}
