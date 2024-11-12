package com.example.trp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import android.graphics.Color

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Инициализация LineChart
        val lineChart = findViewById<LineChart>(R.id.lineChart)

        // Подготавливаем данные для графика
        val values = ArrayList<Entry>().apply {
            add(Entry(1961f, 20f))
            add(Entry(1970f, 30f))
            add(Entry(1980f, 40f))
            add(Entry(1990f, 60f))
            add(Entry(2000f, 80f))
            add(Entry(2008f, 600f)) // Пик, как на изображении
        }

        val dataSet = LineDataSet(values, "Пример данных").apply {
            lineWidth = 2f
            color = Color.BLUE
            setDrawCircles(false)
            setDrawValues(false)
        }

        if (values.isNotEmpty()) {
            lineChart.data = LineData(dataSet)
        }

        // Настройка оси X
        lineChart.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            granularity = 10f
            isGranularityEnabled = true
        }

        // Настройка осей Y
        lineChart.axisLeft.apply {
            granularity = 100f
        }
        lineChart.axisRight.isEnabled = false

        lineChart.invalidate() // Перерисовываем график
    }
}