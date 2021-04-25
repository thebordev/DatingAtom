package com.example.atom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val emailEt: EditText = findViewById(R.id.emailEt)
        val passwordEt: EditText = findViewById(R.id.passwordEt)
        val btnLogin: Button = findViewById(R.id.btnLogIn)

        btnLogin.setOnClickListener {
            val email = emailEt.text.toString()
            val password = passwordEt.text.toString()

            if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                Toast.makeText(
                    applicationContext,
                    "email and password are required",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) {
                        if (it.isSuccessful) {
                            emailEt.setText("")
                            passwordEt.setText("")
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "Authintication failed!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            }

        }
    }
}