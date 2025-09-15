package com.example.empoweringthenation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class loginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)


        val email = findViewById<EditText>(R.id.emailTxt)
        val password = findViewById<EditText>(R.id.passwordTxt)
        val loginBtn = findViewById<Button>(R.id.btnLogin)
        val signUpBtn = findViewById<Button>(R.id.btnSignUp)

        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

// LOGIN button
        loginBtn.setOnClickListener {
            val savedEmail = sharedPref.getString("email", null)
            val savedPassword = sharedPref.getString("password", null)

            if (email.text.toString() == savedEmail && password.text.toString() == savedPassword) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, CourseDetailActivity::class.java))

            } else {
                Toast.makeText(this, "Account not found. Please sign up.", Toast.LENGTH_SHORT).show()
            }
        }

        // SIGN UP button
        signUpBtn.setOnClickListener {
            val emailText = email.text.toString()
            val passwordText = password.text.toString()

            if (emailText.isNotEmpty() && passwordText.isNotEmpty()) {
                // Save new credentials
                with(sharedPref.edit()) {
                    putString("email", emailText)
                    putString("password", passwordText)
                    apply()
                }

                Toast.makeText(this, "Account created", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Please enter Email & Password", Toast.LENGTH_SHORT).show()
            }
        }

    }
}