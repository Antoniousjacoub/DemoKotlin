package com.linkdev.demokotlin.bottomSheetDemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linkdev.demokotlin.R
import kotlinx.android.synthetic.main.item_bottom_sheet.view.*

class BottomSheetAdapter(
    private val context: Context,
    private val mData: Array<String>?
) : RecyclerView.Adapter<BottomSheetAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_bottom_sheet,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val queryString = mData?.get(position)
        holder.bind(queryString)
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }


    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        fun bind(queryString: String?) {
            itemView.apply {
                tvBottomSheet.text = queryString
            }
        }

    }

}