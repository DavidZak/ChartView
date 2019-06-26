package com.mradmin.yks13.chartview

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF

class CanvasController {

    var canvas: Canvas = Canvas()
    var paint: Paint = Paint()

    constructor() {
        this.canvas = Canvas()
        this.paint = Paint()
    }

    constructor(canvas: Canvas) {
        this.canvas = canvas
    }

    constructor(paint: Paint): this() {
        this.paint = paint
    }

    constructor(canvas: Canvas, paint: Paint): this(canvas) {
        this.paint = paint
    }

    //DRAW RECT

    fun drawRect(left: Float, top: Float, right: Float, bottom: Float) {
        canvas.drawRect(left, top, right, bottom, paint)
    }

    fun drawRect(paint: Paint, left: Float, top: Float, right: Float, bottom: Float) {
        canvas.drawRect(left, top, right, bottom, paint)
    }

    fun drawRect(left: Float, top: Float, right: Float, bottom: Float, color: Int) {
        paint.color = color
        canvas.drawRect(left, top, right, bottom, paint)
    }

    fun drawRect(paint: Paint, left: Float, top: Float, right: Float, bottom: Float, color: Int) {
        paint.color = color
        canvas.drawRect(left, top, right, bottom, paint)
    }

    fun drawRect(left: Float, top: Float, right: Float, bottom: Float, color: Int, style: Paint.Style) {
        paint.color = color
        paint.style = style
        canvas.drawRect(left, top, right, bottom, paint)
    }

    fun drawRect(paint: Paint, left: Float, top: Float, right: Float, bottom: Float, color: Int, style: Paint.Style) {
        paint.color = color
        paint.style = style
        canvas.drawRect(left, top, right, bottom, paint)
    }

    //DRAW LINE

    fun drawLine(startX: Float, startY: Float, stopX: Float, stopY: Float) {
        canvas.drawLine(startX, startY, stopX, stopY, paint)
    }

    fun drawLine(paint: Paint, startX: Float, startY: Float, stopX: Float, stopY: Float) {
        canvas.drawLine(startX, startY, stopX, stopY, paint)
    }

    fun drawLine(paint: Paint, startX: Float, startY: Float, stopX: Float, stopY: Float, color: Int) {
        paint.color = color
        canvas.drawLine(startX, startY, stopX, stopY, paint)
    }

    fun drawLine(startX: Float, startY: Float, stopX: Float, stopY: Float, color: Int, style: Paint.Style) {
        paint.color = color
        paint.style = style
        canvas.drawLine(startX, startY, stopX, stopY, paint)
    }

    fun drawLine(paint: Paint, startX: Float, startY: Float, stopX: Float, stopY: Float, color: Int, style: Paint.Style) {
        paint.color = color
        paint.style = style
        canvas.drawLine(startX, startY, stopX, stopY, paint)
    }

    //DRAW TEXT

    fun drawText(paint: Paint, xPos: Float, yPos: Float, textColor: Int, textSize: Float, textAlign: Paint.Align, text: String?) {
        paint.textSize = textSize
        paint.color = textColor
        paint.textAlign = textAlign
        canvas.drawText(text, xPos, yPos, paint)
    }

    fun drawText(paint: Paint, xPos: Float, yPos: Float, text: String?) {
        canvas.drawText(text, xPos, yPos, paint)
    }

    fun drawText(xPos: Float, yPos: Float, text: String?) {
        canvas.drawText(text, xPos, yPos, paint)
    }

    fun drawText(xPos: Float, yPos: Float, textColor: Int, textSize: Float, textAlign: Paint.Align, text: String?) {
        paint.textSize = textSize
        paint.color = textColor
        paint.textAlign = textAlign
        canvas.drawText(text, xPos, yPos, paint)
    }

    //DRAW ARC
    fun drawArc(rectF: RectF, startAngle: Float, sweepAngle: Float, useCenter: Boolean) {
        canvas.drawArc(rectF, startAngle, sweepAngle, useCenter, paint)
    }

    fun drawArc(paint: Paint, rectF: RectF, startAngle: Float, sweepAngle: Float, useCenter: Boolean) {
        canvas.drawArc(rectF, startAngle, sweepAngle, useCenter, paint)
    }

    //DRAW OVAL
    fun drawOval(rectF: RectF) {
        canvas.drawOval(rectF, paint)
    }

    fun drawOval(paint: Paint, rectF: RectF) {
        canvas.drawOval(rectF, paint)
    }

    //DRAW PATH
    fun drawPath(path: Path) {
        canvas.drawPath(path, paint)
    }

    fun drawPath(path: Path, paint: Paint) {
        canvas.drawPath(path, paint)
    }
}