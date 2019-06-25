package com.linkdev.demokotlin.common.helpers

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.widget.ImageView
import com.squareup.picasso.Picasso

object UIUtils {
    fun loadImageWithPicasso(url: String?, imageView: ImageView?, placeholder: Drawable?, errorPlaceholder: Drawable?) {
        if (url == null || placeholder == null || errorPlaceholder == null) return
        if (!TextUtils.isEmpty(url)) {
            Picasso.get()
                .load(url)
                .placeholder(placeholder)
                .error(errorPlaceholder)
                .fit()
                .centerCrop()
                .into(imageView)
        } else {
            imageView?.setImageDrawable(errorPlaceholder)
        }
    }
}