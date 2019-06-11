package com.linkdev.demokotlin.ui.news

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.common.helpers.Constants
import com.linkdev.demokotlin.ui.base.BaseActivityForDrawer

class NewsActivity : BaseActivityForDrawer() {

    companion object {
        fun startActivity(context: Context, name: String?, photoURL: String?, email: String?) {
            val intent = Intent(context, NewsActivity::class.java)
            val bundle = Bundle()
            bundle.putString(Constants.Keys.NAME, name)
            bundle.putString(Constants.Keys.EMAIL, email)
            bundle.putString(Constants.Keys.PHOTO_URL, photoURL)
            intent.putExtras(bundle)
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
            R.id.frmlContainer,
            NewsFragment.create(),
            NewsFragment.TAG
        )
        setupDrawer()
    }
}
