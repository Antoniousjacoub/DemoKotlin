package com.linkdev.demokotlin.models.dto

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.linkdev.demokotlin.models.news.Article

class NewsFeedResponse private constructor(parcel: Parcel) : Parcelable {
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
        this.status = parcel.readValue(String::class.java.classLoader) as String
        this.source = parcel.readValue(String::class.java.classLoader) as String
        this.sortBy = parcel.readValue(String::class.java.classLoader) as String
        parcel.readList(this.articles, Article::class.java.classLoader)
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