package com.example.breakingnews

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val user = findViewById<EditText>(R.id.username)
        val pass = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.btnlogin)

        login.setOnClickListener {
            val inputUser = user.text.toString()
            val inputPass = pass.text.toString()

            if (inputUser == "Wahyu" && inputPass == "12345") {
                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                val sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
                sharedPreferences.edit().putBoolean("is_logged_in", true).apply()
                val intent = Intent(this, BeritaActivity::class.java)
                intent.putExtra("username", inputUser)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(
                    this,
                    "Username dan password yang Anda masukkan salah",
                    Toast.LENGTH_SHORT
                ).show()
                }
            }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}