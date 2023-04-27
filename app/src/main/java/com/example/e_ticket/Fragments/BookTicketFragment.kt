package com.example.e_ticket.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.e_ticket.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BookTicketFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_ticket, container, false)
        val db = Firebase.firestore

        val button = view.findViewById<Button>(R.id.bookTicket)
        button.setOnClickListener {
            val Fname = view.findViewById<EditText>(R.id.et_name).text.toString().trim()
            val mobileNumber = view.findViewById<EditText>(R.id.et_mobileNumber).text.toString().trim()
            val startingStation = view.findViewById<EditText>(R.id.et_starting).text.toString().trim()
            val destinationStation = view.findViewById<EditText>(R.id.et_destination).text.toString().trim()
            val aadharNumber = view.findViewById<EditText>(R.id.et_aadharNumber).text.toString().trim()
            val dob = view.findViewById<EditText>(R.id.et_dob).text.toString().trim()
            val det = hashMapOf(
                "Full Name" to Fname,
                "Mobile Number" to mobileNumber,
                "Starting Station" to startingStation,
                "Destination Station" to destinationStation,
                "Aadhar Number" to aadharNumber,
                "Date of Birth" to dob
            )
            val userid = FirebaseAuth.getInstance().currentUser!!.uid
            db.collection("user").document(userid).set(det)
                .addOnSuccessListener {
                    val context = activity?.applicationContext
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    val context = activity?.applicationContext
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                }
        }

        return view
    }
}