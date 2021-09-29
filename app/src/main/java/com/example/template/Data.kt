package com.example.template

import java.io.Serializable

data class Data(val icon: Int)

data class Data2(val icon: Int, val userName: String, val gold: String)

data class Data3(val icon: Int, val userName: String) : Serializable