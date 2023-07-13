package com.aamirashraf.brochillassignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aamirashraf.brochillassignment.R
import com.aamirashraf.brochillassignment.model.Tweet

class TweetAdapter(var tweet:List<Tweet>):RecyclerView.Adapter<TweetAdapter.TweetViewHolder>() {
    inner class TweetViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val tv_tweet=itemView.findViewById<TextView>(R.id.tv_rv_items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.rv_tweets,parent,false)
        return TweetViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tweet.size
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        val curr=tweet[position]
        holder.tv_tweet.text=curr.tw
    }
}