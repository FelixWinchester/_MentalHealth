package com.example.second_try

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.second_try.ui.theme.Second_tryTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        // Проверка авторизации пользователя
        if (auth.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish() // Закрываем MainActivity, если пользователь не авторизован
        } else {
            setContent {
                Second_tryTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        MainScreen(
                            modifier = Modifier.padding(innerPadding),
                            onLogout = {
                                auth.signOut()
                                startActivity(Intent(this, LoginActivity::class.java))
                                finish() // Закрываем MainActivity после выхода
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier, onLogout: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Greeting(name = "User")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onLogout) {
            Text("Выйти")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Second_tryTheme {
        MainScreen(onLogout = {})
    }
}
