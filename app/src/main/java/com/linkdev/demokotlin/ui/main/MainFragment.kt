package com.linkdev.demokotlin.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.models.news.Article
import com.linkdev.demokotlin.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment(), NewsFeedAdapter.OnItemNewsClicked {

    val TAG: String = "MainFragment"

    companion object Factorysds {
        fun create(): MainFragment = MainFragment()
    }

    override fun onItemNewsClicked(article: Article) {
    }

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var mContext: Context
    override fun layoutViewId(): Int {
        return R.layout.fragment_main
    }

    override fun initializeViews(v: View) {
    }

    override fun setListeners() {
    }

    override fun setObservers() {
        newsViewModel.newsLiveData.observe(this, newOnSusscesObserver)
    }

    override fun showProgress(shouldShow: Boolean) {
        if (shouldShow) {
            load_view.visibility = View.VISIBLE
        } else {
            load_view.visibility = View.GONE
        }
    }

    override fun onViewReady(context: Context) {
        mContext = context
        initViewModel()
        newsViewModel.fetchNews()
        setObservers()
    }

    private fun initViewModel() {
        val newsViewModelFactory = NewsViewModelFactory(activity?.application)
        newsViewModel = ViewModelProviders.of(this, newsViewModelFactory).get(NewsViewModel::class.java)

    }

    private var newOnSusscesObserver = Observer<List<Article>> {
        Log.d("newOnSusscesObserver", "" + it?.size)
        rv_news_feed.layoutManager = LinearLayoutManager(mContext)
        rv_news_feed.adapter = NewsFeedAdapter(it, this)
    }
}
