package com.rcudev.swipeablelistcell

import android.content.res.Resources

fun Float.toPx(): Float = (this * Resources.getSystem().displayMetrics.density)
