package com.aamirashraf.brochillassignment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.aamirashraf.brochillassignment.databinding.ActivityTweetBinding
import com.aamirashraf.brochillassignment.model.Tweet
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class TweetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTweetBinding
    private val tweetCollectionRef=Firebase.firestore.collection("Tweets")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTweetBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.btnPost.setOnClickListener {
            when{
                TextUtils.isEmpty(binding.tvPersonData.text.toString().trim{it<=' '})->{
                    Toast.makeText(this@TweetActivity,"tweet is empty",Toast.LENGTH_SHORT).show()
                }
                else ->{
                    val tweet=binding.tvPersonData.text.toString().trim{it<=' '}
                    val tweett= Tweet(tweet)
                    saveToDb(tweett)
                    Intent(this@TweetActivity, HomeActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
                }
            }
        }
    }

    private fun saveToDb(tweet: Tweet)= CoroutineScope(Dispatchers.IO).launch {
        try {
            tweetCollectionRef.add(tweet).await()
            withContext(Dispatchers.Main){
                Toast.makeText(this@TweetActivity,"successfully saved to db",Toast.LENGTH_SHORT).show()
            }


        }catch (e:Exception){
            withContext(Dispatchers.Main){
                Toast.makeText(this@TweetActivity,e.message.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }
}