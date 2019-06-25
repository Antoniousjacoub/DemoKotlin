package com.linkdev.demokotlin.ui.news

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.common.helpers.UIUtils
import com.linkdev.demokotlin.common.helpers.Utils
import com.linkdev.demokotlin.common.interfaces.onNewsInteraction
import com.linkdev.demokotlin.models.news.Article
import kotlinx.android.synthetic.main.item_news_feed.view.*

/**
 * Created by antonio on 1/16/19.
 */

class NewsFeedAdapter(
    private val context: Context,
    private val mData: List<Article>?,
    private val onNewsInteraction: onNewsInteraction?
) :
    RecyclerView.Adapter<NewsFeedAdapter.ViewHolder>() {
    private var isAddedFirstTime: Boolean = true

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news_feed, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = mData?.get(position)
        holder.bind(article)

    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }


    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val imgNewsFeed = view.imgNewsFeed
        private val tvNewsFeedTitle = view.tvNewsFeedTitle
        private val tvAuthor = view.tvAuthor
        private val tvPublishDate = view.tvPublishDate

        fun bind(article: Article?) {
            if (Utils.isTablet(context) && adapterPosition == 0 && isAddedFirstTime) {
                isAddedFirstTime = false
                onNewsInteraction?.onItemClicked(article)
            }
            tvAuthor.text = article?.author
            tvNewsFeedTitle.text = article?.title
            tvPublishDate.text = article?.publishedAt
            UIUtils.loadImageWithPicasso(
                article?.urlToImage, imgNewsFeed, context.getDrawable(R.drawable.placeholder),
                context.getDrawable(R.drawable.placeholder)
            )

            itemView.setOnClickListener { onNewsInteraction?.onItemClicked(article) }

        }

    }
}
