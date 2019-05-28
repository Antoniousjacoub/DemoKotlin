package com.linkdev.demokotlin.ui.location

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.ui.base.BaseActivity

class LocationActivity : BaseActivity() {
    private lateinit var toolbar: Toolbar

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, LocationActivity::class.java)
            context.startActivity(intent)
        }
    }


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
        setToolbar(toolbar, getString(R.string.myLocation), true, false)
        addFragment(
            R.id.frmlContainer,
            LocationFragment.newInstance(),
            LocationFragment.TAG
        )
    }
}
