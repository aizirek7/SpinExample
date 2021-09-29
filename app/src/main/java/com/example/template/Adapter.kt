package com.example.template

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.template.R

class Adapter(
    private val list: List<Data>,
    private val recyclerView: RecyclerView,
    private val boolean: Boolean,
    private val string: String
) : RecyclerView.Adapter<Adapter.Holder>() {

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val icon1 = view.findViewById<ImageView>(R.id.icon1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        if (string.equals("easy")){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
            return Holder(view)
        }
        if(string.equals("middle")){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item2, parent, false)
            return Holder(view)

        }
        if (string.equals("hard")){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item3, parent, false)
            return Holder(view)
        }
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, @SuppressLint("RecyclerView") position: Int) {
        holder.icon1.setImageResource(list[position].icon)
        if (boolean == true) {
            recyclerView.smoothScrollToPosition(position + 5)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

