package com.mradmin.yks13.chartview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var graphViewLine: GraphView? = null
    private var graphViewPie: GraphView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        graphViewLine = findViewById(R.id.graph_line)
        graphViewPie = findViewById(R.id.graph_pie)

        val random = Random()

        //line
        graphViewLine?.values = ArrayList()
        graphViewLine?.verticalLabels = ArrayList()
        graphViewLine?.horizontalLabels = ArrayList()

        for (i in 0..10) {
            graphViewLine?.values!!.add(random.nextFloat())
            graphViewLine?.verticalLabels!!.add("i = $i")
            graphViewLine?.horizontalLabels!!.add("i = $i")
        }

        //pie
        graphViewPie?.values = ArrayList()

        for (i in 0..10) {
            graphViewPie?.values!!.add(random.nextFloat())
        }
    }

}
