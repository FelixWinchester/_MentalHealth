package com.example.second_try

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AchievementsActivity : AppCompatActivity() {

    private lateinit var lineChart: LineChart
    private val database = FirebaseDatabase.getInstance("https://mental-health-72105-default-rtdb.europe-west1.firebasedatabase.app")

    private lateinit var popupContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievements)

        // Инициализация элементов
        lineChart = findViewById(R.id.lineChart)
        popupContainer = findViewById(R.id.popupContainer)
        val dialogTextView: TextView = findViewById(R.id.dialogTextView)
        val showDialogButton: Button = findViewById(R.id.showDialogButton)

        // Установка текста всплывающего окна
        dialogTextView.text = getString(R.string.dialog_content)

        // Показ и скрытие окна
        showDialogButton.setOnClickListener {
            popupContainer.visibility = View.VISIBLE
        }
        popupContainer.setOnClickListener {
            popupContainer.visibility = View.GONE
        }

        // Загрузка данных для графика
        loadChartData()
    }

    private fun loadChartData() {
        val chartDataRef = database.getReference("chartData")
        chartDataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val entries = mutableListOf<Entry>()
                for (dataSnapshot in snapshot.children) {
                    val year = dataSnapshot.child("year").getValue(Float::class.java)
                    val value = dataSnapshot.child("value").getValue(Float::class.java)
                    if (year != null && value != null) {
                        entries.add(Entry(year, value))
                    }
                }
                if (entries.isNotEmpty()) {
                    val dataSet = LineDataSet(entries, "График").apply {
                        lineWidth = 2f
                        color = Color.BLUE
                        setDrawCircles(false)
                        setDrawValues(false)
                    }
                    lineChart.data = LineData(dataSet)
                    lineChart.xAxis.apply {
                        position = XAxis.XAxisPosition.BOTTOM
                        granularity = 1f
                        textColor = Color.BLACK
                    }
                    lineChart.axisLeft.textColor = Color.BLACK
                    lineChart.axisRight.isEnabled = false
                    lineChart.invalidate()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                error.toException().printStackTrace()
            }
        })
    }
}
