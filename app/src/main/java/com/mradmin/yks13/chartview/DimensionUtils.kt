package com.mradmin.yks13.chartview

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

open class DimensionUtils {

    companion object {

        fun convertSpToPixels(sp: Float, context: Context): Float {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.resources.displayMetrics)
        }

        fun convertDpToPixels(dp: Float, context: Context): Float {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)
        }

        fun convertPixelsToSp(px: Float, context: Context): Float {
            return px / context.resources.displayMetrics.scaledDensity
        }

        fun dpToPx(dp: Int, context: Context): Int {
            val displayMetrics = context.resources.displayMetrics
            return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
        }

    }
}