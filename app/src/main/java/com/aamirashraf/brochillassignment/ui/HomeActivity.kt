package com.aamirashraf.brochillassignment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aamirashraf.brochillassignment.R
import com.aamirashraf.brochillassignment.adapter.TweetAdapter
import com.aamirashraf.brochillassignment.model.Tweet
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    private val tweetCollectionRef= Firebase.firestore.collection("Tweets")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
//        var tweets= mutableListOf(
//            Tweet("cr7: it is good to back on the field lets go lads"),
//            Tweet("Bitcoin: Again falling in crypto share market graph is still falling"),
//            Tweet("Rain: Raining probability in India is very high in this week")
//        )
        subscribeToTheRealTimeUpdates()
//        var tweetAdapter=TweetAdapter(tweets)
//        val rv=findViewById<RecyclerView>(R.id.rvHome)
//        rv.adapter=tweetAdapter
//        rv.layoutManager=LinearLayoutManager(this)
        val fab=findViewById<FloatingActionButton>(R.id.fabAdd)
        fab.setOnClickListener {
            Intent(this@HomeActivity, TweetActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

    }
    fun subscribeToTheRealTimeUpdates(){
        tweetCollectionRef.addSnapshotListener{ value,error ->
            error?.let {
                //means there is error in the retrieving process
                Toast.makeText(this@HomeActivity,it.message.toString(),Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            value?.let {
                var listt= mutableListOf<Tweet>()
                for (doc in it){
                    val tweet=doc.toObject<Tweet>()
                    listt.add(tweet)
                }
//                Log.d("MainActivity",listt.toString())
                var tweetAdapter=TweetAdapter(listt)
                val rv=findViewById<RecyclerView>(R.id.rvHome)
                rv.adapter=tweetAdapter
                rv.layoutManager=LinearLayoutManager(this@HomeActivity)
            }
        }
    }
}