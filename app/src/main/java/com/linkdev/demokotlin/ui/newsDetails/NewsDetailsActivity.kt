package com.linkdev.demokotlin.ui.newsDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.common.helpers.Constants.Keys.ARTICLE_KEY
import com.linkdev.demokotlin.models.news.Article
import com.linkdev.demokotlin.ui.base.BaseActivity

class NewsDetailsActivity : BaseActivity() {

    private lateinit var toolbar: Toolbar

    companion object {
        fun startActivity(context: Context, article: Article) {
            val intent = Intent(context, NewsDetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(ARTICLE_KEY, article)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }


    override fun initializeViews() {
        toolbar = findViewById(R.id.toolbar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(
            R.id.frmlContainer,
            NewsDetailsFragment.create(intent?.extras?.getParcelable(ARTICLE_KEY)),
            NewsDetailsFragment.TAG
        )
        setToolbar(toolbar, getString(R.string.newsDetails), true, false)
    }

    override fun setListeners() {
    }

    override fun layoutViewId(): Int {
        return R.layout.activity_toolbar_with_fragment
    }
}