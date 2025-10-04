package com.example.empoweringthenation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlin.system.exitProcess

class ContactUsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contact_us)
        val homeBtn = findViewById<Button>(R.id.homeBtn)
        val exitBtn = findViewById<Button>(R.id.exitBtn)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                // Already on home
            }

            R.id.nav_six_week -> {
                startActivity(Intent(this, CourseDetailActivity::class.java))
            }
            R.id.nav_course_selection -> {
                startActivity(Intent(this, CourseSelectionActivity2::class.java))
            }
            R.id.nav_contact -> {
                startActivity(Intent(this, ContactUsActivity::class.java))
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
        val startBtn = findViewById<android.widget.Button>(R.id.startBtn)
        startBtn.setOnClickListener {
            val intent = Intent(this, CourseDetailActivity::class.java)
            startActivity(intent)
        }
    }
}