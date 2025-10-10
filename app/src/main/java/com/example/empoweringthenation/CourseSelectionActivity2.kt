package com.example.empoweringthenation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class CourseSelectionActivity2 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    // Data class to hold course info
    data class Course(val name: String, val price: Int)

    // List of available courses
    private val courses = listOf(
        Course("First Aid", 1500),
        Course("Sewing", 1500),
        Course("Landscaping", 1500),
        Course("Life Skills", 1500),
        Course("Child Minding", 750),
        Course("Cooking", 750),
        Course("Garden Maintenance", 750),
    )

    private lateinit var checkBoxes: List<CheckBox>
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_selection2)

        // Set up toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Set up drawer and navigation view
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)

        // Enable drawer toggle (hamburger icon)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Link checkboxes to layout
        checkBoxes = listOf(
            findViewById(R.id.ChkFirstAid),
            findViewById(R.id.ChkSewing),
            findViewById(R.id.ChkLandscaping),
            findViewById(R.id.ChkLifeSkills),
            findViewById(R.id.ChkChildMinding),
            findViewById(R.id.ChkCooking),
            findViewById(R.id.ChkGardenMain),
        )

        // Handle button click to calculate and send data
        val btnProceed = findViewById<Button>(R.id.btnProceed)
        btnProceed.setOnClickListener {
            val selectedCourses = ArrayList<String>()
            var total = 0

            for (i in checkBoxes.indices) {
                if (checkBoxes[i].isChecked) {
                    selectedCourses.add("${courses[i].name} (R${courses[i].price})")
                    total += courses[i].price
                }
            }

            val intent = Intent(this, QuotationScreenActivity::class.java)
            intent.putStringArrayListExtra("SELECTED_COURSES", selectedCourses)
            intent.putExtra("TOTAL_PRICE", total)
            startActivity(intent)
        }
    }

    // Reset checkboxes when user returns
    override fun onResume() {
        super.onResume()
        checkBoxes.forEach { it.isChecked = false }
    }

    // Handle navigation drawer item clicks
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> startActivity(Intent(this, HomeActivity::class.java))
           R.id.nav_six_week -> startActivity(Intent(this, CourseDetailActivity::class.java))
            R.id.nav_contact -> startActivity(Intent(this, ContactUsActivity::class.java))
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    // Handle back button to close drawer if open
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}