package com.example.template

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Record : AppCompatActivity() {
    lateinit var recordRecyclerView: RecyclerView
    lateinit var messageEmpty: TextView
    var icon = R.drawable.icon1
    var username = "unknown"
    val list = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        list.add("blabla1")
        list.add("blabla2")
        list.add("blabla3")
        list.add("blabla4")

        recordRecyclerView = findViewById(R.id.recordRecyclerView)
        messageEmpty = findViewById(R.id.messageEmpty)

        getUser()

        initAdapter()

    }

    private fun initAdapter() {
        recordRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = Adapter2(getList(), username, icon)
        recordRecyclerView.adapter = adapter
    }

    fun getList(): ArrayList<String> {
        val sharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("LIST", null)
        if (json.isNullOrEmpty()) {
            recordRecyclerView.visibility = View.GONE
            messageEmpty.visibility = View.VISIBLE
        } else {
            recordRecyclerView.visibility = View.VISIBLE
            messageEmpty.visibility = View.GONE
            val type = object : TypeToken<ArrayList<String>>() {}.type
            return gson.fromJson(json, type)
        }
        return list as ArrayList<String>
    }

    fun onClick(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun getUser() {
        if (load("username") != null && load("icon") != null) {
            username = load("username").toString()
            icon = load("icon")!!.toInt()
        }
        if (load("username") != null) {
            username = load("username").toString()
        }
        if (load("icon") != null) {
            icon = load("icon")!!.toInt()
        }
    }

    fun load(key: String): String? {
        val sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE)
        val value = sharedPreferences.getString(key, null)
        return value
    }
}