package com.linkdev.demokotlin.ui.login

import android.app.Application
import androidx.lifecycle.MutableLiveData
import android.os.Bundle
import android.util.Log
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.GraphRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.models.UserModel
import com.linkdev.demokotlin.ui.base.BaseViewModel
import org.json.JSONException

class LoginViewModel(application: Application?) : BaseViewModel(application) {
    private val userModel: UserModel = UserModel()
    var loginCallbackManager: CallbackManager? = null
    var onSuccessLoginLiveData: MutableLiveData<UserModel> = MutableLiveData()

    fun buildSignInClient(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(getApplication<Application>().applicationContext, gso)
    }

    fun createFacebookCallbackManager(): CallbackManager? {
        loginCallbackManager = CallbackManager.Factory.create()
        return loginCallbackManager
    }

    fun handleFacebookSignInResult(accessToken: AccessToken) {
        val request = GraphRequest.newMeRequest(accessToken) { jsonObject, _ ->
            try {
                val name = jsonObject.getString("name")
                val email = jsonObject.getString("email")
                val image = jsonObject.getJSONObject("picture").getJSONObject("data").getString("url")
                userModel.name = name
                userModel.email = email
                userModel.imageURL = image
                onSuccessLoginLiveData.postValue(userModel)
            } catch (e: JSONException) {
                onSetError(R.string.somthing_went_wrong)
                e.printStackTrace()
            }
        }
        val parameters = Bundle()
        parameters.putString("fields", "id,name,email,picture.width(200)")
        request.parameters = parameters
        request.executeAsync()
    }

    fun handleGoogleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            userModel.name = account?.displayName
            userModel.email = account?.email
            userModel.imageURL = account?.photoUrl?.toString()
            onSuccessLoginLiveData.postValue(userModel)
        } catch (e: ApiException) {
            onSetError(R.string.somthing_went_wrong)
            e.printStackTrace()

        }

    }


}
