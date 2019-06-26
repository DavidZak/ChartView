package com.mradmin.yks13.chartview.model

interface BaseData {

    //utils
    fun getTotal(values: FloatArray): Float {
        var total = 0f
        for (`val` in values)
            total += `val`
        return total
    }
}