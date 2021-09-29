package com.example.spinexample

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spinexample.databinding.ActivityMainBinding
import com.example.spinexample.databinding.ActivityRecordBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Record : AppCompatActivity() {
    lateinit var binding: ActivityRecordBinding
    val list = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        list.add("blabla1")
        list.add("blabla2")
        list.add("blabla3")
        list.add("blabla4")

    }

    private fun initAdapter() {
        binding.recordRecuclerView.layoutManager = LinearLayoutManager(applicationContext)
        val adapter = Adapter2(getList(), "userName", R.drawable.icon1)
        binding.recordRecuclerView.adapter = adapter
    }

    fun getList(): ArrayList<String> {
        val sharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("LIST", null)
        if (json.isNullOrEmpty()) {
            binding.recordRecuclerView.visibility = View.GONE
            binding.messageEmpty.visibility = View.VISIBLE
        } else {
            binding.recordRecuclerView.visibility = View.VISIBLE
            binding.messageEmpty.visibility = View.GONE
            val type = object : TypeToken<ArrayList<String>>() {}.type
            return gson.fromJson(json, type)
        }
        return list as ArrayList<String>
    }

    fun onClick(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}