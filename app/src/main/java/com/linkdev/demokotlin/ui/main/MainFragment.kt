package com.linkdev.demokotlin.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.common.helpers.SnackbarHelper
import com.linkdev.demokotlin.models.news.Article
import com.linkdev.demokotlin.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment() {
    override fun initializeViews(v: View) {

    }

    private var newsViewModel: NewsViewModel? = null
    private lateinit var mContext: Context

    companion object {
        const val TAG: String = "MainFragment"
        fun create(): MainFragment {
            return MainFragment()
        }

    }

    override fun layoutViewId(): Int {
        return R.layout.fragment_main
    }


    override fun setListeners() {
        swipe_refresh_layout.setOnRefreshListener { newsViewModel?.fetchNews() }
    }

    override fun setObservers() {
        newsViewModel?.getSuccessObserver()?.observe(this, newOnSusscesObserver)
        newsViewModel?.getErrorObserver()?.observe(this, onErroeObserver)
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
        initViewModel()
        newsViewModel?.fetchNews()
        setObservers()
    }

    private fun initViewModel() {
        val newsViewModelFactory = NewsViewModelFactory(activity!!.application)
        newsViewModel = ViewModelProviders.of(this, newsViewModelFactory).get(NewsViewModel::class.java)

    }

    private var newOnSusscesObserver = Observer<List<Article>> {
        Log.d("newOnSusscesObserver", "" + it?.size)
        rv_news_feed.layoutManager = LinearLayoutManager(mContext)
        rv_news_feed.adapter = NewsFeedAdapter(it)
    }
    private var onErroeObserver = Observer<Int> {
        Log.d("onErroeObserver", "" + it)
        if (context != null && it != null && view != null) {
            SnackbarHelper.showErrorMessage(context!!, view!!, it)
        }
    }
    private var loadingObserver = Observer<Boolean> {
        Log.d("loadingObserver", "" + it)
        if (it != null)
            showProgress(it)
    }
}
