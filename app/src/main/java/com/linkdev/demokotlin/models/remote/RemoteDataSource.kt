package com.linkdev.demokotlin.models.remote

import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.models.ResultResponse
import com.linkdev.demokotlin.models.StatusCode
import com.linkdev.demokotlin.models.dto.NewsFeedResponse
import com.linkdev.demokotlin.retrofit.NetworkingModule
import com.linkdev.demokotlin.retrofit.ServicesInterface


class RemoteDataSource : IRemoteDataSource {
    private var servicesInterface: ServicesInterface = NetworkingModule.SERVICES_INTERFACE

    override suspend fun getNews(source: String, key: String): ResultResponse<NewsFeedResponse> {
        val response: NewsFeedResponse
        return try {
            response = servicesInterface.getNews(source, key).await()
            ResultResponse.Success(response, StatusCode.SUCCESS)
        } catch (e: Exception) {
            ResultResponse.Error(R.string.error_communicating_with_server, StatusCode.ERROR)
        }

    }

}
