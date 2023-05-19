package com.example.e_ticket.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.e_ticket.databinding.ActivityTrainEnquiryBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TrainEnquiryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTrainEnquiryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityTrainEnquiryBinding.inflate(layoutInflater)
     setContentView(binding.root)
        binding.showTrainDetails.setOnClickListener {
            val trainNumber = binding.etTrainNumberOne.text.toString()
            if(trainNumber.isNotEmpty()){
                getData(trainNumber)
            }
            else{
                Toast.makeText(this, "Enter Train Number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getData(trainNumber: String) {
        val db = Firebase.firestore
        val userid = FirebaseAuth.getInstance().currentUser!!.uid
        val docRef = db.collection("TrainNumbers").whereEqualTo("TrainNo", trainNumber)
        docRef.get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    Toast.makeText(this, "Train doesn't exist", Toast.LENGTH_SHORT).show()
                } else {
                    val document = documents.first()
                    val from = document.getString("From")
                    val to = document.getString("To")
                    val distance = document.getString("Distance")
                    val time = document.getString("Time")

                    Toast.makeText(this,"Successful", Toast.LENGTH_SHORT).show()
                    binding.etTrainNumberOne.text.clear()
                    binding.tvFrom.text = from
                    binding.tvTo.text = to
                    binding.tvDistance.text = distance
                    binding.tvTravelTime.text = time
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Failed to get details: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
