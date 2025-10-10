package com.example.empoweringthenation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlin.math.roundToInt

class QuotationScreenActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotation_screen)

        // Set up the toolbar
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

        // Get selected courses and total price from intent
        val selectedCourses = intent.getStringArrayListExtra("SELECTED_COURSES") ?: arrayListOf()
        val totalPrice = intent.getIntExtra("TOTAL_PRICE", 0)

        // Link views
        val txtSelected = findViewById<TextView>(R.id.txSelectedCourses)
        val txtBreakdown = findViewById<TextView>(R.id.txtBreakdown)
        val edtName = findViewById<EditText>(R.id.edtName)
        val edtPhone = findViewById<EditText>(R.id.edtPhone)
        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val btnConfirm = findViewById<Button>(R.id.btnConfirm)

        


        // Build selected course list
        val courseListBuilder = StringBuilder("Courses Selected:\n\n")
        for (course in selectedCourses) {
            courseListBuilder.append("• $course\n")
        }
        txtSelected.text = courseListBuilder.toString()

        // Apply discount rules
        val courseCount = selectedCourses.size
        val discountPercent = when (courseCount) {
            1 -> 0
            2 -> 5
            3 -> 10
            else -> 15
        }

        val discountAmount = (totalPrice * discountPercent / 100.0).roundToInt()
        val finalPrice = totalPrice - discountAmount

        // Display quotation breakdown
        txtBreakdown.text = """
            QUOTATION

            Number of courses: $courseCount
            Subtotal: R$totalPrice
            Discount: $discountPercent% (R$discountAmount)
            --------------------------------------
            Final Price: R$finalPrice
        """.trimIndent()

        // Confirm button validates and shows dialog
        btnConfirm.setOnClickListener {
            val name = edtName.text.toString().trim()
            val phone = edtPhone.text.toString().trim()
            val email = edtEmail.text.toString().trim()

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Please fill in all details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            AlertDialog.Builder(this)
                .setTitle("Enquiry Submitted")
                .setMessage("Thank you $name for your enquiry!\n\nWe will contact you shortly at:\n$phone\n$email\n\nFor more details, please visit our Contact page.")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                    val intent = Intent(this, ContactUsActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                }
                .show()
        }
    }

    // Handle navigation drawer item clicks
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> startActivity(Intent(this, HomeActivity::class.java))
            R.id.nav_six_week -> startActivity(Intent(this, CourseDetailActivity::class.java))
            R.id.nav_course_selection -> startActivity(Intent(this, CourseSelectionActivity2::class.java))
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