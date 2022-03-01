package com.example.single

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.single.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var nameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var registerBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fillComponents()
        registerBtn.setOnClickListener {
            createUser()
        }
    }

    private fun createUser() {
        val userName = nameInput.text.toString()
        val userEmail = emailInput.text.toString()
        val userPass = passwordInput.text.toString()
        userCheck(userEmail, userPass)
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(userEmail, userPass)
    }

    private fun userCheck(userEmail: String, userPass: String) {
        if (userEmail.isNullOrEmpty() || userPass.isNullOrEmpty()){
            return Toast.makeText(
                this,
                "Os campos senha e email devem ser preenchidos",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun fillComponents() {
        this.nameInput = binding.nameInput
        this.emailInput = binding.emailInput
        this.passwordInput = binding.passinput
        this.registerBtn = binding.regButton
    }

}