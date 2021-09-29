package com.example.spinexample

import Adapter3
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.spinexample.databinding.ActivitySettingBinding

class Setting : AppCompatActivity(), Adapter3.OnItemClickListener {
    lateinit var viewPager: RecyclerView
    lateinit var editText: EditText
    lateinit var imageView: ImageView
    var list = mutableListOf<Data>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        viewPager = findViewById(R.id.viewPager2)
        editText = findViewById(R.id.editTextTextPersonName)
        imageView = findViewById(R.id.imageView)

        list.add(Data(R.drawable.icon1))
        list.add(Data(R.drawable.icon2))
        list.add(Data(R.drawable.icon3))
        list.add(Data(R.drawable.icon4))
        list.add(Data(R.drawable.icon5))
        list.add(Data(R.drawable.icon6))
        list.add(Data(R.drawable.icon7))
        list.add(Data(R.drawable.icon8))
        list.add(Data(R.drawable.icon9))
        list.add(Data(R.drawable.icon10))

        initAdapter()

    }

    private fun initAdapter() {
        viewPager.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, true)
        val adapter = Adapter3(list, this, applicationContext)
        viewPager.adapter = adapter
    }

    override fun onItemClick(position: Int, list: List<Data>, view: View) {
        val intent = Intent(this, MainActivity::class.java)
        val text = editText.text.toString()
        intent.putExtra("userName", text)
        intent.putExtra("icon", list[position].icon)
        if (text.isNotEmpty()){
            editText.hint = text
        }
        startActivity(intent)
    }
    fun onClick(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}