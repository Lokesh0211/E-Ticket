package com.example.e_ticket.Activities

import android.content.Intent
import com.example.e_ticket.Activities.MyViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.example.e_ticket.R

class SplashScreen : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()


        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashScreen, LoginActivity()::class.java)
            startActivity(intent)
        }, 1000)
        val viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
    }
    override fun onPause() {
        super.onPause()
        finish()
    }
}