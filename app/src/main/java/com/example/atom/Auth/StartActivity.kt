package com.example.atom.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.atom.MainActivity
import com.example.atom.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class StartActivity : AppCompatActivity() {

    private lateinit var firebaseUser: FirebaseUser

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

    override fun onStart() {
        super.onStart()
        firebaseUser = FirebaseAuth.getInstance().currentUser

        if (firebaseUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}