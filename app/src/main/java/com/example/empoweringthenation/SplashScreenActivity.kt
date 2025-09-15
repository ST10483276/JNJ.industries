package com.example.empoweringthenation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)


        //Shows the splash screen for 3 seconds, then automatically opens the login

        val homeIntent = Intent(this@SplashScreenActivity, loginActivity::class.java)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(homeIntent)
            finish() //closes the splash screen so the user can’t go back to it.
        }, SPLASH_TIME_OUT.toLong())
    }
    companion object {
        const val SPLASH_TIME_OUT = 3000

    }
}