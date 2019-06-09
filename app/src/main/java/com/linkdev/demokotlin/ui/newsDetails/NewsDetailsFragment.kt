package com.linkdev.demokotlin.ui.newsDetails

import android.content.Context
import android.view.View
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.ui.base.BaseFragment

class NewsDetailsFragment : BaseFragment() {

    companion object {
        const val TAG: String = "NewsDetailsFragment"
        fun create(): NewsDetailsFragment {
            return NewsDetailsFragment()
        }

    }

    override fun layoutViewId(): Int {
        return R.layout.fragment_news_details
    }

    override fun setListeners() {
    }

    override fun setObservers() {
    }

    override fun initializeViews(v: View) {
    }

    override fun showProgress(shouldShow: Boolean) {
    }

    override fun onViewReady(context: Context) {
    }
}