package com.linkdev.demokotlin.models.news

import java.io.Serializable

data class Article(
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null
) : Serializable