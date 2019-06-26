package com.mradmin.yks13.chartview

import android.content.Context

class ColorUtils {

    companion object {

        fun generateColorsArray(context: Context, forSize: Int): IntArray {
            val colors = IntArray(8)
            colors[0] = context.resources.getColor(R.color.color_blue)
            colors[1] = context.resources.getColor(R.color.color_green)
            colors[2] = context.resources.getColor(R.color.color_yellow)
            colors[3] = context.resources.getColor(R.color.color_red)
            colors[4] = context.resources.getColor(R.color.color_orange)
            colors[5] = context.resources.getColor(R.color.color_purple)
            colors[6] = context.resources.getColor(R.color.color_gray)
            colors[7] = context.resources.getColor(R.color.color_brown)

            var generatedColors = IntArray(forSize)
            if (generatedColors.size > colors.size) {
                for (i in generatedColors.indices) {
                    generatedColors[i] = colors[i % 8]
                }
            } else {
                for (i in generatedColors.indices) {
                    generatedColors[i] = colors[i]
                }
            }

            return generatedColors
        }
    }
}