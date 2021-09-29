package com.example.spinexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spinexample.databinding.RecordItemBinding


class Adapter2(
    private val gold: List<String>,
    private val userName: String,
    private val icon: Int
) : RecyclerView.Adapter<Adapter2.Holder>() {

    inner class Holder(val binding: RecordItemBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = RecordItemBinding.inflate(LayoutInflater.from(parent.context))
        val holder = Holder(binding)
        return holder
    }

    override fun getItemCount(): Int {
        return gold.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.apply {
            binding.userName.text = userName
            binding.recordGold.text = gold[position]
            binding.recordIcon.setImageResource(icon)
        }
    }
}

