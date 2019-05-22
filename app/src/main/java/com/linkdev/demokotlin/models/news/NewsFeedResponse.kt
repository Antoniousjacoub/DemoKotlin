package com.linkdev.demokotlin.models.news

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by antonio on 1/16/19.
 */

class NewsFeedResponse private constructor(`in`: Parcel) : Parcelable {
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("source")
    @Expose
    var source: String? = null
    @SerializedName("sortBy")
    @Expose
    var sortBy: String? = null
    @SerializedName("articles")
    @Expose
    var articles: List<Article>? = null

    init {
        this.status = `in`.readValue(String::class.java.classLoader) as String
        this.source = `in`.readValue(String::class.java.classLoader) as String
        this.sortBy = `in`.readValue(String::class.java.classLoader) as String
        `in`.readList(this.articles, Article::class.java.classLoader)
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(status)
        dest.writeValue(source)
        dest.writeValue(sortBy)
        dest.writeList(articles)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {

        @SuppressLint("ParcelCreator")
        val CREATOR: Parcelable.Creator<NewsFeedResponse> = object : Parcelable.Creator<NewsFeedResponse> {


            override fun createFromParcel(`in`: Parcel): NewsFeedResponse {
                return NewsFeedResponse(`in`)
            }

            override fun newArray(size: Int): Array<NewsFeedResponse?> {
                return arrayOfNulls(size)
            }

        }
    }

}