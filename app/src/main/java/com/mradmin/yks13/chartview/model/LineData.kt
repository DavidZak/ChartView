package com.mradmin.yks13.chartview.model

class LineData : BaseData {

    var values: FloatArray
    var xLabels: Array<String>
    var yLabels: Array<String>

    constructor(values: FloatArray, xLabels: Array<String>, yLabels: Array<String>) {
        this.values = values
        this.xLabels = xLabels
        this.yLabels = yLabels
    }

    constructor(values: FloatArray, xLabels: Array<String>) {
        this.values = values
        this.xLabels = xLabels
        this.yLabels = emptyArray()
    }

    constructor(values: FloatArray) {
        this.values = values
        this.xLabels = emptyArray()
        this.yLabels = emptyArray()
    }

    constructor() {
        this.values = FloatArray(0)
        this.xLabels = emptyArray()
        this.yLabels = emptyArray()
    }
}