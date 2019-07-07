package com.linkdev.demokotlin.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.models.network.ResultResponse
import com.linkdev.demokotlin.models.network.StatusCode
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel(application: Application?) : AndroidViewModel(application!!) {

    private var compositeDisposable: CompositeDisposable? = null

    private val onErrorAction: MutableLiveData<Int> = MutableLiveData()
    private var showLoading: MutableLiveData<Boolean> = MutableLiveData()

    protected fun <T> validateResponse(response: ResultResponse<T>?): StatusCode {
        return when {
            response == null -> {
                onSetError(R.string.somthing_went_wrong)
                StatusCode.ERROR
            }
            response.codeStatus == StatusCode.NO_NETWORK -> {
                onSetError(R.string.noInternetConnection)
                StatusCode.NO_NETWORK
            }
            response.codeStatus == StatusCode.SUCCESS -> StatusCode.SUCCESS

            else -> StatusCode.UNKNOWN
        }

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

    fun add(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(disposable)
    }

    override fun onCleared() {
        dispose()
        super.onCleared()
    }

    private fun dispose() {
        compositeDisposable?.dispose()
        compositeDisposable = null
    }
}
