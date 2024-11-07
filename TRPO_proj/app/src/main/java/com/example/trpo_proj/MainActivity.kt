package com.example.trpo_proj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSchedule = findViewById<Button>(R.id.btnSchedule)
        val btnDiary = findViewById<Button>(R.id.btnDiary)
        val btnSetGoal = findViewById<Button>(R.id.btnSetGoal)
        val btnTips = findViewById<Button>(R.id.btnTips)

        btnSchedule.setOnClickListener {
            startActivity(Intent(this, ScheduleActivity::class.java))
        }

        btnDiary.setOnClickListener {
            startActivity(Intent(this, DiaryActivity::class.java))
        }

        btnSetGoal.setOnClickListener {
            startActivity(Intent(this, SetGoalActivity::class.java))
        }

        btnTips.setOnClickListener {
            startActivity(Intent(this, TipsActivity::class.java))
        }
    }
}
