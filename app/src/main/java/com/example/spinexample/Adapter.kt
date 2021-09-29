package com.example.spinexample

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import androidx.recyclerview.widget.RecyclerView
import com.example.spinexample.databinding.ItemBinding
import com.google.android.material.animation.AnimationUtils
import java.util.logging.Handler

class Adapter(
    private val list: List<Data>,
    private val recyclerView: RecyclerView,
    private val boolean: Boolean
) : RecyclerView.Adapter<Adapter.Holder>() {

    inner class Holder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context))
        val holder = Holder(binding)
        return holder
    }

    override fun onBindViewHolder(holder: Holder, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.icon1.setImageResource(list[position].icon)
        if (boolean == true) {
            recyclerView.smoothScrollToPosition(position + 5)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

