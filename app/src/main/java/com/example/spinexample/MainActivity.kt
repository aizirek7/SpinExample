package com.example.spinexample

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.LinearGradient
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.util.TypedValue
import android.view.*
import androidx.recyclerview.widget.GridLayoutManager
import com.example.spinexample.databinding.ActivityMainBinding
import java.util.*
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    var dataList = mutableListOf<Data>()
    private var list = mutableListOf<Data>()
    private var records = mutableListOf<String>()
    var count = 1
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        saveSharedPref(true, "boolean")
        firstRunCheck("boolean")
        setUserIcon("boolean")



        dataList.add(Data(R.drawable.icon1))
        dataList.add(Data(R.drawable.icon2))
        dataList.add(Data(R.drawable.icon3))
        dataList.add(Data(R.drawable.icon4))
        dataList.add(Data(R.drawable.icon5))
        dataList.add(Data(R.drawable.icon6))
        dataList.add(Data(R.drawable.icon7))
        dataList.add(Data(R.drawable.icon8))
        dataList.add(Data(R.drawable.icon9))
        dataList.add(Data(R.drawable.icon10))

        list.addAll(dataList)
        dataList.addAll(list)
        list.addAll(dataList)
        dataList.addAll(list)
        list.addAll(dataList)
        dataList.addAll(list)
        list.addAll(dataList)
        dataList.addAll(list)
        list.addAll(dataList)
        dataList.addAll(list)
        list.addAll(dataList)

        initAdapter(false)

    }

    fun onClick1(v: View?) {
        if (v == binding.minus) {
            if (count == 1) {
                count == 1
            } else {
                count--
                binding.textView.setText(count.toString())
            }
        }
        if (v == binding.plus) {
            count++
            binding.textView.setText(count.toString())
        } else if (v == binding.spin) {
            initAdapter(true)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initAdapter(boolean: Boolean) {
        binding.recyclerView.layoutManager = GridLayoutManager(applicationContext, 5)
        Collections.shuffle(list)
        binding.recyclerView.setOnTouchListener { v, event -> true }
        val adapter = Adapter(list, binding.recyclerView, boolean)
        binding.recyclerView.adapter = adapter
        if (boolean == true) {
            binding.textView2.text = random()
            records.add(binding.textView2.text.toString())
            setLists(records as ArrayList<String>)
        } else {
            binding.textView2.text = "0"
        }
    }

    private fun random(): String {
        return (0 until 10001).random().toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    fun setLists(list: ArrayList<String>) {
        val sharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString("LIST", json)
        editor.commit()
    }

    fun setActivity(activity: Activity) {
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }

    fun firstRunCheck(key: String){
        Log.i("TAG", "firstRunCheck")
        val boolean = loadSharedPref(key)
        if (boolean == true){
            Log.i("TAG", "boolean true")
            binding.name.text = "unknown"
            val value = intent.getStringExtra("userName")
            if(value != null) {
                Log.i("TAG", "isNotEmpty")
                save(value.toString(), "c")
                binding.name.text = value.toString()
                saveSharedPref(false, "boolean")
                Log.i("TAG", load("c"))
                Log.i("TAG1", loadSharedPref("boolean").toString())
            }
        }
        else if (boolean == false){
            val value = intent.getStringExtra("userName")
            Log.i("TAG", value.toString())
            Log.i("TAG", "boolean false")
            Log.i("TAG", load("c"))
            if(value == load("c") || value == null){
                binding.name.text = load("c")
            }
            else{
                save(value.toString(), "c")
                Log.i("TAG", "1234567")
                binding.name.text = load("c")
            }
        }
    }

    fun setUserIcon(key: String){
        Log.i("TAGICON", "setUserIcon")
        val boolean = loadSharedPref(key)
        if (boolean == true){
            Log.i("TAGICON", "boolean true")
            binding.Icon.setImageResource(R.drawable.bakgraund)
            val value = intent.getIntExtra("icon", R.drawable.icon1)
            if (value != R.drawable.bakgraund){
                Log.i("TAGICON", "isNotEmpty")
                save(value.toString(), "icon")
                binding.Icon.setImageResource(value)
                saveSharedPref(false, "boolean")
                Log.i("TAGICON", loadSharedPref("boolean").toString())
            }
        }
        else if (boolean == false){
            val value = intent.getIntExtra("icon", R.drawable.icon1)
            Log.i("TAGICON", value.toString())
            Log.i("TAGICON", "boolean false")
            Log.i("TAGICON", load("icon"))
            if (value.toString() == load("icon") || value == R.drawable.bakgraund){
                binding.Icon.setImageResource(load("icon").toInt())
            }
            else{
                save(value.toString(), "icon")
                Log.i("TAGICON", "123")
                binding.Icon.setImageResource(load("icon").toInt())
            }
        }
    }

    fun saveSharedPref(id: Boolean, key: String) {
        val sharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putBoolean(key, id)
        }.apply()
    }


    fun loadSharedPref(key: String): Boolean {
        val sharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val boolean = sharedPreferences.getBoolean(key, true)
        return boolean
    }

    fun save(value: String, key: String){
        val sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString(key, value)
        }.apply()
    }

    fun load(key: String): String{
        val sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE)
        val value = sharedPreferences.getString(key, "11")
        return value.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> setActivity(Setting())
            R.id.record -> setActivity(Record())
        }
        return super.onOptionsItemSelected(item)
    }
}