package com.example.e_ticket.Activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.e_ticket.R
import com.example.e_ticket.databinding.ActivityLoginBinding
import com.example.e_ticket.databinding.ActivityMainBinding
import com.example.e_ticket.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.Objects

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var fireBaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.registerBtn.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity()::class.java)
            startActivity(intent)
        }
        fireBaseAuth = FirebaseAuth.getInstance()

        binding.loginBtn.setOnClickListener {

            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            if(username.isNotEmpty() && password.isNotEmpty()){
               fireBaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener{
                   if (it.isSuccessful){
                       val intent = Intent(this@LoginActivity, MainActivity()::class.java)
                       startActivity(intent)
                   }else{
                       Toast.makeText(this, it.exception.toString(),Toast.LENGTH_SHORT).show()
                   }
               }
            }
        }
        fun saveCredentials(username: String, password: String) {
            val prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("username", username)
            editor.putString("password", password)
            editor.apply()
        }

    }
    override fun onPause() {
        super.onPause()
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }




}