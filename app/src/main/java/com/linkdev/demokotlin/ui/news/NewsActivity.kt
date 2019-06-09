package com.linkdev.demokotlin.ui.news

import android.os.Bundle
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.ui.base.BaseActivityForDrawer

class NewsActivity : BaseActivityForDrawer() {



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
            R.id.frmlContainer,
            NewsFragment.create(),
            NewsFragment.TAG
        )
        setupDrawer()
    }
}
