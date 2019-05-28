package com.linkdev.demokotlin.ui.main

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.ui.base.BaseActivityForDrawer

class MainActivity : BaseActivityForDrawer() {



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
            MainFragment.create(),
            MainFragment.TAG
        )
        setupDrawer()
    }
}
