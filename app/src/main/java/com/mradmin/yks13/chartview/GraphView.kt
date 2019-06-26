package com.mradmin.yks13.chartview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import java.util.*
import android.graphics.*
import android.util.TypedValue
import kotlin.collections.ArrayList
import android.graphics.RectF
import android.graphics.Shader.TileMode
import android.graphics.LinearGradient
import android.graphics.Shader

class GraphView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    companion object {
        var LINE = 0
        var PIE = 1

        //LINE GRAPH
        private const val DEFAULT_TITLE_TEXT = "GraphView"
        private const val DEFAULT_TITLE_TEXT_SIZE = 12f
        private const val DEFAULT_TITLE_COLOR = Color.BLACK
        private const val DEFAULT_VERTICAL_LABELS_COLOR = Color.BLACK
        private const val DEFAULT_HORIZONTAL_LABELS_COLOR = Color.BLACK
        private const val DEFAULT_GRAPH_LINE_COLOR = Color.BLACK

    }

    private val paint: Paint
    private val canvasController: CanvasController

    var values: ArrayList<Float>? = null
    var horizontalLabels: ArrayList<String>? = null
    var verticalLabels: ArrayList<String>? = null
    var title: String? = null

    private var shader: Shader
    private var path: Path

    private var size = width
    private var slices: ArrayList<PieSlice>? = null
        get() {
            var slicesArray: ArrayList<PieSlice>? = null
            if (this.values != null) {
                val random = Random()
                slicesArray = ArrayList<PieSlice>()
                for (i in this.values!!.indices){
                    slicesArray.add(PieSlice(Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256)), values!![i]))
                }
            }
            return slicesArray
        }

    private val maxValue: Float
        get() {
            var largest = Integer.MIN_VALUE.toFloat()
            for (i in values!!.indices)
                if (values!![i] > largest)
                    largest = values!![i]
            return largest
        }

    private val minValue: Float
        get() {
            var smallest = Integer.MAX_VALUE.toFloat()
            for (i in values!!.indices)
                if (values!![i] < smallest)
                    smallest = values!![i]
            return smallest
        }

    //attrs
    var shouldDrawHorizontalLabels: Boolean = true
    var shouldDrawVerticalLabels: Boolean = true
    var shouldDrawTitleLabel: Boolean = true
    var shouldDrawGradient: Boolean = false
    var titleTextSize: Int = DEFAULT_TITLE_TEXT_SIZE.toInt()
    var titleColor: Int = DEFAULT_TITLE_COLOR
    var horizontalLabelsColor: Int = DEFAULT_HORIZONTAL_LABELS_COLOR
    var verticalLabelsColor: Int = DEFAULT_VERTICAL_LABELS_COLOR
    var graphLineColor: Int = DEFAULT_GRAPH_LINE_COLOR
    var graphType: Int = LINE

    //init
    init {
        this.slices = ArrayList()
        this.values = ArrayList()
        this.horizontalLabels = ArrayList()
        this.verticalLabels = ArrayList()
        this.title = DEFAULT_TITLE_TEXT

        @Suppress("DEPRECATION")
        this.shader = LinearGradient(0f, 0f, 0f, Math.min(width, height).toFloat(), resources.getColor(R.color.color_red), resources.getColor(R.color.color_blue), TileMode.CLAMP)

        this.path = Path()

        paint = Paint()
        paint.isAntiAlias = true
        canvasController = CanvasController(paint)

        setupDefaultAttributes()
        setupAttributes(attrs)
    }

    constructor(context: Context) : this(context, null) {
        setupDefaultAttributes()
    }

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : this(context, attrs)

    private fun setupDefaultAttributes() {
        val dm = resources.displayMetrics

        titleTextSize = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, DEFAULT_TITLE_TEXT_SIZE, dm).toInt()
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.GraphView,
                0, 0)

        val dm = resources.displayMetrics

        shouldDrawTitleLabel = typedArray.getBoolean(R.styleable.GraphView_shouldDrawTitleLabel, true)
        shouldDrawHorizontalLabels = typedArray.getBoolean(R.styleable.GraphView_shouldDrawHorizontalLabels, true)
        shouldDrawVerticalLabels = typedArray.getBoolean(R.styleable.GraphView_shouldDrawVerticalLabels, true)
        shouldDrawGradient = typedArray.getBoolean(R.styleable.GraphView_shouldDrawGradient, false)

        titleTextSize = typedArray.getDimensionPixelSize(
                R.styleable.GraphView_titleTextSize,
                TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_SP, DEFAULT_TITLE_TEXT_SIZE, dm).toInt())

        titleColor = typedArray.getInt(R.styleable.GraphView_titleColor, DEFAULT_TITLE_COLOR)
        horizontalLabelsColor = typedArray.getInt(R.styleable.GraphView_horizontalLabelsColor, DEFAULT_HORIZONTAL_LABELS_COLOR)
        verticalLabelsColor = typedArray.getInt(R.styleable.GraphView_verticalLabelsColor, DEFAULT_VERTICAL_LABELS_COLOR)

        graphLineColor = typedArray.getInt(R.styleable.GraphView_graphLineColor, DEFAULT_GRAPH_LINE_COLOR)

        graphType = typedArray.getInt(R.styleable.GraphView_graphType, LINE)

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        size = Math.min(measuredWidth, measuredHeight)

        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null)
            return

        canvasController.canvas = canvas

        if (this.maxValue != this.minValue) {
            drawGraph()
        }
    }

    //draw methods

    private fun drawShader() {
        val diff = this.maxValue - this.minValue
        val dataLength = this.values!!.size.toFloat()
        val colWidth = (width) / dataLength
        val halfCol = colWidth / 2
        var lastH = 0f

        @Suppress("DEPRECATION")
        this.shader = LinearGradient(0f, 0f, 0f, Math.min(width, height).toFloat(), resources.getColor(R.color.color_red), resources.getColor(R.color.color_blue), TileMode.CLAMP)

        canvasController.paint.shader = shader
        path.moveTo(0f, height.toFloat())

        for (i in values!!.indices) {
            val `val` = values!![i] - this.minValue
            val rat = `val` / diff
            val h = height * rat
            if (i > 0) {
                canvasController.drawLine((i - 1) * colWidth + (paddingStart + 1) + halfCol, paddingStart - lastH + height, i * colWidth + (paddingStart + 1) + halfCol, paddingStart - h + height)
                path.lineTo((i - 1) * colWidth + (paddingStart + 1) + halfCol, paddingStart - lastH + height)
                path.lineTo(i * colWidth + (paddingStart + 1) + halfCol, paddingStart - h + height)
            } else {
                path.moveTo((paddingStart + 1) + halfCol, paddingStart - lastH + height)
            }
            lastH = h
        }
        path.lineTo((values!!.size - 1) * colWidth + (paddingStart + 1) + halfCol, height.toFloat())
        path.close()
        canvasController.drawPath(path)
    }

    private fun drawTitle() {
        canvasController.paint.color = this.titleColor
        canvasController.paint.textSize = this.titleTextSize.toFloat()
        canvasController.paint.textAlign = Paint.Align.CENTER
        canvasController.drawText(width / 2f, height / 2f, this.title)
    }

    private fun drawVerticalLabels() {
        canvasController.paint.textAlign = Paint.Align.LEFT
        canvasController.paint.color = this.verticalLabelsColor
        canvasController.paint.strokeWidth = 1f
        canvasController.paint.textSize = this.titleTextSize.toFloat()
        val vers = verticalLabels!!.size
        for (i in verticalLabels!!.indices) {
            val y = height / vers * i
            canvasController.drawLine(paddingStart.toFloat(), y.toFloat(), width.toFloat() - paddingEnd, y.toFloat())
            canvasController.drawText(0f, y.toFloat(), verticalLabels!![i])
        }
    }

    private fun drawHorizontalLabels() {
        canvasController.paint.textAlign = Paint.Align.LEFT
        canvasController.paint.color = this.horizontalLabelsColor
        canvasController.paint.strokeWidth = 1f
        canvasController.paint.textSize = this.titleTextSize.toFloat()

        val horz = horizontalLabels!!.size
        for (i in horizontalLabels!!.indices) {
            val x = width / horz * i + paddingStart
            canvasController.drawLine(x.toFloat(), height.toFloat(), x.toFloat(), paddingStart.toFloat())
            if (i == horizontalLabels!!.size)
                canvasController.paint.textAlign = Paint.Align.RIGHT
            if (i == 0)
                canvasController.paint.textAlign = Paint.Align.LEFT
            canvasController.drawText(x.toFloat(), height.toFloat() - 4, horizontalLabels!![i])
        }
    }

    private fun drawGraph() {
        when (this.graphType) {
            LINE -> drawLineGraph()
            PIE -> drawPieGraph()
            else -> {
                return
            }
        }
    }

    private fun drawLineGraph() {
        //for text
        if (shouldDrawTitleLabel) {
            drawTitle()
        }

        //for vertical labels
        if (shouldDrawVerticalLabels) {
            drawVerticalLabels()
        }

        //for horizontal labels
        if (shouldDrawHorizontalLabels) {
            drawHorizontalLabels()
        }

        canvasController.paint.color = this.graphLineColor
        canvasController.paint.style = Paint.Style.FILL
        canvasController.paint.strokeWidth = 5f

        if (shouldDrawGradient) {
            drawShader()
        } else {
            val diff = this.maxValue - this.minValue
            val dataLength = this.values!!.size.toFloat()
            val colWidth = (width) / dataLength
            val halfCol = colWidth / 2
            var lastH = 0f
            for (i in values!!.indices) {
                val `val` = values!![i] - this.minValue
                val rat = `val` / diff
                val h = height * rat
                if (i > 0)
                    canvasController.drawLine((i - 1) * colWidth + (paddingStart + 1) + halfCol, paddingStart - lastH + height, i * colWidth + (paddingStart + 1) + halfCol, paddingStart - h + height)
                lastH = h
            }
        }
    }

    private fun drawPieGraph() {

        canvasController.paint.strokeWidth = 5f

        val radius = Math.min(width, height)
        val rectF = RectF(width / 2f - radius / 2f, 0f, width / 2f + radius / 2f, radius.toFloat())
        val rectFHole = RectF(width / 2f - radius / 4f , radius / 4f, width / 2f + radius / 4f, radius.toFloat() / 4f * 3f)
        val scaledValues = scaleSlices()
        var start = 0f

        val colors = ColorUtils.generateColorsArray(context, this.slices!!.size)

        for (i in 0 until this.slices!!.size) {

            //for arc without stroke
            canvasController.paint.style = Paint.Style.FILL
            canvasController.paint.color = colors[i]//this.slices!![i].color
            canvasController.drawArc(rectF, start, scaledValues[i], true)

            //for arc stroke only
            //canvasController.paint.style = Paint.Style.STROKE
            //canvasController.paint.color = Color.WHITE
            //canvasController.drawArc(rectF, start, scaledValues[i], true)

            start += scaledValues[i]
        }

        //for oval center hole
        //canvasController.paint.style = Paint.Style.FILL
        //canvasController.paint.color = Color.WHITE
        //canvasController.drawOval(rectFHole)

        drawPieValues()

    }

    private fun drawPieValues() {
        canvasController.paint.color = this.titleColor
        canvasController.paint.textSize = this.titleTextSize.toFloat()

        val radius = Math.min(width, height)
        val rectF = RectF(width / 2f - radius / 2f, 0f, width / 2f + radius / 2f, radius.toFloat())

        var sliceStartAngle = 0f
        val textDistance = radius / 2f * (1 - 0.2f / 2f)
        var sliceHalfAngle: Float
        var textCenterX: Float
        var textCenterY: Float


        for (slice in this.slices!!) {
            sliceHalfAngle = sliceStartAngle + getSliceAngle(slice) / 2f
            textCenterX = MathUtils.getPointX(
                    rectF.centerX(), textDistance, sliceHalfAngle)
            textCenterY = MathUtils.getPointY(
                    rectF.centerY(), textDistance, sliceHalfAngle)

            val text = getSlicePercentString(slice)
            val bounds = Rect()

            canvasController.paint.getTextBounds(text, 0, text.length, bounds)
            canvasController.drawText(textCenterX - bounds.width() / 2f,
                    textCenterY + bounds.height() / 2f, text)

            sliceStartAngle += getSliceAngle(slice)
        }
    }

    //utils
    private fun scaleSlices(): FloatArray {
        val scaledValues = FloatArray(this.slices!!.size)
        for (i in 0 until this.slices!!.size) {
            scaledValues[i] = getSliceAngle(this.slices!![i]) //Scale each value
        }
        return scaledValues
    }

    private fun getTotal(): Float {
        var total = 0f
        for (`val` in this.slices!!)
            total += `val`.value
        return total
    }

    private fun getSliceAngle(slice: PieSlice): Float {
        return slice.value / getTotal() * 360
    }

    private fun getSlicePercentString(slice: PieSlice): String {
        return Math.round(slice.value / getTotal() * 100).toString() + "%"
    }
}