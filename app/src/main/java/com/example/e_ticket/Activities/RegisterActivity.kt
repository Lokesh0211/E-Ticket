package com.example.e_ticket.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.e_ticket.R
import com.example.e_ticket.databinding.ActivityMainBinding
import com.example.e_ticket.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var fireBaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fireBaseAuth = FirebaseAuth.getInstance()
        binding.Register.setOnClickListener {
            val username = binding.RUsername.text.toString()
            val password = binding.RPassword.text.toString()
            val confirmPassword = binding.RCPassword.text.toString()
            if (username.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                if (password == confirmPassword){
                     fireBaseAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener {
                         if (it.isSuccessful){
                             val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                             startActivity(intent)
                         }else{
                             print(it.exception.toString())
                             Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                         }
                     }
                }else{
                    Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "fill every detail", Toast.LENGTH_SHORT).show()
            }
        }

    }
}