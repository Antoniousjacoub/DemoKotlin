package com.linkdev.demokotlin.ui.login

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.SignInButton
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.common.helpers.AppPreferences
import com.linkdev.demokotlin.common.helpers.Constants
import com.linkdev.demokotlin.common.helpers.SnackbarHelper
import com.linkdev.demokotlin.models.UserModel
import com.linkdev.demokotlin.ui.base.BaseFragment
import com.linkdev.demokotlin.ui.news.NewsActivity
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.*

class LoginFragment : BaseFragment(), View.OnClickListener {
    var loginViewModel: LoginViewModel? = null

    companion object {
        const val REQUEST_CODE_GOOGLE_SIGN_IN: Int = 0
        const val TAG: String = "LoginFragment"
        fun create(): LoginFragment {
            return LoginFragment()
        }
    }


    override fun onViewReady(context: Context) {
        initViewModel()
        setObservers()
    }

    override fun setListeners() {
        btnLoginWithGoogle?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnLoginWithGoogle -> signInWithGoogle()
        }
    }

    override fun onResume() {
        super.onResume()
        signInWithFacebook()
    }

    override fun layoutViewId(): Int {
        return R.layout.fragment_login
    }

    override fun setObservers() {
        loginViewModel?.getErrorObserver()?.observe(this, onErrorObserver)
        loginViewModel?.onSuccessLoginLiveData?.observe(this, onSuccessObserver)
    }

    override fun initializeViews(v: View) {
        btnLoginWithGoogle?.setSize(SignInButton.SIZE_STANDARD)

    }

    override fun showProgress(shouldShow: Boolean) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        loginViewModel?.loginCallbackManager?.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            loginViewModel?.handleGoogleSignInResult(task)
        }

    }

    private fun initViewModel() {
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

    }

    private fun signInWithFacebook() {
        btnFacebook.setReadPermissions(Arrays.asList("email"))
        btnFacebook.fragment = this
        btnFacebook.registerCallback(
            loginViewModel?.createFacebookCallbackManager(),
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    loginViewModel?.handleFacebookSignInResult(loginResult.accessToken)
                }

                override fun onCancel() {
                    loginViewModel?.onSetError(R.string.failedToLoginFacebook)

                }

                override fun onError(error: FacebookException) {
                    Log.e(TAG, "message>>> " + error.message)
                    Log.e(TAG, "localizedMessage>>> " + error.localizedMessage)
                    loginViewModel?.onSetError(R.string.failedToLoginFacebook)

                }
            })
    }


    private fun signInWithGoogle() {
        val signInIntent = loginViewModel?.buildSignInClient()?.signInIntent
        startActivityForResult(signInIntent, REQUEST_CODE_GOOGLE_SIGN_IN)
    }

    private fun onSuccessLogin(name: String?, profilePhotoURL: String?, email: String?) {
        if (context != null) {
            AppPreferences.setString(Constants.Keys.NAME, name, context!!)
            AppPreferences.setString(Constants.Keys.PHOTO_URL, profilePhotoURL, context!!)
            AppPreferences.setString(Constants.Keys.EMAIL, email, context!!)
            NewsActivity.startActivity(context!!)
            activity?.finish()
        }
    }

    private val onSuccessObserver = Observer<UserModel> {
        onSuccessLogin(it?.name, it?.imageURL, it?.name)
    }
    private val onErrorObserver = Observer<Int> {
        SnackbarHelper.showErrorMessage(context, view, it)
    }
}
