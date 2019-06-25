package com.linkdev.demokotlin.ui.news

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.common.helpers.SnackbarHelper
import com.linkdev.demokotlin.common.interfaces.onNewsInteraction
import com.linkdev.demokotlin.models.news.Article
import com.linkdev.demokotlin.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*


class NewsFragment : BaseFragment(), onNewsInteraction {
    private var newsViewModel: NewsViewModel? = null
    private lateinit var mContext: Context
    private var onNewsInteraction: onNewsInteraction? = null

    companion object {
        const val TAG: String = "NewsFragment"
        fun create(): NewsFragment {
            return NewsFragment()
        }

    }


    override fun onItemClicked(article: Article?) {
        onNewsInteraction?.onItemClicked(article)
    }

    override fun initializeViews(v: View) {

    }

    override fun layoutViewId(): Int {
        return R.layout.fragment_main
    }


    override fun setListeners() {
        swipeRefreshLayout.setOnRefreshListener { newsViewModel?.onRequestNews() }
    }

    override fun setObservers() {
        newsViewModel?.getSuccessObserver()?.observe(this, newOnSuccessObserver)
        newsViewModel?.getErrorObserver()?.observe(this, onErrorObserver)
        newsViewModel?.getLoadingObserver()?.observe(this, loadingObserver)
    }

    override fun showProgress(shouldShow: Boolean) {
        swipeRefreshLayout.isRefreshing = false
        if (shouldShow) {
            loadView.visibility = View.VISIBLE
        } else {
            loadView.visibility = View.GONE
        }
    }

    override fun onViewReady(context: Context) {
        mContext = context
        initViewModel()
        newsViewModel?.onRequestNews()
        setObservers()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            onNewsInteraction = context as onNewsInteraction?
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun initViewModel() {
        val newsViewModelFactory = NewsViewModelFactory(activity!!.application)
        newsViewModel = ViewModelProviders.of(this, newsViewModelFactory).get(NewsViewModel::class.java)

    }

    private var newOnSuccessObserver = Observer<List<Article>> {
        swipeRefreshLayout.isRefreshing = false
        rvNewsFeed.layoutManager = LinearLayoutManager(mContext)
        rvNewsFeed.adapter = NewsFeedAdapter(mContext, it, this)
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
