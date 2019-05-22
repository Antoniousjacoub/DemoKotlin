package com.linkdev.demokotlin.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.models.news.Article
import kotlinx.android.synthetic.main.item_news_feed.view.*

/**
 * Created by antonio on 1/16/19.
 */

class NewsFeedAdapter(val mData: MutableList<Article>?, val onItemNewsClicked: OnItemNewsClicked) :
    RecyclerView.Adapter<NewsFeedAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news_feed, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = mData!![position]
        holder.bind(article)
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }

    interface OnItemNewsClicked {
        fun onItemNewsClicked(article: Article)
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val img_news_feed = view.img_news_feed
        val tv_news_feed_title = view.tv_news_feed_title
        val tv_author = view.tv_author
        val tv_publish_date = view.tv_publish_date

        fun bind(article: Article) {
            tv_author.text = article.author
            tv_news_feed_title.text = article.title
            tv_publish_date.text = article.publishedAt
        }

    }
}
