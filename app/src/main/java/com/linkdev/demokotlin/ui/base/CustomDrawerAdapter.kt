package com.linkdev.demokotlin.ui.base

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.models.dto.DrawerItem
import kotlinx.android.synthetic.main.item_drawer_list.view.*

/**
 * Created by antonio on 1/16/19.
 */


class CustomDrawerAdapter(
    private val context: Context,
    private val drawerItemList: List<DrawerItem>,
    private val onItemSideMenuClicked: OnItemSideMenuClicked
) : RecyclerView.Adapter<CustomDrawerAdapter.ViewHolder>() {

    private var lastSelectedSideMenuPosition: Int = 0//make the 0 position the default selected


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_drawer_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dItem = drawerItemList[position]
        holder.bind(dItem)


    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return drawerItemList.size
    }


    interface OnItemSideMenuClicked {
        fun onItemSideMenuClicked(position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageSelectedItem = view.imageSelectedItem
        private val drawerIcon = view.drawerIcon
        private val drawerItemName = view.drawerItemName

        fun bind(drawerItem: DrawerItem) {
            drawerItemName.text = drawerItem.itemName
            drawerIcon.setImageDrawable(ContextCompat.getDrawable(itemView.context, drawerItem.imgResID))
            itemView.setOnClickListener {
                lastSelectedSideMenuPosition = adapterPosition
                onItemSideMenuClicked.onItemSideMenuClicked(adapterPosition)
            }
            if (adapterPosition == lastSelectedSideMenuPosition) {
                imageSelectedItem.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.selected))
            } else {
                imageSelectedItem.setImageDrawable(null)
            }
        }
    }

}
