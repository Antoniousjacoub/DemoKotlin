package com.linkdev.demokotlin.ui.news

import android.content.Context
import com.linkdev.demokotlin.BuildConfig
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.common.helpers.Constants
import com.linkdev.demokotlin.models.ResultResponse
import com.linkdev.demokotlin.models.StatusCode
import com.linkdev.demokotlin.models.dto.NewsFeedResponse
import com.linkdev.demokotlin.models.remote.RemoteDataSource
import com.linkdev.demokotlin.ui.base.BaseRepository

class NewsRepository : BaseRepository() {

    private val api: RemoteDataSource = RemoteDataSource()
    suspend fun getNewsList(context: Context): ResultResponse<NewsFeedResponse> {
        return if (isConnected(context)) {
            api.getNews(Constants.Network.SOURCE, BuildConfig.API_KEY)
        } else {
            ResultResponse.Error(R.string.noInternetConnection, StatusCode.NO_NETWORK)
        }
    }

}