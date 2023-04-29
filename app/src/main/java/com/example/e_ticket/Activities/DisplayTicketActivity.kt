package com.example.e_ticket.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.e_ticket.databinding.ActivityBookTicketBinding
import com.example.e_ticket.databinding.ActivityDisplayTicketBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.text.isNotEmpty


class DisplayTicketActivity : AppCompatActivity() {
    lateinit var binding: ActivityDisplayTicketBinding
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.showTicket.setOnClickListener {
            val fullName = binding.etPassengerName.text.toString()
            if(fullName.isNotEmpty()){
                getData(fullName)
            }
            else{
                Toast.makeText(this, "Enter user name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getData(fullName: String) {
        val db = Firebase.firestore
        val userid = FirebaseAuth.getInstance().currentUser!!.uid
        val docRef = db.collection("user").document(userid).collection("bookings").whereEqualTo("Full Name", fullName)
        docRef.get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {

                    Toast.makeText(this, "User doesn't exist", Toast.LENGTH_SHORT).show()
                } else {
                    val document = documents.first()
                    val Fname = document.getString("Full Name")
                    val Mnumber = document.getString("Mobile Number")
                    val Sstation = document.getString("Starting Station")
                    val Dstation = document.getString("Destination Station")
                    val Anumber = document.getString("Aadhar Number")
                    val Dob = document.getString("Date of Birth")

                    Toast.makeText(this,"Successful", Toast.LENGTH_SHORT).show()
                    binding.etPassengerName.text.clear()
                    binding.tvFullName.text = Fname
                    binding.tvMobileNumber.text = Mnumber
                    binding.tvSource.text = Sstation
                    binding.tvDestination.text = Dstation
                    binding.tvAadhar.text = Anumber
                    binding.tvDob.text = Dob
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Failed to get details: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }

}