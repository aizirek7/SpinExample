package com.example.template

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class Adapter2(
    private val gold: List<String>,
    private val userName1: String,
    private val icon: Int
) : RecyclerView.Adapter<Adapter2.Holder>() {

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val userName = view.findViewById<TextView>(R.id.userName)
        val recordGold = view.findViewById<TextView>(R.id.recordGold)
        val recordIcon = view.findViewById<ImageView>(R.id.recordIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.record_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return gold.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.apply {
            userName.text = userName1
            recordGold.text = gold[position]
            recordIcon.setImageResource(icon)
        }
    }
}

