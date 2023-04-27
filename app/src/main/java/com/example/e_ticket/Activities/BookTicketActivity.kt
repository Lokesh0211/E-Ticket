package com.example.e_ticket.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.e_ticket.R
import com.example.e_ticket.databinding.ActivityBookTicketBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BookTicketActivity : AppCompatActivity() {

    lateinit var binding: ActivityBookTicketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = Firebase.firestore
        val button = findViewById<Button>(R.id.bookTicket)
        button.setOnClickListener {
            val fullName = binding.etName.text.toString().trim()
            val mobileNumber = binding.etMobileNumber.text.toString().trim()
            val sourceStation = binding.etStarting.text.toString().trim()
            val destinationStation = binding.etDestination.text.toString().trim()
            val aadharNumber = binding.etAadharNumber.text.toString().trim()
            val dob = binding.etDob.text.toString().trim()

            val det = hashMapOf(
                "Full Name" to fullName,
                "Mobile Number" to mobileNumber,
                "Starting Station" to sourceStation,
                "Destination Station" to destinationStation,
                "Aadhar Number" to aadharNumber,
                "Date of Birth" to dob
            )
            val userid = FirebaseAuth.getInstance().currentUser!!.uid
            val newDocRef = db.collection("user").document(userid).collection("bookings").document()
            newDocRef
                .set(det)
                .addOnSuccessListener {
                    Toast.makeText(this@BookTicketActivity, "Success", Toast.LENGTH_SHORT).show()
                    binding.etName.text.clear()
                    binding.etMobileNumber.text.clear()
                    binding.etStarting.text.clear()
                    binding.etDestination.text.clear()
                    binding.etAadharNumber.text.clear()
                    binding.etDob.text.clear()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this@BookTicketActivity, "Failed: ${e.message}", Toast.LENGTH_SHORT).show()
                    binding.etName.text.clear()
                    binding.etMobileNumber.text.clear()
                    binding.etStarting.text.clear()
                    binding.etDestination.text.clear()
                    binding.etAadharNumber.text.clear()
                    binding.etDob.text.clear()
                }
        }
    }
}