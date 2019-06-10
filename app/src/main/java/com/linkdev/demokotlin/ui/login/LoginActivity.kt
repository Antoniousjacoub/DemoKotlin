package com.linkdev.demokotlin.ui.login

import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.ui.base.BaseActivity

class LoginActivity : BaseActivity() {
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
