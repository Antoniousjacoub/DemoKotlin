package com.linkdev.demokotlin.ui.base

import android.arch.lifecycle.ViewModel
import com.linkdev.demokotlin.models.ResultResponse
import com.linkdev.demokotlin.models.StatusCode
import kotlinx.coroutines.*
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel() {
    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = newSingleThreadContext("xcvxzc")

    protected val scope = CoroutineScope(coroutineContext)

   private fun cancelAllRequests() = coroutineContext.cancel()

    override fun onCleared() {
        cancelAllRequests()
        super.onCleared()
    }

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): T? {
        val response = call.invoke()

        var data: T? = null

        when (isValidResult(response)) {
            StatusCode.SUCCESS -> {
                data = response.body()
                ResultResponse.Success(response)
            }
            StatusCode.ERROR -> {
            }
            StatusCode.SERVER_ERROR -> TODO()
            StatusCode.NO_NETWORK -> TODO()

        }
        return data
    }

    private fun isValidResult(response: Response<out Any>): StatusCode {

        if (!response.isSuccessful) {
            return StatusCode.ERROR
        }
        return StatusCode.SUCCESS
    }

}