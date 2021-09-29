package com.example.template

import Adapter3
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
        if (text.isNotEmpty()){
            save(text, "username")
            save(list[position].icon.toString(), "icon")
        }
        else{
            save(list[position].icon.toString(), "icon")
        }
        startActivity(intent)
    }
    fun onClick(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun save(value: String, key: String) {
        val sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString(key, value)
        }.apply()
    }
}