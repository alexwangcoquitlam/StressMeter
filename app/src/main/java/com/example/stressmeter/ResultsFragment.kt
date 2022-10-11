package com.example.stressmeter

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class ResultsFragment : Fragment() {

    private lateinit var chart: lecho.lib.hellocharts.view.LineChartView
    private lateinit var table: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ret = inflater.inflate(R.layout.fragment_results, container, false)
        table = ret.findViewById(R.id.summaryTable)

        var rows = getRecords()
        populateTable(rows)

        return ret
    }

    fun getRecords() : List<Record>{
        var rows = ArrayList<Record>()
        try{
            BufferedReader(FileReader(File(Environment.getExternalStorageDirectory(),"stressMeter.csv"))).use{ reader ->
                var line: String
                while(reader.readLine().also { line = it } != null){
                    val tokens = line.split(",")
                    rows.add(Record(tokens[0], tokens[1].toInt()))
                }
            }
        }
        catch(e: java.lang.Exception){
            e.printStackTrace()
        }
        return rows
    }

    fun populateTable(rows: List<Record>){
        for(i in rows.orEmpty()){
            table.addView(createTableRow(i.getTimestamp(), i.getRecord()))
        }
    }

    private fun createTableRow(time: String, stress: Int): TableRow? {
        val row = TableRow(activity)
        row.layoutParams = TableRow.LayoutParams(-1, -2)

        val timestamp = TextView(activity)
        timestamp.text = time
        timestamp.setLayoutParams(TableRow.LayoutParams(200, -2))

        val value = TextView(activity)
        value.text = stress.toString()

        row.addView(timestamp)
        row.addView(value)
        return row
    }
}