package com.linkdev.demokotlin.ui.login

import android.arch.lifecycle.Observer
import android.content.Context
import android.view.View
import com.facebook.FacebookSdk
import com.google.android.gms.common.SignInButton
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.common.helpers.AppPreferences
import com.linkdev.demokotlin.common.helpers.Constants
import com.linkdev.demokotlin.common.helpers.SnackbarHelper
import com.linkdev.demokotlin.ui.news.NewsActivity

class LoginFragment : LoginHandlerFragment(), View.OnClickListener {
    private var signInButton: SignInButton? = null

    companion object {
        const val TAG: String = "LoginFragment"
        fun create(): LoginFragment {
            return LoginFragment()
        }
    }

    override fun onSuccessLogin(name: String?, profilePhotoURL: String?, email: String?) {
        if (context != null) {
            AppPreferences.setString(Constants.Keys.NAME, name, context!!)
            AppPreferences.setString(Constants.Keys.PHOTO_URL, profilePhotoURL, context!!)
            AppPreferences.setString(Constants.Keys.EMAIL, email, context!!)
            NewsActivity.startActivity(context!!)
            activity?.finish()
        }
    }

    override fun onViewReady(context: Context) {
        initViewModel()
        setObservers()
    }

    override fun setListeners() {
        signInButton?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnLoginWithGoogle -> googleSignIn()
        }
    }

    override fun onResume() {
        super.onResume()
        facebookSignIn()
    }

    override fun layoutViewId(): Int {
        if (context != null) {
            FacebookSdk.sdkInitialize(context)
        }
        return R.layout.fragment_login
    }

    override fun setObservers() {
        loginViewModel?.getErrorObserver()?.observe(this, onErrorObserver)
    }

    override fun initializeViews(v: View) {
        signInButton = v.findViewById(R.id.btnLoginWithGoogle)
        signInButton?.setSize(SignInButton.SIZE_STANDARD)

    }

    private var onErrorObserver = Observer<Int> {
        if (context != null && it != null && view != null) {
            SnackbarHelper.showErrorMessage(context!!, view!!, it)
        }
    }

    override fun showProgress(shouldShow: Boolean) {

    }


}
