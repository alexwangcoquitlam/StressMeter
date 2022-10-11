package com.example.stressmeter

import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import lecho.lib.hellocharts.model.Axis
import lecho.lib.hellocharts.model.Line
import lecho.lib.hellocharts.model.LineChartData
import lecho.lib.hellocharts.model.PointValue
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class ResultsFragment : Fragment() {

    private lateinit var chart: lecho.lib.hellocharts.view.LineChartView
    private lateinit var table: TableLayout

    private var values = ArrayList<PointValue>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ret = inflater.inflate(R.layout.fragment_results, container, false)
        table = ret.findViewById(R.id.summaryTable)
        chart = ret.findViewById(R.id.chart)

        val rows = getRecords()
        populateTable(rows)

        if (rows.isNotEmpty()) {
            val line = Line(values).setColor(Color.BLUE).setCubic(true)
            line.values = values
            line.isFilled = true
            val lines: MutableList<Line> = java.util.ArrayList()
            lines.add(line)
            val data = LineChartData()
            data.lines = lines
            val axisX = Axis()
            val axisY = Axis().setHasLines(true)
            axisX.name = "Instances"
            axisY.name = "Stress Level"
            data.axisXBottom = axisX
            data.axisYLeft = axisY
            chart.lineChartData = data
        }

        return ret
    }

    private fun getRecords() : List<Record>{
        val rows = ArrayList<Record>()
        var counter = 0
        try{
            BufferedReader(FileReader(File(Environment.getExternalStorageDirectory(),"stressMeter.csv"))).use{ reader ->
                var line: String
                while(reader.readLine().also { line = it } != null){
                    val tokens = line.split(",")
                    rows.add(Record(tokens[0], tokens[1].toInt()))
                    values.add(PointValue(counter.toFloat(), tokens[1].toFloat()))
                    counter++
                }
            }
        }
        catch(e: java.lang.Exception){
            e.printStackTrace()
        }
        return rows
    }

    private fun populateTable(rows: List<Record>){
        for(i in rows){
            table.addView(createTableRow(i.getTimestamp(), i.getRecord()))
        }
    }

    private fun createTableRow(time: String, stress: Int): TableRow {
        val row = TableRow(activity)
        row.layoutParams = TableRow.LayoutParams(-1, -1)

        val timestamp = TextView(activity)
        timestamp.text = time
        timestamp.layoutParams = TableRow.LayoutParams(200, -1)

        val value = TextView(activity)
        value.text = stress.toString()

        row.addView(timestamp)
        row.addView(value)
        return row
    }
}