package com.linkdev.demokotlin.models.remote

import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.models.dto.NewsFeedResponse
import com.linkdev.demokotlin.models.network.ResultResponse
import com.linkdev.demokotlin.models.network.StatusCode
import com.linkdev.demokotlin.retrofit.NetworkingModule
import com.linkdev.demokotlin.retrofit.ServicesInterface
import retrofit2.Response


class RemoteDataSource : IRemoteDataSource {
    private var servicesInterface: ServicesInterface = NetworkingModule.SERVICES_INTERFACE

    override fun getNews(source: String, key: String): ResultResponse<NewsFeedResponse> {
        val response: Response<NewsFeedResponse>
        return try {
            response = servicesInterface.getNews(source, key).execute()
            ResultResponse.Success(response.body(), StatusCode.SUCCESS)
        } catch (e: Exception) {
            ResultResponse.Error(R.string.error_communicating_with_server, StatusCode.ERROR)
        }

    }

}
