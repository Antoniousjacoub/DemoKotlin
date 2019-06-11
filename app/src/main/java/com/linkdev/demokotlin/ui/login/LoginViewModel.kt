package com.linkdev.demokotlin.ui.login

import android.app.Application
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.linkdev.demokotlin.ui.base.BaseViewModel

class LoginViewModel(application: Application?) : BaseViewModel(application) {

    fun buildSignInClient(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(getApplication<Application>().applicationContext, gso)
    }
}
