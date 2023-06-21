package com.example.attendencemanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        val login = findViewById<Button>(R.id.login)
        login.setOnClickListener{
            val a = Intent(this,MainActivity::class.java)
            startActivity(a)
        }
    }
}



