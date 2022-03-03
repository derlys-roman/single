package com.example.single

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.single.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var newHere: TextView
    private lateinit var login: Button
    private lateinit var email: EditText
    private lateinit var pasw: EditText
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fillLayoutComponents()
        createAccount()
    }

    private fun fillLayoutComponents() {
        newHere = binding.tvCreateAccount
        login = binding.regButton
        email = binding.emailinput
        pasw = binding.passinput
    }

    private fun login(){
        login.setOnClickListener{
            val mail = email.text.toString()
            val pass = pasw.text.toString()
        }
    }

    private fun createAccount(){
        newHere.setOnClickListener {

            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}