/*
 * Developed by Raul Corvo (recu.developer@gmail.com)
 * Copyright (c) 2019. All rights reserved
 */

package com.rcudev.swipeablelistcell

import android.content.Context
import android.support.annotation.*
import android.support.annotation.IntRange
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.swipeable_list_cell.view.*

class SwipeableListCell @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val NUM_BUTTONS_ONE: Int = 1
    private val NUM_BUTTONS_TWO: Int = 2
    private val NUM_BUTTONS_THREE: Int = 3

    private var mButtonClickListener: OnButtonClickListener? = null
    private var mCellIndex: Int = 0
    private var mNumOfButtons: Int = 0
    private var mSwipeDistance: Float = 0.0f
    private var mAnimDuration: Long = 0

    init {
        LayoutInflater.from(context).inflate(R.layout.swipeable_list_cell, this)
    }

    fun initializeComponent(
            @NonNull cellIndex: Int, @IntRange(from = 1, to = 3) numOfButtons: Int, @NonNull animDuration: Long,
            @NonNull listener: OnButtonClickListener
    ) {
        this.mCellIndex = cellIndex
        this.mNumOfButtons = numOfButtons
        this.mAnimDuration = animDuration
        this.mButtonClickListener = listener
        setButtonConfiguration()
        setListeners()
    }

    fun setButtonOneValues(@Nullable @ColorRes backgroundColor: Int, @NonNull text: String, @Nullable @ColorRes textColor: Int) {
        setButtonValues(button_one, button_one_text, backgroundColor, text, textColor)
    }

    fun setButtonTwoValues(@Nullable @ColorRes backgroundColor: Int, @NonNull text: String, @Nullable @ColorRes textColor: Int) {
        setButtonValues(button_two, button_two_text, backgroundColor, text, textColor)
    }

    fun setButtonThreeValues(@Nullable @ColorRes backgroundColor: Int, @NonNull text: String, @Nullable @ColorRes textColor: Int) {
        setButtonValues(button_three, button_three_text, backgroundColor, text, textColor)
    }

    fun setSwipeableCell(@LayoutRes swipeableCell: Int): View {
        view_stub_layout.layoutResource = swipeableCell
        return view_stub_layout.inflate()
    }

    private fun setButtonValues(
            button: View,
            textView: TextView,
            backgroundColor: Int,
            text: String,
            textColor: Int
    ) {
        button.setBackgroundColor(ContextCompat.getColor(this.context, backgroundColor))
        textView.text = text
        textView.setTextColor(ContextCompat.getColor(this.context, textColor))
    }

    private fun setListeners() {
        button_one.setOnClickListener {
            mButtonClickListener?.onButtonClickListener(mCellIndex, button_one_text.text.toString())
        }

        button_two.setOnClickListener {
            mButtonClickListener?.onButtonClickListener(mCellIndex, button_two_text.text.toString())
        }

        button_three.setOnClickListener {
            mButtonClickListener?.onButtonClickListener(mCellIndex, button_three_text.text.toString())
        }

        swipeable_view.setOnTouchListener(SwipeableTouchListener(this, swipeable_view, mSwipeDistance, mAnimDuration))
    }

    private fun setButtonConfiguration() {
        when (mNumOfButtons) {
            NUM_BUTTONS_ONE -> {
                button_two.visibility = View.GONE
                button_three.visibility = View.GONE
                mSwipeDistance = 90.0f
            }
            NUM_BUTTONS_TWO -> {
                button_three.visibility = View.GONE
                mSwipeDistance = 180.0f
            }
            NUM_BUTTONS_THREE -> {
                mSwipeDistance = 270.0f
            }
        }
    }

    interface OnButtonClickListener {
        fun onButtonClickListener(cellIndex: Int, buttonClicked: String)
    }
}