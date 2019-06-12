package com.linkdev.demokotlin.ui.splash

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.common.helpers.AppPreferences
import com.linkdev.demokotlin.common.helpers.Constants
import com.linkdev.demokotlin.ui.login.LoginActivity
import com.linkdev.demokotlin.ui.news.NewsActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        displaySplash()
    }

    private fun displaySplash() {
        Handler().postDelayed({ this.onSplashFinished() }, Constants.General.SPLASH_DELAY.toLong())
    }

    private fun onSplashFinished() {
        if (AppPreferences.getString(Constants.Keys.NAME, this, "") === "" ||
            AppPreferences.getString(Constants.Keys.NAME, this, "") === null
        ) {
            LoginActivity.startActivity(this)
        } else {
            NewsActivity.startActivity(this)
        }
        finish()
    }

}
