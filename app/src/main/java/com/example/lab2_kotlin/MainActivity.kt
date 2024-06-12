package com.example.lab2_kotlin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun doActivity(v: View) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setClass(this, SecondActivity::class.java)
        startActivity(intent)
    }

    fun doYahoo(v: View) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("http://www.yahoo.com.tw")
        startActivity(intent)
    }
}