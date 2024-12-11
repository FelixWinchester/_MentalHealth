package com.example.trp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class GraphicActivity : AppCompatActivity() {

    private lateinit var lineChart: LineChart
    private val database = FirebaseDatabase.getInstance("https://mental-health-72105-default-rtdb.europe-west1.firebasedatabase.app")


    private lateinit var popupContainer: FrameLayout // Контейнер для всплывающего окна

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_graphic)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.graph)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Инициализация LineChart
        lineChart = findViewById(R.id.lineChart)

        // Загрузка данных для графика
        loadChartData()

        // Инициализация всплывающего окна
        popupContainer = findViewById(R.id.popupContainer)
        val dialogTextView: TextView = findViewById(R.id.dialogTextView)

        // Задаём текст для окна (можно настроить через Firebase или ресурсы)
        dialogTextView.text = getString(R.string.dialog_content)

        // Настройка кнопки для показа окна
        val showDialogButton: Button = findViewById(R.id.showDialogButton)
        showDialogButton.setOnClickListener {
            popupContainer.visibility = View.VISIBLE // Показываем окно
        }

        // Закрытие окна при нажатии на фон
        popupContainer.setOnClickListener {
            popupContainer.visibility = View.GONE // Скрываем окно
        }
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
                    val dataSet = LineDataSet(entries, "Данные из Realtime Database").apply {
                        lineWidth = 2f
                        color = Color.BLUE
                        setDrawCircles(false)
                        setDrawValues(false)
                    }
                    lineChart.data = LineData(dataSet)

                    // Настройка оси X
                    lineChart.xAxis.apply {
                        position = XAxis.XAxisPosition.BOTTOM
                        granularity = 10f
                        isGranularityEnabled = true
                    }

                    // Настройка осей Y
                    lineChart.axisLeft.granularity = 100f
                    lineChart.axisRight.isEnabled = false

                    lineChart.invalidate() // Перерисовываем график
                }
            }

            override fun onCancelled(error: DatabaseError) {
                error.toException().printStackTrace()
            }
        })
    }
}
