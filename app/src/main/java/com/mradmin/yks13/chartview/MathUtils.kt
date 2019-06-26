package com.mradmin.yks13.chartview

import android.graphics.PointF

class MathUtils {

    companion object {
        fun getPoint(centerX: Float, centerY: Float, distance: Float, degrees: Float): PointF {
            return PointF(
                    getPointX(centerX, distance, degrees),
                    getPointY(centerY, distance, degrees))
        }

        fun getPointX(centerX: Float, distance: Float, degrees: Float): Float {
            return (centerX + distance * Math.sin(-degrees * Math.PI / 180 + Math.PI / 2)).toFloat()
        }

        fun getPointY(centerY: Float, distance: Float, degrees: Float): Float {
            return (centerY + distance * Math.cos(-degrees * Math.PI / 180 + Math.PI / 2)).toFloat()
        }
    }
}