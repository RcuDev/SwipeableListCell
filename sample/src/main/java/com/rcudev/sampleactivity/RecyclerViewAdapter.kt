/*
 * Developed by Raul Corvo (recu.developer@gmail.com)
 * Copyright (c) 2019. All rights reserved
 */

package com.rcudev.sampleactivity

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rcudev.swipeablelistcell.SwipeableListCell
import kotlinx.android.synthetic.main.any_cell.view.*
import kotlinx.android.synthetic.main.swipe_list_item.view.*

class RecyclerViewAdapter(
    val context: Context,
    val items: ArrayList<String>,
    val listener: OnClickListener
) : RecyclerView.Adapter<ViewHolder>(), SwipeableListCell.OnButtonClickListener {

    // Inflates the item view
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.swipe_list_item,
                parent,
                false
            )
        )
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val anyCell: View = holder.swipeableListCell.setSwipeableCell(R.layout.any_cell)
        anyCell.item_text.text = items[position]
//        anyCell.setOnClickListener {
//            listener.onCellClickListenr(position)
//        }

        when (items[position]) {
            context.getString(R.string.one_button) -> {
                holder.swipeableListCell.initializeComponent(2, 1, 500, this)
                holder.swipeableListCell.setButtonOneValues(
                    android.R.color.holo_blue_dark,
                    "Add",
                    android.R.color.white
                )
            }
            context.getString(R.string.two_button) -> {
                holder.swipeableListCell.initializeComponent(2, 2, 500, this)
                holder.swipeableListCell.setButtonOneValues(
                    android.R.color.holo_blue_dark,
                    "Add",
                    android.R.color.white
                )
                holder.swipeableListCell.setButtonTwoValues(
                    android.R.color.holo_green_dark,
                    "Remove",
                    android.R.color.white
                )
            }
            context.getString(R.string.three_button) -> {
                holder.swipeableListCell.initializeComponent(2, 3, 500, this)
                holder.swipeableListCell.setButtonOneValues(
                    android.R.color.holo_blue_dark,
                    "Add",
                    android.R.color.white
                )
                holder.swipeableListCell.setButtonTwoValues(
                    android.R.color.holo_green_dark,
                    "Remove",
                    android.R.color.white
                )
                holder.swipeableListCell.setButtonThreeValues(
                    android.R.color.holo_orange_dark,
                    "Share",
                    android.R.color.white
                )
            }
        }
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onButtonClickListener(cellIndex: Int, buttonClicked: String) {
        listener.onButtonClickListener(cellIndex, buttonClicked)
    }

    interface OnClickListener {
        fun onCellClickListenr(cellIndex: Int)
        fun onButtonClickListener(cellIndex: Int, buttonClicked: String)
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val swipeableListCell = view.swipeable_list_item
}