package com.linkdev.demokotlin.ui.base

import android.content.Context
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
        holder.bind(dItem, lastSelectedSideMenuPosition, onItemSideMenuClicked)


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

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val image_selected_item = view.image_selected_item
        private val drawer_icon = view.drawer_icon
        private val drawer_itemName = view.drawer_itemName

        fun bind(
            drawerItem: DrawerItem,
             lastPosition: Int,
            onItemSideMenuClicked: OnItemSideMenuClicked
        ) {
            drawer_itemName.text = drawerItem.itemName
            drawer_icon.setImageDrawable(itemView.context.resources.getDrawable(drawerItem.imgResID))
            itemView.setOnClickListener {
                onItemSideMenuClicked.onItemSideMenuClicked(adapterPosition)
            }
            if (adapterPosition == lastPosition) {
                image_selected_item.setImageDrawable(itemView.context.resources.getDrawable(R.drawable.selected))
            } else {
                image_selected_item.setImageDrawable(null)
            }
        }
    }

}
