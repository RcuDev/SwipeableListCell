/*
 * Developed by Raul Corvo (recu.developer@gmail.com)
 * Copyright (c) 2019. All rights reserved
 */

package com.rcudev.swipeablelistcell

import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.swipeable_list_cell.view.*

open class SwipeableTouchListener(
    val swipeableListCell: SwipeableListCell,
    val swipeableView: View,
    val swipeDistance: Float,
    val swipeDuration: Long
) : View.OnTouchListener {

    private val NEGATIVE_VALUE: Int = -1
    private val POSITION_0: Float = 0.0f
    private val MIN_SCROLL: Float = 150.0f

    private val mGestureDetector = GestureDetector(GestureListener())

    private var mRightToLeft: Boolean = false
    private var mLeftToRight: Boolean = false

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            when {
                mRightToLeft -> {
                    if (swipeableListCell.parent is RecyclerView) {
                        val parent: RecyclerView = swipeableListCell.parent as RecyclerView
                        for (index in 0 until parent.childCount) {
                            val child: SwipeableListCell =
                                parent.getChildAt(index) as SwipeableListCell
                            child.swipeable_view.animate().translationX(POSITION_0)
                                .setDuration(swipeDuration)
                                .start()
                        }
                    }
                    swipeableView.animate().translationX(swipeDistance.toPx() * NEGATIVE_VALUE)
                        .setDuration(swipeDuration).start()
                }
                mLeftToRight -> swipeableView.animate().translationX(POSITION_0).setDuration(
                    swipeDuration
                )
                    .start()
                else -> v.performClick()
            }

            mRightToLeft = false
            mLeftToRight = false
        }
        return mGestureDetector.onTouchEvent(event)
    }

    open fun onSwipeableCellClickListener() {}

    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onScroll(
            e1: MotionEvent,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            if (e1.action == MotionEvent.ACTION_DOWN) {
                swipeableListCell.requestDisallowInterceptTouchEvent(true)
            }

            if (e1.x > e2.x + MIN_SCROLL) {
                mRightToLeft = true
            } else if (e1.x < e2.x - MIN_SCROLL) {
                mLeftToRight = true
            }

            return false
        }

        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            onSwipeableCellClickListener()
            return true
        }

    }
}