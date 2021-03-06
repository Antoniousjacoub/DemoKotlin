package com.linkdev.demokotlin.ui.news

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.common.helpers.SnackbarHelper
import com.linkdev.demokotlin.models.news.Article
import com.linkdev.demokotlin.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*


class NewsFragment : BaseFragment(), NewsFeedAdapter.OnAdapterNewsInteraction {
    private var newsViewModel: NewsViewModel? = null
    private lateinit var mContext: Context
    private var onAdapterNewsInteraction: NewsFeedAdapter.OnAdapterNewsInteraction? = null

    companion object {
        const val TAG: String = "NewsFragment"
        fun create(): NewsFragment {
            return NewsFragment()
        }

    }


    override fun onItemClicked(article: Article?) {
        if (context == null) return
        onAdapterNewsInteraction?.onItemClicked(article)
    }

    override fun initializeViews(v: View) {

    }

    override fun layoutViewId(): Int {
        return R.layout.fragment_main
    }


    override fun setListeners() {
        swipe_refresh_layout.setOnRefreshListener { newsViewModel?.onRequestNews() }
    }

    override fun setObservers() {
        newsViewModel?.getSuccessObserver()?.observe(this, newOnSuccessObserver)
        newsViewModel?.getErrorObserver()?.observe(this, onErrorObserver)
        newsViewModel?.getLoadingObserver()?.observe(this, loadingObserver)
    }

    override fun showProgress(shouldShow: Boolean) {
        swipe_refresh_layout.isRefreshing = false
        if (shouldShow) {
            load_view.visibility = View.VISIBLE
        } else {
            load_view.visibility = View.GONE
        }
    }

    override fun onViewReady(context: Context) {
        mContext = context

    }

    override fun onStart() {
        super.onStart()
        initViewModel()
        newsViewModel?.onRequestNews()
        setObservers()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            onAdapterNewsInteraction = context as NewsFeedAdapter.OnAdapterNewsInteraction?
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun initViewModel() {
        val newsViewModelFactory = NewsViewModelFactory(activity!!.application)
        newsViewModel = ViewModelProviders.of(this, newsViewModelFactory).get(NewsViewModel::class.java)

    }

    private var newOnSuccessObserver = Observer<List<Article>> {
        swipe_refresh_layout.isRefreshing = false
        rv_news_feed.layoutManager = LinearLayoutManager(mContext)
        rv_news_feed.adapter = NewsFeedAdapter(mContext,it, this)
    }
    private var onErrorObserver = Observer<Int> {
        if (context != null && it != null && view != null) {
            SnackbarHelper.showErrorMessage(mContext, view!!, it)
        }
    }
    private var loadingObserver = Observer<Boolean> {
        if (it != null)
            showProgress(it)
    }
}
