package com.mradmin.yks13.chartview.model

import android.graphics.Color
import android.support.annotation.ColorInt

class PieData : BaseData {

    var values: FloatArray

    constructor() {
        this.values = FloatArray(0)
    }

    constructor(values: FloatArray) {
        this.values = values
    }

    //utils
    fun getSliceAngle(value: Float): Float {
        return value / getTotal(values) * 360
    }

    fun scaleSlices(): FloatArray {
        val scaledValues = FloatArray(values.size)
        for (i in 0 until values.size) {
            scaledValues[i] = getSliceAngle(values[i]) //Scale each value
        }
        return scaledValues
    }

    private fun getPieSlicePercentString(value: Float): String {
        return Math.round(value / getTotal(values) * 100).toString() + "%"
    }

}