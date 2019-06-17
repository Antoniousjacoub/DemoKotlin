package com.linkdev.demokotlin.ui.login

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.facebook.AccessToken
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*
import org.json.JSONException
import java.util.*


abstract class LoginHandlerFragment : BaseFragment() {
    var loginViewModel: LoginViewModel? = null

    protected abstract fun onSuccessLogin(name: String?, profilePhotoURL: String?, email: String?)

    companion object {
        const val TAG: String = "LoginAccouFragment"
        const val GOOGLE_SIGN_IN: Int = 0
        const val TWITTER_SIGN_IN: Int = 1

    }

    protected fun initViewModel() {
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        loginViewModel?.loginCallbackManager?.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }

    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            Log.d(TAG, "photoUrl=" + account?.photoUrl)
            onSuccessLogin(account?.displayName, account?.photoUrl?.toString(), account?.email)
        } catch (e: ApiException) {
            loginViewModel?.onSetError(R.string.somthing_went_wrong)
            e.printStackTrace()

        }

    }

    protected fun facebookSignIn() {
        btnFacebook.setReadPermissions(Arrays.asList("email"))
        btnFacebook.fragment = this
        btnFacebook.registerCallback(
            loginViewModel?.createFacebookCallbackManager(),
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    useLoginInformation(loginResult.accessToken)
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

    private fun useLoginInformation(accessToken: AccessToken) {
        val request = GraphRequest.newMeRequest(accessToken) { jsonObject, _ ->
            try {
                val name = jsonObject.getString("name")
                val email = jsonObject.getString("email")
                val image = jsonObject.getJSONObject("picture").getJSONObject("data").getString("url")
                onSuccessLogin(name, image, email)
            } catch (e: JSONException) {
                loginViewModel?.onSetError(R.string.somthing_went_wrong)
                e.printStackTrace()
            }
        }
        val parameters = Bundle()
        parameters.putString("fields", "id,name,email,picture.width(200)")
        request.parameters = parameters
        request.executeAsync()
    }

    protected fun googleSignIn() {
        val signInIntent = loginViewModel?.buildSignInClient()?.signInIntent
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN)
    }


}