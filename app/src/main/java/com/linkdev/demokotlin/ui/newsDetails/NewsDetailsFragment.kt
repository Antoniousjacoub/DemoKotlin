package com.linkdev.demokotlin.ui.newsDetails

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.View
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.common.helpers.Constants
import com.linkdev.demokotlin.common.helpers.StringValidator
import com.linkdev.demokotlin.common.helpers.UIUtils
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
        btn_open_website.setOnClickListener { newsDetailsViewModel?.openWebsiteOnBrowser() }
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
        tv_author.text = StringValidator.validString(article?.author)
        tv_news_feed_title.text = StringValidator.validString(article?.title)
        tv_news_details_desc.text = StringValidator.validString(article?.description)
        tv_date_published.text = Utils.parseDate(
            article?.publishedAt,
            Constants.Date.inputPattern,
            Constants.Date.outputPattern
        )
        UIUtils.loadImageWithPicasso(
            article?.urlToImage, img_news_feed_details, context?.getDrawable(R.drawable.placeholder),
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