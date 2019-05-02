/*
 * Developed by Raul Corvo (recu.developer@gmail.com)
 * Copyright (c) 2019. All rights reserved
 */

package com.rcudev.swipeablelistcell

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

    private val POSITION_0: Float = 0.0f

    private val mGestureDetector = GestureDetector(GestureListener(swipeableListCell))
    private var mSwipeableView: View = swipeableView
    private var mSwipeDistnace: Float = swipeDistance
    private var mSwipeDuration: Long = swipeDuration

    private var mRightToLeft: Boolean = true

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            if (mRightToLeft) {
                mSwipeableView.animate().translationX(mSwipeDistnace.toPx() * -1).setDuration(mSwipeDuration).start()
            } else {
                mSwipeableView.animate().translationX(POSITION_0).setDuration(mSwipeDuration).start()
            }
        }
        return mGestureDetector.onTouchEvent(event)
    }

    private fun Float.toPx(): Float = (this * Resources.getSystem().displayMetrics.density)

    private inner class GestureListener(swipeableListCell: SwipeableListCell) : GestureDetector.SimpleOnGestureListener() {

        private var mSwipeableListCell: SwipeableListCell = swipeableListCell

        override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
            if (e1.action == MotionEvent.ACTION_DOWN) {
                mSwipeableListCell.requestDisallowInterceptTouchEvent(true)
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