package com.aamirashraf.brochillassignment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.aamirashraf.brochillassignment.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var email:EditText
    private lateinit var password:EditText
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        auth= FirebaseAuth.getInstance()
        email=binding.etEmailLogin
        password=binding.etPassword
        binding.btnNextLogin.setOnClickListener {
            val email=email.text.toString()
            val password=password.text.toString()
            login(email,password)
        }
    }
    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
//                    val user = auth.currentUser
                    val intent= Intent(this@LoginActivity, HomeActivity::class.java)

                    startActivity(intent)
                    finish()
                    // Launch main activity or navigate to next screen
                } else {
                    // Sign in fails, display a message to the user.
                    Toast.makeText(this@LoginActivity, "User doesn't exist.", Toast.LENGTH_LONG).show()
                    Intent(this@LoginActivity, SignUpActivity::class.java).also {
                        startActivity(it)
                    }
                }
            }
    }
}