package com.example.e_ticket.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.e_ticket.R
import com.example.e_ticket.databinding.ActivityFareCalculatorBinding

class FareCalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFareCalculatorBinding
    private var sourceStation: String = ""
    private var destinationStation: String = ""
    private var numPassengers: Int = 0
    val fareMatrix = arrayOf(
        doubleArrayOf(0.0, 10.0, 20.0, 30.0),
        doubleArrayOf(10.0, 0.0, 15.0, 25.0),
        doubleArrayOf(20.0, 15.0, 0.0, 10.0),
        doubleArrayOf(30.0, 25.0, 10.0, 0.0)
    )
    private val stationList = arrayOf("BCM", "KTDM", "PVC", "SCM")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFareCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize source station spinner
        val sourceStationList = arrayOf("BCM", "KTDM", "PVC", "SCM")
        val sourceAdapter = ArrayAdapter(this, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, sourceStationList)
        binding.spinner1.adapter = sourceAdapter
        binding.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                sourceStation = sourceStationList[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        // Initialize destination station spinner
        val destinationStationList = arrayOf("BCM", "KTDM", "PVC", "SCM")
        val destinationAdapter = ArrayAdapter(this, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, destinationStationList)
        binding.spinner2.adapter = destinationAdapter
        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                destinationStation = destinationStationList[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        // Set click listener for the submit button
        binding.fareCalculate.setOnClickListener {
            if (sourceStation.isEmpty() || destinationStation.isEmpty()) {
                Toast.makeText(this, "Please select source and destination stations", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.etNoOfPeople.text.isEmpty()) {
                Toast.makeText(this, "Please enter the number of passengers", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            numPassengers = binding.etNoOfPeople.text.toString().toInt()

            val fare = calculateFare(sourceStation, destinationStation, numPassengers)

            binding.txtFareDisplay.text = "$fare /-"
        }
    }

    private fun calculateFare(source: String, destination: String, numPassengers: Int): Double {
            val sourceIndex = getIndex(source)
            val destinationIndex = getIndex(destination)

            if (sourceIndex == -1 || destinationIndex == -1) {
                throw IllegalArgumentException("Invalid source or destination station")
            }

            val fare = fareMatrix[sourceIndex][destinationIndex] * numPassengers
            return fare
        }

        private fun getIndex(station: String): Int {
            return stationList.indexOf(station)
    }
}
