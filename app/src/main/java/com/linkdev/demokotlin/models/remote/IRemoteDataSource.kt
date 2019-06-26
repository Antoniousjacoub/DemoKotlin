package com.linkdev.demokotlin.models.remote


import com.linkdev.demokotlin.models.network.ResultResponse
import com.linkdev.demokotlin.models.dto.NewsFeedResponse

interface IRemoteDataSource {

    suspend fun getNews(source: String, key: String): ResultResponse<NewsFeedResponse>

}
