package com.linkdev.demokotlin.ui.main

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.ui.base.BaseActivity

class MainActivity : BaseActivity() {


    private lateinit var toolbar: Toolbar


    override fun initializeViews() {
        toolbar = findViewById(R.id.toolbar)
    }

    override fun setListeners() {
    }

    override fun layoutViewId(): Int {
        return R.layout.activity_toolbar_with_fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbar(toolbar, getString(R.string.news), false, false)
        addFragment(
            R.id.frmlContainer,
            MainFragment.create(),
            MainFragment().TAG
        )
    }
}
