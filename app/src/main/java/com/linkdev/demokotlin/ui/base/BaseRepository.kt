package com.linkdev.demokotlin.ui.base

import android.util.Log
import com.linkdev.demokotlin.common.ResultResponse
import retrofit2.Response
import java.io.IOException

open class BaseRepository{

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result : ResultResponse<T> = safeApiResult(call,errorMessage)
        var data : T? = null

        when(result) {
            is ResultResponse.Success ->
                data = result.data
            is ResultResponse.Error -> {
                Log.d("1.DataRepository", "$errorMessage & Exception - ${result.exception}")
            }
        }


        return data

    }

    private suspend fun <T: Any> safeApiResult(call: suspend ()-> Response<T>, errorMessage: String) : ResultResponse<T>{
        val response = call.invoke()
        if(response.isSuccessful) return ResultResponse.Success(response.body()!!)

        return ResultResponse.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }
}
