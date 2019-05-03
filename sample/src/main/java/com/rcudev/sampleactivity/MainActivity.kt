/*
 * Developed by Raul Corvo (recu.developer@gmail.com)
 * Copyright (c) 2019. All rights reserved
 */

package com.rcudev.sampleactivity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.rcudev.swipeablelistcell.SwipeableListCell
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnClickListener {

    val listItems: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createRecyclerViewList()
        renderRecyclerView()
    }

    override fun onCellClickListenr(cellIndex: Int) {
        Toast.makeText(this, "Cell clicked: $cellIndex", Toast.LENGTH_LONG).show()
    }

    override fun onButtonClickListener(cellIndex: Int, buttonClicked: String) {
        Toast.makeText(
            this,
            "Index: $cellIndex - Button clicked: $buttonClicked",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun createRecyclerViewList() {
        listItems.add(getString(R.string.one_button))
        listItems.add(getString(R.string.two_button))
        listItems.add(getString(R.string.three_button))
    }

    private fun renderRecyclerView() {
        swipeable_list_rv.layoutManager = LinearLayoutManager(this)
        swipeable_list_rv.adapter = RecyclerViewAdapter(this, listItems, this)
    }
}
