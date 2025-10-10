package com.example.empoweringthenation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class CourseDetailActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    private lateinit var layoutSixMonths: LinearLayout
    private lateinit var layoutSixWeek: LinearLayout
    private lateinit var btnSixMonths: Button
    private lateinit var btnSixWeeks: Button
    private lateinit var btnHomeScreen: ImageView
    private lateinit var BtnCourseSelction:Button

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_course_detail)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)
        // Adjust padding to avoid status bar or camera cutout
        ViewCompat.setOnApplyWindowInsetsListener(navView) { view, insets ->
            val systemInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(top = systemInsets.top)
            insets
        }


        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        layoutSixMonths = findViewById(R.id.layoutSixMonthsCourses)
        layoutSixWeek = findViewById(R.id.layoutSixWeeksCourses)
        btnSixMonths = findViewById(R.id.btnSixMonths)
        btnSixWeeks = findViewById(R.id.btnSixWeeks)
        btnHomeScreen = findViewById(R.id.btnHomeScreen)
        BtnCourseSelction=findViewById(R.id.BtnCourseSelection)

        BtnCourseSelction.setOnClickListener{
            val intent = Intent(this,CourseSelectionActivity2::class.java)
            startActivity(intent)
        }

        btnHomeScreen.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }


        btnHomeScreen.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }


        btnSixMonths.setOnClickListener {
            toggleVisibility(layoutSixMonths, btnSixMonths)
        }
        btnSixWeeks.setOnClickListener {
            toggleVisibility(layoutSixWeek, btnSixWeeks)
        }

        //Directs to the First Aid Detail
        findViewById<Button>(R.id.btnFirstAid).setOnClickListener {
            val intent = Intent(this, FirstAidActivity::class.java)
            startActivity(intent)
        }

        // Sewing Detail
        findViewById<Button>(R.id.btnSewing).setOnClickListener {
            val intent = Intent(this, SewingActivity::class.java)
            startActivity(intent)
        }

        // Landscaping Detail
        findViewById<Button>(R.id.btnLandscaping).setOnClickListener {
            val intent = Intent(this, LandscapingActivity::class.java)
            startActivity(intent)
        }

        // Life Skills  Detail
        findViewById<Button>(R.id.btnLifeSkills).setOnClickListener {
            val intent = Intent(this, LifeSkillsActivity::class.java)
            startActivity(intent)
        }

        // Child Minding Detail
        findViewById<Button>(R.id.btnChildMinding).setOnClickListener {
            val intent = Intent(this, ChildMindingActivity::class.java)
            startActivity(intent)
        }

        // Cooking  Detail
        findViewById<Button>(R.id.btnCooking).setOnClickListener {
            val intent = Intent(this, CookingActivity::class.java)
            startActivity(intent)
        }

        //  Garden Maintenance
        findViewById<Button>(R.id.btnGardenMain).setOnClickListener {
            val intent = Intent(this, GardenMaintenanceActivity::class.java)
            startActivity(intent)


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
            R.id.nav_find_us-> {
                startActivity(Intent(this, MapsActivity::class.java))
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


    private fun toggleVisibility(layout: LinearLayout, button: Button) {
        if (layout.visibility == View.VISIBLE) { // drop down menu
            layout.visibility = View.GONE
            button.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.ic_arrow_drop_down,
                0
            )
        } else {
            layout.visibility = View.VISIBLE
            button.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.ic_arrow_drop_up,
                0
            )
        }

    }



}