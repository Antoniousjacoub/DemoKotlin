package com.linkdev.demokotlin.models

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by antonio on 1/16/19.
 */

class Article : Parcelable {
    @SerializedName("author")
    @Expose
    var author: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("urlToImage")
    @Expose
    var urlToImage: String? = null
    @SerializedName("publishedAt")
    @Expose
    var publishedAt: String? = null

    protected constructor(`in`: Parcel) {
        this.author = `in`.readValue(String::class.java.classLoader) as String
        this.title = `in`.readValue(String::class.java.classLoader) as String
        this.description = `in`.readValue(String::class.java.classLoader) as String
        this.url = `in`.readValue(String::class.java.classLoader) as String
        this.urlToImage = `in`.readValue(String::class.java.classLoader) as String
        this.publishedAt = `in`.readValue(String::class.java.classLoader) as String
    }

    constructor() {}

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(author)
        dest.writeValue(title)
        dest.writeValue(description)
        dest.writeValue(url)
        dest.writeValue(urlToImage)
        dest.writeValue(publishedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {

        @SuppressLint("ParcelCreator")
        val CREATOR: Parcelable.Creator<Article> = object : Parcelable.Creator<Article> {


            override fun createFromParcel(`in`: Parcel): Article {
                return Article(`in`)
            }

            override fun newArray(size: Int): Array<Article?> {
                return arrayOfNulls(size)
            }

        }
    }

}