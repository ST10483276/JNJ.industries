package com.example.empoweringthenation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CourseSelectionActivity2 : AppCompatActivity() {

    data class Course(val name: String, val price: Int)

    private val courses = listOf(
        Course("First Aid", 1500),
        Course("Sewing", 1500),
        Course("Landscaping", 1500),
        Course("Life Skills", 1500),
        Course("Child Minding", 750),
        Course("Cooking:", 750),
        Course("Garden Maintenance", 750),
    )
    private lateinit var checkBoxes: List<CheckBox>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_course_selection2)
        checkBoxes = listOf(
            findViewById(R.id.ChkFirstAid),
            findViewById(R.id.ChkSewing),
            findViewById(R.id.ChkLandscaping),
            findViewById(R.id.ChkLifeSkills),
            findViewById(R.id.ChkChildMinding),
            findViewById(R.id.ChkCooking),
            findViewById(R.id.ChkGardenMain),

        )

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

    // Reset checkboxes when user comes back
    override fun onResume() {
        super.onResume()
        checkBoxes.forEach { it.isChecked = false }
    }

    }
