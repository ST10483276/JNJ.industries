package com.example.empoweringthenation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class CourseDetailActivity : AppCompatActivity() {
    private lateinit var layoutSixMonths: LinearLayout
    private lateinit var layoutSixWeek: LinearLayout
    private lateinit var btnSixMonths: Button
    private lateinit var btnSixWeeks: Button
    private lateinit var btnHomeScreen: ImageButton
    private lateinit var btnBack:ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_course_detail)

        layoutSixMonths = findViewById(R.id.layoutSixMonthsCourses)
        layoutSixWeek = findViewById(R.id.layoutSixWeeksCourses)
        btnSixMonths = findViewById(R.id.btnSixMonths)
        btnSixWeeks = findViewById(R.id.btnSixWeeks)
        btnHomeScreen = findViewById(R.id.btnHomeScreen)
        btnBack=findViewById(R.id.btnBack)

        btnBack.setOnClickListener{
            finish() // closes the list
        }

        btnHomeScreen.setOnClickListener {
            val intent = Intent(this, HomeScreenActivity::class.java)
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