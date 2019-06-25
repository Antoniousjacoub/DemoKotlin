package com.linkdev.demokotlin.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.text.TextUtils
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.common.helpers.AppPreferences
import com.linkdev.demokotlin.common.helpers.Constants
import com.linkdev.demokotlin.ui.base.BaseActivity
import com.linkdev.demokotlin.ui.login.LoginActivity
import com.linkdev.demokotlin.ui.news.NewsActivity

class SplashActivity : BaseActivity() {

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, SplashActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun layoutViewId(): Int {
        return R.layout.activity_splash
    }

    override fun initializeViews() {
        displaySplash()

    }

    override fun setListeners() {
    }


    private fun displaySplash() {
        Handler().postDelayed({ this.onSplashFinished() }, Constants.General.SPLASH_DELAY.toLong())
    }

    private fun onSplashFinished() {
        if (TextUtils.equals(AppPreferences.getString(Constants.Keys.NAME, this, ""), "") ||
            TextUtils.equals(AppPreferences.getString(Constants.Keys.NAME, this, ""), null)
        ) {
            LoginActivity.startActivity(this)
        } else {
            NewsActivity.startActivity(this)
        }
        finish()
    }

}
