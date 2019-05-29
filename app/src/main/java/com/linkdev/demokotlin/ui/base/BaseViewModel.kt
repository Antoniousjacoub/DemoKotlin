package com.linkdev.demokotlin.ui.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.models.ResultResponse
import com.linkdev.demokotlin.models.StatusCode
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class BaseViewModel(application: Application) : AndroidViewModel(application) {


    private val onErrorAction: MutableLiveData<Int> = MutableLiveData()
    private var showLoading: MutableLiveData<Boolean> = MutableLiveData()

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.IO

    protected val scope = CoroutineScope(coroutineContext)

    private fun cancelAllRequests() = coroutineContext.cancel()

    override fun onCleared() {
        super.onCleared()
        cancelAllRequests()
    }

     fun onSetError(int: Int) {
        onErrorAction.postValue(int)
    }

    fun getErrorObserver(): MutableLiveData<Int> {
        return onErrorAction
    }

    fun onSetLoading(loading: Boolean) {
        showLoading.postValue(loading)
    }

    fun getLoadingObserver(): MutableLiveData<Boolean> {
        return showLoading
    }

    open fun <T> validateResponse(response: ResultResponse<T>?): StatusCode {
        return when {
            response === null -> {
                onSetError(R.string.somthing_went_wrong)
                StatusCode.ERROR
            }
            response.codeStatus === StatusCode.NO_NETWORK -> {
                onSetError(R.string.noInternetConnection)
                StatusCode.NO_NETWORK
            }
            response.codeStatus === StatusCode.SUCCESS -> StatusCode.SUCCESS

            else -> StatusCode.UNKNOWN
        }

    }
}