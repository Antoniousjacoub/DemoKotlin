package com.linkdev.demokotlin.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.common.helpers.AppPreferences
import com.linkdev.demokotlin.common.helpers.Constants
import com.linkdev.demokotlin.ui.base.BaseActivity
import com.linkdev.demokotlin.ui.login.LoginActivity
import com.linkdev.demokotlin.ui.news.NewsActivity
import kotlin.math.abs
import kotlin.math.absoluteValue

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun displaySplash() {
        Handler().postDelayed({ this.onSplashFinished() }, Constants.General.SPLASH_DELAY.toLong())
    }

    fun diagonalDifference(arr: Array<Array<Int>>): Int {
        var x: Int = 0
        var y: Int = 0
        arr.forEachIndexed { index, element ->
            x += element[element.size - (index + 1)]
            y += element[index + 1]

        }
abs((x-y))
        return (x-y).absoluteValue
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
