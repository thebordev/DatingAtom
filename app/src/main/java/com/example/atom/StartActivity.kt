package com.example.atom

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val btnLog: Button = findViewById(R.id.btnLogin)
        val btnReg: Button = findViewById(R.id.btnRegister)

        btnLog.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btnReg.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

}