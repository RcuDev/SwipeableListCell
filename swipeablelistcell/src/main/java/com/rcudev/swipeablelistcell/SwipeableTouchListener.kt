/*
 * Developed by Raul Corvo (recu.developer@gmail.com)
 * Copyright (c) 2019. All rights reserved
 */

package com.rcudev.swipeablelistcell

import android.content.Context
import android.content.res.Resources
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

class SwipeableTouchListener(
    swipeableListCell: SwipeableListCell,
    swipeableView: View,
    swipeDistance: Float,
    swipeDuration: Long
) : View.OnTouchListener {

    private val mGestureDetector =
        GestureDetector(GestureListener(swipeableListCell, swipeableView, swipeDistance, swipeDuration))

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        return mGestureDetector.onTouchEvent(event)
    }

    private inner class GestureListener(
        swipeableListCell: SwipeableListCell,
        swipeableView: View,
        swipeDistance: Float,
        swipeDuration: Long
    ) : GestureDetector.SimpleOnGestureListener() {

        private val POSITION_0: Float = 0.0f

        private var mSwipeableListCell: SwipeableListCell = swipeableListCell
        private var mSwipeableView: View = swipeableView
        private var mSwipeDistnace: Float = swipeDistance
        private var mSwipeDuration: Long = swipeDuration

        override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
            if (e1.action == MotionEvent.ACTION_DOWN) {
                mSwipeableListCell.requestDisallowInterceptTouchEvent(true)
            }

            if (e1.x < e2.x) {
                mSwipeableView.animate().translationX(mSwipeDistnace.toPx()).setDuration(mSwipeDuration).start()
            } else if (e1.x > e2.x) {
                mSwipeableView.animate().translationX(POSITION_0).setDuration(mSwipeDuration).start()
            }

            return true
        }

        private fun Float.toPx(): Float = (this * Resources.getSystem().displayMetrics.density)
    }
}