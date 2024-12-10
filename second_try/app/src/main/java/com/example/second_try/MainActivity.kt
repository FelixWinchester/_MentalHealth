package com.example.second_try

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        // Проверка авторизации пользователя
        if (auth.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish() // Закрываем MainActivity, если пользователь не авторизован
        } else {
            // Подключение XML разметки
            setContentView(R.layout.activity_main)

            // Обработчики кнопок
            findViewById<Button>(R.id.btnSchedule).setOnClickListener {

            }

            findViewById<Button>(R.id.btnDiary).setOnClickListener {

            }

            findViewById<Button>(R.id.btnSetGoal).setOnClickListener {

            }

            findViewById<Button>(R.id.btnTips).setOnClickListener {

            }

            findViewById<Button>(R.id.btnLogout).setOnClickListener {
                auth.signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()

            }
        }
    }
}