package com.example.second_try

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var userRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance("https://mental-health-72105-default-rtdb.europe-west1.firebasedatabase.app/")

        // Проверка авторизации пользователя
        if (auth.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            setContentView(R.layout.activity_main)

            // Ссылка на данные текущего пользователя
            val currentUser = auth.currentUser
            val userId = currentUser?.uid
            if (userId != null) {
                userRef = database.getReference("users").child(userId)
                loadUserName()
            }

            // Обработчики кнопок
            findViewById<Button>(R.id.btnSchedule).setOnClickListener {
                // Ваш код для кнопки "Расписание"
            }

            findViewById<Button>(R.id.btnDiary).setOnClickListener {
                // Ваш код для кнопки "Дневник"
            }

            findViewById<Button>(R.id.btnSetGoal).setOnClickListener {
                startActivity(Intent(this, AchievementsActivity::class.java))
            }

            findViewById<Button>(R.id.btnTips).setOnClickListener {
                // Ваш код для кнопки "Советы"
            }

            findViewById<Button>(R.id.btnLogout).setOnClickListener {
                auth.signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun loadUserName() {
        val greetingText: TextView = findViewById(R.id.greetingText)

        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            greetingText.text = "Добрый день, Пользователь"
            return
        }

        // Инициализация FirebaseDatabase с правильным URL
        val database = FirebaseDatabase.getInstance("https://mental-health-72105-default-rtdb.europe-west1.firebasedatabase.app")
        val userRef = database.getReference("Users").child(currentUser.uid)

        // Считывание имени пользователя
        userRef.child("username").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userName = snapshot.getValue(String::class.java)
                if (!userName.isNullOrEmpty()) {
                    greetingText.text = "Добрый день, $userName"
                } else {
                    greetingText.text = "Добрый день, Пользователь"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                greetingText.text = "Добрый день, Пользователь"
                error.toException().printStackTrace()
            }
        })
    }

}
