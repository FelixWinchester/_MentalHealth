package com.example.second_try

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class MoodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood)

        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        if (currentUser == null) {
            Toast.makeText(this, "Пользователь не авторизован", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val userId = currentUser.uid
        val databaseRef = FirebaseDatabase.getInstance("https://mental-health-72105-default-rtdb.europe-west1.firebasedatabase.app")
            .getReference("Users")
            .child(userId)
        findViewById<Button>(R.id.btnExit).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        // Обработчики кнопок
        findViewById<Button>(R.id.btnHappy).setOnClickListener { saveMoodAndNavigate("Счастлив", databaseRef) }
        findViewById<Button>(R.id.btnSad).setOnClickListener { saveMoodAndNavigate("Грустный", databaseRef) }
        findViewById<Button>(R.id.btnAngry).setOnClickListener { saveMoodAndNavigate("Злой", databaseRef) }
        findViewById<Button>(R.id.btnRelaxed).setOnClickListener { saveMoodAndNavigate("Спокойный", databaseRef) }
        findViewById<Button>(R.id.btnTired).setOnClickListener { saveMoodAndNavigate("Усталый", databaseRef) }
        findViewById<Button>(R.id.btnExcited).setOnClickListener { saveMoodAndNavigate("Возбужденный", databaseRef) }
    }

    private fun saveMoodAndNavigate(mood: String, databaseRef: DatabaseReference) {
        // Получение текущего времени
        val currentDateTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

        // Данные для сохранения
        val moodData = mapOf(
            "mood" to mood,
            "timestamp" to currentDateTime
        )

        // Сохранение данных в Firebase
        databaseRef.child("Mood").setValue(moodData).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Ваше состояние сохранено: $mood", Toast.LENGTH_SHORT).show()
                // Переход на MainActivity
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Ошибка сохранения состояния", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
