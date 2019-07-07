package com.linkdev.demokotlin.ui.newsDetails

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.View
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.common.helpers.Constants
import com.linkdev.demokotlin.common.helpers.StringValidator
import com.linkdev.demokotlin.common.helpers.UIUtils.loadImageWithPicasso
import com.linkdev.demokotlin.common.helpers.Utils
import com.linkdev.demokotlin.models.news.Article
import com.linkdev.demokotlin.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_news_details.*
import kotlinx.android.synthetic.main.layout_error_with_retry.*

class NewsDetailsFragment : BaseFragment() {

    private var newsDetailsViewModel: NewsDetailsViewModel? = null

    companion object {
        const val TAG: String = "NewsDetailsFragment"
        fun create(article: Article?): NewsDetailsFragment {
            val newsDetailsFragment = NewsDetailsFragment()
            val bundle = Bundle()
            bundle.putSerializable(Constants.Keys.ARTICLE_KEY, article)
            newsDetailsFragment.arguments = bundle
            return newsDetailsFragment
        }

    }

    override fun layoutViewId(): Int {
        return R.layout.fragment_news_details
    }

    override fun setListeners() {
        btnOpenWebsite.setOnClickListener { newsDetailsViewModel?.openWebsiteOnBrowser() }
    }

    override fun setObservers() {
        newsDetailsViewModel?.getArticleMutableLiveData()?.observe(this, onSuccessNewsDetails)
        newsDetailsViewModel?.getErrorObserver()?.observe(this, onErrorObserver)
    }

    override fun initializeViews(v: View) {
    }

    override fun showProgress(shouldShow: Boolean) {

    }

    override fun onViewReady(context: Context) {
        initViewModel()
        setObservers()
    }

    private fun initViewModel() {
        val newsViewModelFactory = NewsDetailsViewModelFactory(activity?.application, arguments)
        newsDetailsViewModel = ViewModelProviders.of(this, newsViewModelFactory).get(NewsDetailsViewModel::class.java)
        newsDetailsViewModel?.requestNewsFeedDetailsData()
    }

    private fun onSetDataOnView(article: Article?) {
        tvAuthor.text = StringValidator.validString(article?.author)
        tvNewsFeedTitle.text = StringValidator.validString(article?.title)
        tvNewsDetailsDesc.text = StringValidator.validString(article?.description)
        tvDatePublished.text = Utils.parseDate(
            article?.publishedAt,
            Constants.Date.inputPattern,
            Constants.Date.outputPattern
        )
        loadImageWithPicasso(
            article?.urlToImage, imgNewsFeedDetails, context?.getDrawable(R.drawable.placeholder),
            context?.getDrawable(R.drawable.placeholder)
        )
    }

    private fun onFailure(int: Int) {
        containerNewsDetails.visibility = View.GONE
        tvReload.visibility = View.VISIBLE
        tvReload.text = getString(int)
    }

    private var onSuccessNewsDetails = Observer<Article> {
        onSetDataOnView(it)
    }
    private var onErrorObserver = Observer<Int> {
        if (context != null && it != null && view != null) {
            onFailure(it)
        }
    }
}