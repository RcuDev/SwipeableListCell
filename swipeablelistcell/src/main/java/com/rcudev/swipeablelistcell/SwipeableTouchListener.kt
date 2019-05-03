/*
 * Developed by Raul Corvo (recu.developer@gmail.com)
 * Copyright (c) 2019. All rights reserved
 */

package com.rcudev.swipeablelistcell

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

class SwipeableTouchListener(
    val swipeableListCell: SwipeableListCell,
    val swipeableView: View,
    val swipeDistance: Float,
    val swipeDuration: Long
) : View.OnTouchListener {

    private val POSITION_0: Float = 0.0f

    private val mGestureDetector = GestureDetector(GestureListener())

    private var mRightToLeft: Boolean = true

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            swipeableListCell.requestDisallowInterceptTouchEvent(true)
            if (mRightToLeft) {
                swipeableView.animate().translationX(swipeDistance.toPx() * -1)
                    .setDuration(swipeDuration).start()
            } else {
                swipeableView.animate().translationX(POSITION_0).setDuration(swipeDuration)
                    .start()
            }
        }
        return mGestureDetector.onTouchEvent(event)
    }

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

            if (e1.x > e2.x) {
                mRightToLeft = true
            } else if (e1.x < e2.x) {
                mRightToLeft = false
            }

            return true
        }
    }
}