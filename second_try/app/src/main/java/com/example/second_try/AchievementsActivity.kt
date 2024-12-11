package com.example.second_try

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AchievementsActivity : AppCompatActivity() {

    private lateinit var lineChart: LineChart
    private val database = FirebaseDatabase.getInstance("https://mental-health-72105-default-rtdb.europe-west1.firebasedatabase.app")
    private val auth = FirebaseAuth.getInstance()

    private lateinit var popupContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievements)

        // Инициализация элементов
        lineChart = findViewById(R.id.lineChart)
        popupContainer = findViewById(R.id.popupContainer)
        val dialogTextView: TextView = findViewById(R.id.dialogTextView)
        val showDialogButton: Button = findViewById(R.id.showDialogButton)
        val backButton: Button = findViewById(R.id.backButton)
        val closeButton: ImageButton = findViewById(R.id.closeButton)

        // Установка текста всплывающего окна
        dialogTextView.text = getString(R.string.dialog_content)

        // Переход на MainActivity
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Показ и скрытие окна
        showDialogButton.setOnClickListener {
            loadMoodReport(dialogTextView)
            popupContainer.visibility = View.VISIBLE
        }
        closeButton.setOnClickListener {
            popupContainer.visibility = View.GONE
        }
        popupContainer.setOnClickListener {
            popupContainer.visibility = View.GONE
        }

        // Загрузка данных для графика
        loadChartData()
    }

    private fun loadChartData() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val userId = currentUser.uid
            val chartDataRef = database.getReference("chartData").child(userId)
            chartDataRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val entries = mutableListOf<Entry>()
                    for (weekSnapshot in snapshot.children) {
                        val week = weekSnapshot.key?.toFloatOrNull()
                        val value = weekSnapshot.getValue(Float::class.java)
                        if (week != null && value != null) {
                            entries.add(Entry(week, value))
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

    private fun loadMoodReport(dialogTextView: TextView) {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val userId = currentUser.uid
            val chartDataRef = database.getReference("chartData").child(userId)

            chartDataRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val moodReport = StringBuilder()

                    for (weekSnapshot in snapshot.children) {
                        val week = weekSnapshot.key?.toIntOrNull()
                        val value = weekSnapshot.getValue(Float::class.java)
                        if (week != null && value != null) {
                            val moodDescription = when {
                                value > 5 -> "отличное настроение"
                                value == 5f -> "среднее настроение"
                                value > 3 -> "не очень хорошее настроение"
                                else -> "очень плохое настроение"
                            }
                            moodReport.append("В $week неделю у вас было $moodDescription.\n")
                        }
                    }

                    dialogTextView.text = moodReport.toString()
                }

                override fun onCancelled(error: DatabaseError) {
                    error.toException().printStackTrace()
                }
            })
        }
    }
}
