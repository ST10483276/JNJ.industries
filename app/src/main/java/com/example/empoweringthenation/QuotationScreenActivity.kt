package com.example.empoweringthenation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.roundToInt

class QuotationScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quotation_screen)
        val selectedCourses = intent.getStringArrayListExtra("SELECTED_COURSES") ?: arrayListOf()
        val totalPrice = intent.getIntExtra("TOTAL_PRICE", 0)

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
            else -> if (courseCount > 3) 15 else 0
        }

        val discountAmount = (totalPrice * discountPercent / 100.0).roundToInt()
        val finalPrice = totalPrice - discountAmount

        // Displays quotation-style breakdown
        txtBreakdown.text = """
            QUOTATION

            Number of courses: $courseCount
            Subtotal: R$totalPrice
            Discount: $discountPercent% (R$discountAmount)
            --------------------------------------
            Final Price: R$finalPrice
        """.trimIndent()

        // Confirm Button
        btnConfirm.setOnClickListener {
            val name = edtName.text.toString().trim()
            val phone = edtPhone.text.toString().trim()
            val email = edtEmail.text.toString().trim()

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Please fill in all details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            // Display Thank You Message
            AlertDialog.Builder(this)
                .setTitle("Enquiry Submitted")
                .setMessage("Thank you $name for your enquiry!\n\nWe will contact you shortly at:\n $phone\n $email \n" +
                        "\nFor more details, please visit our Contact page.")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()

                    // After confirming, return to selection page & clear selections
                    val intent = Intent(this, ContactUsActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // ensures we return clean
                    startActivity(intent)
                }
                .show()

        }
    }
}