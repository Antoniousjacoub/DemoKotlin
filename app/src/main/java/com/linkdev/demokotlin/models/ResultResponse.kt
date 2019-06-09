package com.linkdev.demokotlin.models

import com.linkdev.demokotlin.R


sealed class ResultResponse<T>(
    val data: T? = null,
    val codeStatus: StatusCode? = StatusCode.UNKNOWN,
    val message: Int? = R.string.somthing_went_wrong) {

    class Success<T>(data: T?, codeStatus: StatusCode) : ResultResponse<T>(data, codeStatus)
    class Loading<T>(data: T? = null) : ResultResponse<T>(data)
    class Error<T>(message: Int, codeStatus: StatusCode?, data: T? = null) :
        ResultResponse<T>(data, codeStatus, message)
}