package com.example.e_ticket.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.example.e_ticket.Fragments.BookTicketFragment
import com.example.e_ticket.Fragments.PNRStatusFragment
import com.example.e_ticket.Fragments.ShowTicketFragment
import com.example.e_ticket.Fragments.TrainEnquiryFragment
import com.example.e_ticket.R
import com.example.e_ticket.databinding.ActivityLoginBinding
import com.example.e_ticket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bookTicket.setOnClickListener {
           val transaction : FragmentTransaction =  supportFragmentManager.beginTransaction()
            transaction.replace(R.id.activity_main, BookTicketFragment())
            transaction.addToBackStack("bookTicket")
            transaction.commit()
        }
        binding.showTicket.setOnClickListener {
            val transaction : FragmentTransaction =  supportFragmentManager.beginTransaction()
            transaction.replace(R.id.activity_main, ShowTicketFragment())
            transaction.addToBackStack("showTicket")
            transaction.commit()
        }
        binding.PNRStatus.setOnClickListener {
            val transaction : FragmentTransaction =  supportFragmentManager.beginTransaction()
            transaction.replace(R.id.activity_main, PNRStatusFragment())
            transaction.addToBackStack("PNRStatus")
            transaction.commit()
        }
        binding.trainEnquiry.setOnClickListener {
            val transaction : FragmentTransaction =  supportFragmentManager.beginTransaction()
            transaction.replace(R.id.activity_main, TrainEnquiryFragment())
            transaction.addToBackStack("trainEnquiry")
            transaction.commit()
        }
        binding.BtnLogout.setOnClickListener {
            val intent = Intent(this@MainActivity, LoginActivity()::class.java)
            startActivity(intent)
        }
    }
}