package com.example.empoweringthenation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class ContactUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contact_us)
        val homeBtn = findViewById<Button>(R.id.homeBtn)
        val exitBtn = findViewById<Button>(R.id.exitBtn)

        homeBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish() // Finish this activity to remove it from the back stack
        }

        // Set up Exit App button click listener
        exitBtn.setOnClickListener {
            finishAffinity() // Closes all activities in the task associated with this activity
            exitProcess(0)
        }
    }
}