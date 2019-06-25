package com.linkdev.demokotlin.ui.news

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.common.helpers.Utils
import com.linkdev.demokotlin.common.interfaces.onNewsInteraction
import com.linkdev.demokotlin.models.news.Article
import com.linkdev.demokotlin.ui.base.BaseActivityForDrawer
import com.linkdev.demokotlin.ui.newsDetails.NewsDetailsActivity
import com.linkdev.demokotlin.ui.newsDetails.NewsDetailsFragment

class NewsActivity : BaseActivityForDrawer(), onNewsInteraction {
    companion object {

        fun startActivity(context: Context) {
            val intent = Intent(context, NewsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun initializeViews() {

    }

    override fun setListeners() {
    }

    override fun layoutViewId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(
            R.id.frmlContainerNews,
            NewsFragment.create(),
            NewsFragment.TAG
        )
        setupDrawer()
    }

    override fun onItemClicked(article: Article?) {
        if (Utils.isTablet(this)) {
            replaceFragment(
                R.id.frmlContainerDetails,
                NewsDetailsFragment.create(article),
                NewsDetailsFragment.TAG
            )
        } else {
            if (article != null)
                NewsDetailsActivity.startActivity(this, article)
        }
    }
}
