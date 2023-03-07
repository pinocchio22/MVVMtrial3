package com.example.fob_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var userName: String? = ""
        val result = userName?.isEmpty()
        if (result == true) {
            println("사용자 이름을 설정해주세요")
        }
        println("result: $result")

//        test1("Ss")
    }

    fun test() {
        val double : (Int) -> Int = {
            it * 2
        }

        val intArray = IntArray(5) {
            it + 1
        }
    }
}

class test1(dateTime : String) {
    var datetime = dateTime
        set(value) {
            if (value.isBlank()) {
                field = value
            }
        }
}