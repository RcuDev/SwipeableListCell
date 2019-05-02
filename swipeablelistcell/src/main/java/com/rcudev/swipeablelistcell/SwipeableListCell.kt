/*
 * Developed by Raul Corvo (recu.developer@gmail.com)
 * Copyright (c) 2019. All rights reserved
 */

package com.rcudev.swipeablelistcell

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater

class SwipeableListCell @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.swipeable_list_cell, this, true)
    }

}