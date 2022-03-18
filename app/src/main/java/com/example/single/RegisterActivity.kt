package com.example.single

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.single.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var nameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var registerBtn: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var photo: Button
    private lateinit var frontPhoto: CircleImageView
    private lateinit var mImageUri: Uri
    private val getImage = registerForActivityResult(
        ActivityResultContracts.GetContent(),
        ActivityResultCallback { mImageUri = it
            frontPhoto.setImageURI(mImageUri)}
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fillComponents()
        registerBtn.setOnClickListener {
            createUser()
        }
        photo.setOnClickListener {
            selectPhoto()
        }
    }

    private fun selectPhoto() {
        getImage.launch("image/*")
        photo.alpha = 0f
    }

    private fun createUser() {
        val userName = nameInput.text.toString()
        val userEmail = emailInput.text.toString()
        val userPass = passwordInput.text.toString()
        userCheck(userEmail, userPass, userName)
        auth.createUserWithEmailAndPassword(userEmail, userPass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Test", "createUserWithEmail:success")
                    saveProfileFirebase()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Test", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }

    }

    private fun saveProfileFirebase() {
        val fileName = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$fileName")
        ref.putFile(mImageUri)
    }

    private fun userCheck(userEmail: String, userPass: String, userName: String) {
        if (userEmail.isEmpty() || userPass.isEmpty() || userName.isEmpty()){
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
        this.photo = binding.btnImageProfile
        this.auth = Firebase.auth
        this.frontPhoto = binding.photoSelected
    }

}