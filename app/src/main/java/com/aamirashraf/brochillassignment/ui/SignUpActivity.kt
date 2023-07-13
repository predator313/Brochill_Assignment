package com.aamirashraf.brochillassignment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.aamirashraf.brochillassignment.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var mdbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_sign_up)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnNextSignup.setOnClickListener {
            when{
                TextUtils.isEmpty(binding.etEmailSignup.text.toString().trim{it<=' '})->{
                    Toast.makeText(this@SignUpActivity,"email is empty",Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(binding.etFirstName.text.toString().trim{it<=' '})->{
                    Toast.makeText(this@SignUpActivity,"firstName is empty",Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(binding.etLastName.text.toString().trim{it<=' '})->{
                    Toast.makeText(this@SignUpActivity,"last name is empty",Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(binding.etpasswordsignup.text.toString().trim{it<=' '})->{
                    Toast.makeText(this@SignUpActivity,"password is empty",Toast.LENGTH_SHORT).show()
                }
                else ->{
                    val email=binding.etEmailSignup.text.toString().trim{it<=' '}
                    val password=binding.etpasswordsignup.text.toString().trim{it<=' '}
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener {task->
                            if(task.isSuccessful){
                                val firebaseUser:FirebaseUser = task.result!!.user!!
                                Toast.makeText(this@SignUpActivity,"signup successfully",Toast.LENGTH_SHORT).show()
                                Intent(this@SignUpActivity, LoginActivity::class.java).also {
                                    startActivity(it)
                                    finish()
                                }
                            }
                            else{
                                Toast.makeText(this@SignUpActivity,"signup fail",Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }
        }
    }
}