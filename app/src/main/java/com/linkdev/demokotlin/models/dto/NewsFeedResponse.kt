package com.linkdev.demokotlin.models.dto

import com.linkdev.demokotlin.models.news.Article

data class NewsFeedResponse(
    var status: String? = null,
    var source: String? = null,
    var sortBy: String? = null,
    var articles: List<Article>? = null

)