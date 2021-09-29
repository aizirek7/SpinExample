package com.example.template

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    var dataList = mutableListOf<Data>()

    lateinit var plus: Button
    lateinit var minus: Button
    lateinit var name: TextView
    lateinit var editText: EditText
    lateinit var icon: ImageView
    lateinit var recyclerView: RecyclerView
    lateinit var toolbar: Toolbar
    lateinit var textView2: TextView
    lateinit var textView: TextView
    lateinit var spin: ImageView
    lateinit var actionbar: View
    var level = "easy"

    private var list = mutableListOf<Data>()
    private var records = mutableListOf<String>()
    var count = 1

    @SuppressLint("UseSupportActionBar")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plus = findViewById(R.id.plus)
        minus = findViewById(R.id.minus)
        name = findViewById(R.id.name)
        icon = findViewById(R.id.Icon)

        actionbar = LayoutInflater.from(this).inflate(R.layout.activity_setting, null);
        recyclerView = findViewById(R.id.recyclerView)
        toolbar = findViewById(R.id.toolbar12)
        editText = actionbar.findViewById(R.id.editTextTextPersonName)
        textView2 = findViewById(R.id.textView2)
        spin = findViewById(R.id.spin)
        textView = findViewById(R.id.textView)


        setSupportActionBar(toolbar)

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

        initAdapter(false, "easy")

        getProfile()

    }

    fun onClick1(v: View?) {
        if (v == minus) {
            if (count == 1) {
                count == 1
            } else {
                count--
                textView.setText(count.toString())
            }
        }
        if (v == plus) {
            count++
            textView.setText(count.toString())
        } else if (v == spin) {
            initAdapter(true, level)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initAdapter(boolean: Boolean, string: String) {
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 5)
        Collections.shuffle(list)
        recyclerView.setOnTouchListener { v, event -> true }
        val adapter = Adapter(list, recyclerView, boolean, string)
        recyclerView.adapter = adapter
        if (boolean == true) {
            textView2.text = random()
            records.add(textView2.text.toString())
            setLists(records as ArrayList<String>)
        } else {
            textView2.text = "0"
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


    fun load(key: String): String? {
        val sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE)
        val value = sharedPreferences.getString(key, null)
        return value
    }

    fun getProfile() {
        if (load("username") != null && load("icon") != null) {
            val name = load("username")
            Log.i("TAG", name.toString())
            val icon = load("icon")
            Log.i("TAG", icon.toString())
            this.name.text = name.toString()
            this.icon.setImageResource(icon!!.toInt())
        }
        if (load("username") != null) {
            val name = load("username")
            Log.i("TAG", name.toString())
            this.name.text = name.toString()
        }
        if(load("icon") != null){
            val icon = load("icon")
            Log.i("TAG", icon.toString())
            this.icon.setImageResource(icon!!.toInt())
        }
        else if (load("icon") == null && load("username") == null){
            this.name.text = "unknown"
            this.icon.setImageResource(R.drawable.icon1)
        }
        else if (load("icon") == null){
            this.icon.setImageResource(R.drawable.icon1)
        }
        else if (load("username") == null){
            this.name.text = "unknown"
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> setActivity(Setting())
            R.id.record -> setActivity(Record())
            R.id.easy -> onClick("easy")
            R.id.middle -> onClick("middle")
            R.id.hard -> onClick("hard")
        }
        return super.onOptionsItemSelected(item)
    }

    fun onClick(string: String) {
        if (string.equals("easy")) {
            level = "easy"
            initAdapter(false, level)
        }
        if (string.equals("middle")) {
            level = "middle"
            initAdapter(false, level)
        }
        if (string.equals("hard")) {
            level = "hard"
            initAdapter(false, level)
        }
    }
}