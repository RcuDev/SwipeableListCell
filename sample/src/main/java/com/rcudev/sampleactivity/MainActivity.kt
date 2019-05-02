/*
 * Developed by Raul Corvo (recu.developer@gmail.com)
 * Copyright (c) 2019. All rights reserved
 */

package com.rcudev.sampleactivity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.rcudev.swipeablelistcell.SwipeableListCell
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SwipeableListCell.OnButtonClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeable_component.setSwipeableCell(R.layout.any_cell)
        swipeable_component.initializeComponent(0, 3, 800, this)
        swipeable_component.setButtonOneValues(android.R.color.holo_blue_dark, "Add", android.R.color.white)
        swipeable_component.setButtonTwoValues(android.R.color.holo_green_dark, "Remove", android.R.color.white)
        swipeable_component.setButtonThreeValues(android.R.color.holo_orange_dark, "Share", android.R.color.white)
    }

    override fun onButtonClickListener(cellIndex: Int, buttonClicked: String) {
        Toast.makeText(this, "Index: $cellIndex - Button clicked: $buttonClicked", Toast.LENGTH_LONG).show()
    }
}
