package com.example.e_ticket.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.e_ticket.R
import com.example.e_ticket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bookTicket.setOnClickListener {
           val intent =  Intent(this@MainActivity, BookTicketActivity::class.java)
            startActivity(intent)
        }
        binding.showTicket.setOnClickListener {
            val intent =  Intent(this@MainActivity, DisplayTicketActivity::class.java)
            startActivity(intent)
        }
        binding.fareCalculator.setOnClickListener {
           /* val transaction : FragmentTransaction =  supportFragmentManager.beginTransaction()
            transaction.replace(R.id.activity_main, PNRStatusFragment())
            transaction.addToBackStack("PNRStatus")
            transaction.commit()*/
            val intent =  Intent(this@MainActivity, FareCalculatorActivity::class.java)
            startActivity(intent)
        }
        binding.trainEnquiry.setOnClickListener {
          /*  val transaction : FragmentTransaction =  supportFragmentManager.beginTransaction()
            transaction.replace(R.id.activity_main, TrainEnquiryFragment())
            transaction.addToBackStack("trainEnquiry")
            transaction.commit()*/
            val intent =  Intent(this@MainActivity, TrainEnquiryActivity::class.java)
            startActivity(intent)
        }
        binding.BtnLogout.setOnClickListener {
            val intent = Intent(this@MainActivity, LoginActivity()::class.java)
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}