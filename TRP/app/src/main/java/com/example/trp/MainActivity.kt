package com.example.trp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonToSecondActivity: Button = findViewById(R.id.buttonToSecondActivity)
        buttonToSecondActivity.setOnClickListener {
            // Создаем Intent для перехода
            val intent = Intent(this, GraphicActivity::class.java)
            startActivity(intent) // Запускаем SecondActivity
        }
    }
}
