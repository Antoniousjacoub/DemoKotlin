package com.linkdev.demokotlin.ui.news

import android.content.Context
import com.linkdev.demokotlin.common.helpers.Constants
import com.linkdev.demokotlin.models.dto.NewsFeedResponse
import com.linkdev.demokotlin.models.network.ResultResponse
import com.linkdev.demokotlin.models.network.StatusCode
import com.linkdev.demokotlin.models.remote.RemoteDataSource
import com.linkdev.demokotlin.ui.base.BaseRepository
import io.reactivex.Single
import java.util.concurrent.Callable


class NewsRepository : BaseRepository() {

    private val api: RemoteDataSource = RemoteDataSource()
    fun getNewsList(context: Context): Single<ResultResponse<NewsFeedResponse>> {
        return if (isConnected(context)) {
            createSingle(Callable {api.getNews(Constants.Network.SOURCE, Constants.Network.API_KEY) })
        } else {
            createSingle(Callable {ResultResponse<NewsFeedResponse>(null, StatusCode.NO_NETWORK)})

        }
    }

}