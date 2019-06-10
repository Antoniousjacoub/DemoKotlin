package com.linkdev.demokotlin.ui.login

import android.content.Context
import android.view.View
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.ui.base.BaseFragment

class LoginFragment :BaseFragment() {

    companion object {
        const val TAG: String = "LoginFragment"
        fun create(): LoginFragment {
            return LoginFragment()
        }
    }

    override fun onViewReady(context: Context) {
    }

    override fun layoutViewId(): Int {
        return R.layout.fragment_login
    }

    override fun setListeners() {
    }

    override fun setObservers() {
    }

    override fun initializeViews(v: View) {
    }

    override fun showProgress(shouldShow: Boolean) {
    }
}