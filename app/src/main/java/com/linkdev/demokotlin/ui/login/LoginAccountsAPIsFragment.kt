package com.linkdev.demokotlin.ui.login

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.ui.base.BaseFragment
import com.linkdev.demokotlin.ui.news.NewsViewModelFactory


abstract class LoginAccountsAPIsFragment : BaseFragment() {
    var loginViewModel: LoginViewModel? = null
    protected abstract fun onSuccussLogin(name: String?, profilePhotoURL: String?, email: String?)

    companion object {
        const val TAG: String = "LoginAccouFragment"
        const val GOOGLE_SIGN_IN: Int = 0

    }

    protected fun initViewModel() {
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            Log.d(TAG, "photoUrl=" + account?.photoUrl)
            onSuccussLogin(account?.displayName, account?.photoUrl?.toString(), account?.email)
        } catch (e: ApiException) {
            loginViewModel?.onSetError(R.string.somthing_went_wrong)
            e.printStackTrace()

        }

    }

    protected fun googleSignIn() {
        val signInIntent = loginViewModel?.buildSignInClient()?.signInIntent
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN)
    }


}