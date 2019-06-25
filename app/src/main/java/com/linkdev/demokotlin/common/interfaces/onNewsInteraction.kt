package com.linkdev.demokotlin.common.interfaces

import com.linkdev.demokotlin.models.news.Article

interface onNewsInteraction {
    fun onItemClicked(article: Article?)
}
