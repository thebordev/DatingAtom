package com.example.atom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        val btnRegister: Button = findViewById(R.id.btnRegister)
        val usernameEt: EditText = findViewById(R.id.usernameEt)
        val emailEt: EditText = findViewById(R.id.emailEt)
        val passwordEt: EditText = findViewById(R.id.passwordEt)

        btnRegister.setOnClickListener{
            val username = usernameEt.text.toString()
            val email = emailEt.text.toString()
            val password = passwordEt.text.toString()

            if (TextUtils.isEmpty(username)){
                Toast.makeText(applicationContext,"username is required",Toast.LENGTH_SHORT).show()
            }
            if (TextUtils.isEmpty(email)){
                Toast.makeText(applicationContext,"email is required",Toast.LENGTH_SHORT).show()
            }

            if (TextUtils.isEmpty(password)){
                Toast.makeText(applicationContext,"password is required",Toast.LENGTH_SHORT).show()
            }
            registerUser(username, email, password)
        }
    }

    private fun registerUser(username:String, email:String, password:String) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        val user: FirebaseUser? = auth.currentUser
                        val userId: String = user!!.uid

                        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId)

                        val hashMap: HashMap<String, String> = HashMap()
                        hashMap.put("id", userId)
                        hashMap.put("username", username)
                        hashMap.put("imageURL", "default")
                        hashMap.put("status", "online")
                        hashMap.put("typingTo", "noOne")

                        databaseReference.setValue(hashMap).addOnCompleteListener(this) {
                            if (it.isSuccessful) {
                                var intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }
                    }
                    else {
                        Toast.makeText(this, "You cant't register woth this email or password", Toast.LENGTH_SHORT).show()
                }
        }
    }
}