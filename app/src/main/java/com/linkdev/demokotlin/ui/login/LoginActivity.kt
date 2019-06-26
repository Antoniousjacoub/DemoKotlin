package com.linkdev.demokotlin.ui.login

import android.content.Context
import android.content.Intent
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.ui.base.BaseActivity

class LoginActivity : BaseActivity() {

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun layoutViewId(): Int {
        return R.layout.activity_with_fragment
    }

    override fun initializeViews() {
        addFragment(
            R.id.frmlContainer,
            LoginFragment.create(),
            LoginFragment.TAG
        )
    }

    override fun setListeners() {

    }
}
