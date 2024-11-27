package com.example.second_try

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.second_try.databinding.ActivityLoginBinding
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.loginBtn.setOnClickListener{
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(applicationContext, "Пустые поля", Toast.LENGTH_SHORT).show()
            } else {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            checkMoodStatusAndNavigate()
                        } else {
                            Toast.makeText(applicationContext, "Ошибка авторизации", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        binding.registerBtn.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }
    private fun checkMoodStatusAndNavigate() {
        val currentUser = auth.currentUser
        if (currentUser == null) {
            Toast.makeText(applicationContext, "Пользователь не найден", Toast.LENGTH_SHORT).show()
            return
        }

        val userId = currentUser.uid
        val databaseRef = FirebaseDatabase.getInstance("https://mental-health-72105-default-rtdb.europe-west1.firebasedatabase.app")
            .getReference("Users")
            .child(userId)

        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        databaseRef.child("moodEvaluated").get().addOnSuccessListener { dataSnapshot ->
            val lastMoodDate = dataSnapshot.getValue(String::class.java)
            if (lastMoodDate == currentDate) {
                // Если пользователь уже оценивал своё состояние сегодня, переходим на MainActivity
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            } else {
                // Если пользователь ещё не оценивал состояние сегодня, переходим на MoodActivity
                startActivity(Intent(this@LoginActivity, MoodActivity::class.java))
            }
            finish()
        }.addOnFailureListener {
            Toast.makeText(applicationContext, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show()
        }
    }
}