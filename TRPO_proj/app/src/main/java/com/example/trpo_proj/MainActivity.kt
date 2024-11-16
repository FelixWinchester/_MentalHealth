package com.example.trpo_proj

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Обработчики кнопок
        findViewById<Button>(R.id.btnSchedule).setOnClickListener {
            startActivity(Intent(this, ScheduleActivity::class.java))
        }
        findViewById<Button>(R.id.btnDiary).setOnClickListener {
            startActivity(Intent(this, DiaryActivity::class.java))
        }
        findViewById<Button>(R.id.btnSetGoal).setOnClickListener {
            startActivity(Intent(this, SetGoalActivity::class.java))
        }
        findViewById<Button>(R.id.btnTips).setOnClickListener {
            startActivity(Intent(this, TipsActivity::class.java))
        }
    }
}
