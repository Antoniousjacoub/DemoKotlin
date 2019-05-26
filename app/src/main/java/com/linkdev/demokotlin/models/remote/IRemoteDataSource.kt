package com.linkdev.demokotlin.models.remote


import com.linkdev.demokotlin.models.ResultResponse
import com.linkdev.demokotlin.models.news.NewsFeedResponse

interface IRemoteDataSource {

   suspend fun getNews(page: String, pageSize: String): ResultResponse<NewsFeedResponse>

}
