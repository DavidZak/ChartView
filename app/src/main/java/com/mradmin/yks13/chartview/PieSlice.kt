package com.mradmin.yks13.chartview

import android.support.annotation.ColorInt

class PieSlice {

    @ColorInt
    var color: Int
    var value: Float

    constructor(@ColorInt color: Int, value: Float) {
        this.color = color
        this.value = value
    }
}