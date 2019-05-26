package com.linkdev.demokotlin.models.remote

import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.models.ResultResponse
import com.linkdev.demokotlin.models.StatusCode
import com.linkdev.demokotlin.models.news.NewsFeedResponse
import com.linkdev.demokotlin.retrofit.NetworkingModule
import com.linkdev.demokotlin.retrofit.ServicesInterface
import retrofit2.Response


class RemoteDataSource : IRemoteDataSource {
    private var servicesInterface: ServicesInterface = NetworkingModule.SERVICES_INTERFACE

    override suspend fun getNews(source: String, apiKey: String): ResultResponse<NewsFeedResponse> {
        val response: NewsFeedResponse
        return try {
            response = servicesInterface.getNews(source, apiKey).await()
            ResultResponse.Success<NewsFeedResponse>(response, StatusCode.SUCCESS)
        } catch (e: Exception) {
            ResultResponse.Error<NewsFeedResponse>(R.string.error_communicating_with_server, StatusCode.ERROR)
        }

    }

}
